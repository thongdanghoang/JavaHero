package ctrl;

import model.dao.StaffDAO;
import model.entity.Gender;
import model.entity.Staff;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StaffController {
    private StaffDAO dao;
    private List<Staff> lsStaffs;

    public StaffController() {
        dao = new StaffDAO();
        lsStaffs = dao.getAll();
    }

    public void insert(Staff newStaff) {
        lsStaffs.add(newStaff);
        dao.write(lsStaffs);
    }

    public List<Staff> getLsStaffs() {
        return lsStaffs;
    }

    public Staff findLargestSalaryByGender(Gender gender) {
        return lsStaffs.stream().filter(t -> t.getGender().equals(gender)).max(this::compare).orElse(null);
    }

    public Staff findLargestSalaryByPosition(Class<Staff> type) {
        return lsStaffs.stream().filter(t -> t.getClass() == type).max(this::compare).orElse(null);
    }

    private int compare(Staff t1, Staff t2) {
        return (int) (t1.calSalary() - t2.calSalary());
    }


}
