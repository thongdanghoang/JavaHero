package view;

import ctrl.ClazzCtrl;
import ctrl.StudentCtrl;
import ctrl.dto.ClazzDTO;
import ctrl.dto.StudentDTO;
import java.util.Map;
import java.util.Scanner;
import model.dao.exception.DeleteExceptions;
import model.dao.exception.FindByIDExceptions;
import model.dao.exception.GetAllExceptions;
import model.dao.exception.InsertExceptions;
import model.dao.exception.UpdateExceptions;
import model.dbconnection.DBConnectionException;

/**
 *
 * @author thomas
 */
public class Menu {

    private final StudentCtrl studentCtrl = new StudentCtrl();
    private final ClazzCtrl clazzCtrl = new ClazzCtrl();

    private final Scanner SCAN = new Scanner(System.in);
    private String command;

    private final String CREATE_STUDENT = "insert student";
    private final String READ_STUDENT = "read student";
    private final String UPDATE_STUDENT = "update student";
    private final String DELETE_STUDENT = "delete student";

    private final String CREATE_CLASS = "insert class";
    private final String READ_CLASS = "read class";
    private final String UPDATE_CLASS = "update class";
    private final String DELETE_CLASS = "delete class";

    private final String EXIT = "exit";

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
                    createClazz();
                    break;
                case READ_CLASS:
                    readClazz();
                    break;
                case UPDATE_CLASS:
                    updateClazz();
                    break;
                case DELETE_CLASS:
                    deleteClazz();
                    break;
                case EXIT:
                    System.out.println("Exit!!!");
                    System.exit(0);
                default:
                    System.out.println("Command not found");
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
        StudentDTO student = new StudentDTO(classroom, id, name, birthday, gender);
        try {
            if (studentCtrl.insert(student) != null) {
                System.out.println("Insert student successfully");
                System.out.println(student);
            } else {
                System.out.println("Insert student failed");
            }
        } catch (DBConnectionException | InsertExceptions ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void readStudent() {
        try {
            Map<String, StudentDTO> students = studentCtrl.getAll();
            students.values().stream().forEach(System.out::println);
        } catch (DBConnectionException | GetAllExceptions ex) {
            System.err.println(ex.getMessage());
        }
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
        StudentDTO student = new StudentDTO(classroom, id, name, birthday, gender);
        try {
            if (studentCtrl.findById(id) != null) {
                try {
                    if (studentCtrl.updateById(student) != null) {
                        System.out.println("Update student successfully");
                    } else {
                        System.out.println("Update student failure");
                    }
                } catch (UpdateExceptions ex) {
                    System.err.println("Update student error!!!");
                }
            } else {
                System.out.println("Update unsuccessfully because student isn't exist");
            }
        } catch (DBConnectionException | FindByIDExceptions ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void deleteStudent() {
        System.out.print("ID: ");
        String id = SCAN.nextLine();
        try {
            if (studentCtrl.deleteById(id)) {
                System.out.println("Delete student successfully");
            } else {
                System.out.println("Delete student failure");
            }
        } catch (DBConnectionException | DeleteExceptions ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void createClazz() {
        String id, name;
        System.out.print("Class ID: ");
        id = SCAN.nextLine();
        System.out.print("Class name: ");
        name = SCAN.nextLine();
        ClazzDTO clazz = new ClazzDTO(id, name);
        try {
            if (clazzCtrl.insert(clazz) != null) {
                System.out.println("Insert class successfully");
                System.out.println(clazz);
            } else {
                System.out.println("Insert class failure");
            }
        } catch (DBConnectionException | InsertExceptions ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void readClazz() {
        try {
            Map<String, ClazzDTO> clazzes = clazzCtrl.getAll();
            clazzes.values().forEach(System.out::println);
        } catch (DBConnectionException | GetAllExceptions ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void updateClazz() {
        String id, name;
        System.out.print("Class ID: ");
        id = SCAN.nextLine();
        System.out.print("Class name: ");
        name = SCAN.nextLine();
        try {
            if (clazzCtrl.updateById(new ClazzDTO(id, name)) != null) {
                System.out.println("Update class successfully");
            } else {
                System.out.println("Update class failure");
            }
        } catch (DBConnectionException | UpdateExceptions ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void deleteClazz() {
        System.out.print("ID: ");
        String id = SCAN.nextLine();
        try {
            if (clazzCtrl.deleteById(id)) {
                System.out.println("Delete class successfully");
            } else {
                System.out.println("Delete class failure");
            }
        } catch (DBConnectionException | DeleteExceptions ex) {
            System.out.println(ex.getMessage());
        }
    }

}
