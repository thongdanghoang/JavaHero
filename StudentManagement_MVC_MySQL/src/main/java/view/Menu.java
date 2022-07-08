package view;

import ctrl.ClassroomCtrl;
import ctrl.StudentCtrl;
import ctrl.dto.StudentDTO;
import java.util.Map;
import java.util.Scanner;
import model.dao.exception.DeleteExceptions;
import model.dao.exception.FindByIDExceptions;
import model.dao.exception.InsertExceptions;
import model.dao.exception.UpdateExceptions;
import model.dbconnection.DBConnectionException;

/**
 *
 * @author thomas
 */
public class Menu {

    private StudentCtrl studentCtrl = new StudentCtrl();
    private ClassroomCtrl classroomCtrl = new ClassroomCtrl();

    private final Scanner SCAN = new Scanner(System.in);
    private String command;

    private final String CREATE_STUDENT = "create student";
    private final String READ_STUDENT = "read student";
    private final String UPDATE_STUDENT = "update student";
    private final String DELETE_STUDENT = "delete student";

    private final String CREATE_CLASS = "create class";
    private final String READ_CLASS = "read class";
    private final String UPDATE_CLASS = "update class";
    private final String DELETE_CLASS = "delete class";

    private void run() {
        printMenu();
        while (true) {
            System.out.print("system:~$ ");
            command = SCAN.nextLine();
            switch (command) {
                case CREATE_STUDENT:
                    createStudent();
                    break;
                case READ_STUDENT:
                    readStudent();
                    break;
                case UPDATE_STUDENT:
                    updateStudent();
                    break;
                case DELETE_STUDENT:
                    deleteStudent();
                    break;
                case CREATE_CLASS:
                    break;
                case READ_CLASS:
                    break;
                case UPDATE_CLASS:
                    break;
                case DELETE_CLASS:
                    break;
                default:
                    System.out.println("Exit!!!");
                    System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new Menu().run();
    }

    public void printMenu() {
    }

    private void createStudent() {
        String id, classroom, name, birthday, gender;
        System.out.print("ID: ");
        id = SCAN.nextLine();
        System.out.print("Classroom: ");
        classroom = SCAN.nextLine();
        System.out.print("Name: ");
        name = SCAN.nextLine();
        System.out.print("Birthday (dd/MM/yyyy): ");
        birthday = SCAN.nextLine();
        System.out.print("Gender: ");
        gender = SCAN.nextLine();
        StudentDTO student = new StudentDTO(id, classroom, name, birthday, gender);
        try {
            if (studentCtrl.insert(student) != null) {
                System.out.println("Insert Successfully");
                System.out.println(student);
            } else {
                System.out.println("Insert Failed");
            }
        } catch (DBConnectionException | InsertExceptions ex) {
            System.err.println("Insert error!!!");
        }
    }

    private void readStudent() {
        Map<String, StudentDTO> students = studentCtrl.getAll();
        students.values().stream().forEach(System.out::println);
    }

    private void updateStudent() {
        String id, classroom, name, birthday, gender;
        System.out.print("ID: ");
        id = SCAN.nextLine();
        System.out.print("Classroom: ");
        classroom = SCAN.nextLine();
        System.out.print("Name: ");
        name = SCAN.nextLine();
        System.out.print("Birthday (dd/MM/yyyy): ");
        birthday = SCAN.nextLine();
        System.out.print("Gender: ");
        gender = SCAN.nextLine();
        StudentDTO student = new StudentDTO(id, classroom, name, birthday, gender);
        try {
            if (studentCtrl.findById(id) != null) {
                try {
                    if (studentCtrl.updateById(student) != null) {
                        System.out.println("Update successfully");
                    } else {
                        System.out.println("Update failure");
                    }
                } catch (UpdateExceptions ex) {
                    System.err.println("Update student error!!!");
                }
            } else {
                System.out.println("Update unsuccessfully because student isn't exist");
            }
        } catch (DBConnectionException | FindByIDExceptions ex) {
            System.err.println("Find student error!!!");
        }
    }

    private void deleteStudent() {
        System.out.print("ID: ");
        String id = SCAN.nextLine();
        try {
            if (studentCtrl.deleteById(id)) {
                System.out.println("Delete successfully");
            } else {
                System.out.println("Delete unsuccessfully");
            }
        } catch (DBConnectionException | DeleteExceptions ex) {
            System.out.println("Delete student error!\n".concat(ex.getMessage()));
        }
    }
    
    
}
