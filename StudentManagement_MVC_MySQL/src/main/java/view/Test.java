package view;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.StudentDAO;
import model.dao.exception.GetAllExceptions;
import model.dbconnection.DBConnectionException;
import model.entity.Student;

/**
 *
 * @author thomas
 */
public class Test {

    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        try {
            Map<String, Student> all = dao.getAll();
            all.values().forEach(System.out::println);
        } catch (DBConnectionException | GetAllExceptions ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
