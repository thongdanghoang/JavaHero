package view;

import ctrl.StaffController;
import model.entity.*;

import java.util.Scanner;

public class MainConsole {
    public static Scanner scanner = new Scanner(System.in);
    public static StaffController ctrl = new StaffController();

    public static void main(String[] args) {
        String choose = null;
        boolean exit = true;
        showMenu();
        while (exit) {
            System.out.print("Your choice: ");
            choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    addStaff();
                    break;
                case "2":
                    ctrl.getLsStaffs().forEach(System.out::println);
                    break;
                case "3.1":
                    findLargestSalaryByGender();
                    break;
                case "3.2":
                    findLargestSalaryByPosition();
                    break;
                case "4":
                    exit = false;
                    break;
                default:
            }
        }
    }

    private static void addStaff() {
        Staff newStaff = null;
        System.out.print("Type of staff - officer/worker/manager: ");
        String type = scanner.nextLine();
        System.out.print("Code: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Gender: ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Salary: ");
        float salary = Float.parseFloat(scanner.nextLine());
        switch (type.charAt(0)) {
            case 'w':
                System.out.print("Workdays: ");
                int workday = Integer.parseInt(scanner.nextLine());
                newStaff = new WorkerStaff(id, name, gender, salary, workday);
                break;
            case 'o':
                System.out.print("Salary Coefficient: ");
                float salaryCoeficient = Float.parseFloat(scanner.nextLine());
                newStaff = new OfficerStaff(id, name, gender, salary, salaryCoeficient);
                break;
            case 'm':
                System.out.print("Number of employees: ");
                int nEmployees = Integer.parseInt(scanner.nextLine());
                newStaff = new ManagerStaff(id, name, gender, salary, nEmployees);
                break;
            default:
                System.out.println("Your type of staff is not valid.");
        }

        if (newStaff != null) {
            System.out.println("Add new staff successfully.");
            ctrl.insert(newStaff);
        } else {
            System.out.println("Failure to add new staff.");

        }
    }

    public static void findLargestSalaryByGender() {
        System.out.print("Enter gender you wanna find: ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());
        Staff largestSalaryByGender = ctrl.findLargestSalaryByGender(gender);
        if (largestSalaryByGender != null) {
            System.out.println("largestSalaryByGender = " + largestSalaryByGender);
        }
    }

    public static void findLargestSalaryByPosition() {
        System.out.print("Enter position you wanna find: ");
        String type = scanner.nextLine().toLowerCase();
        Class clType;
        switch (type.charAt(0)) {
            case 'w':
                clType = WorkerStaff.class;
                break;
            case 'o':
                clType = OfficerStaff.class;
                break;
            case 'm':
                clType = ManagerStaff.class;
                break;
            default:
                clType = null;
        }
        Staff largestSalaryByPosition = ctrl.findLargestSalaryByPosition(clType);
        if (largestSalaryByPosition != null) {
            System.out.println("largestSalaryByPosition = " + largestSalaryByPosition);
        }

    }

    private static void showMenu() {
        System.out.println("1 Add a staff(officer/worker/manager).");
        System.out.println("2 Show list of staffs in system.");
        System.out.println("3.1 Find the staff whose largest income by gender.");
        System.out.println("3.2 Find the staff whose largest income by position.");
        System.out.println("4. Exit");
    }
}
