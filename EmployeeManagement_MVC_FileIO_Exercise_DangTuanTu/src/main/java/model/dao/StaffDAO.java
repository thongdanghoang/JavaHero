package model.dao;

import model.entity.Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {

    private static final String STAFF_FILE_NAME = "staff.txt";
    private File file = new File(STAFF_FILE_NAME);

    {
        if (!file.exists()) {
            write(new ArrayList<>());
        }
    }

    public List<Staff> getAll() {
        List<Staff> lsStaffs = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            lsStaffs = (List<Staff>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeStream(ois);
            closeStream(fis);
        }
        return lsStaffs;
    }

    public void write(List<Staff> lsStaff) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(lsStaff);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeStream(oos);
            closeStream(fos);
        }
    }


    private void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
