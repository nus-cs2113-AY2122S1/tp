//@@author kairoskoh

package seedu.duke.employee;

import seedu.duke.main.MainUI;

import java.sql.SQLOutput;

/**
 * Prints messages to interact with the users.
 */
public class EmployeeUI {

    public static void printNoEmployeeMessage() {
        MainUI.printSingleLine();
        System.out.println(" You have no employee.");
        MainUI.printSingleLine();
    }

    public static void printAddEmployeeMessage(EmployeeList masterList) {
        MainUI.printSingleLine();
        System.out.println(" I have added: ");
        System.out.println("   " + masterList.employeeList.get(masterList.totalEmployee - 1));
        System.out.println(" You now have " + masterList.totalEmployee + " employees.");
        MainUI.printSingleLine();
    }

    public static void printInvalidRemoveMessage() {
        MainUI.printSingleLine();
        System.out.println(" Invalid input detected.");
        System.out.println(" Please include employee index found in \"list-employee\".");
        MainUI.printSingleLine();
    }

    public static void printRemoveEmployeeMessage(EmployeeList masterList, int employeeIndex) {
        MainUI.printSingleLine();
        System.out.println(" I have deleted: ");
        System.out.println("   " + masterList.employeeList.get(employeeIndex));
        MainUI.printSingleLine();
    }

    public static void printEmployeeListMessage(EmployeeList masterList) {
        MainUI.printSingleLine();
        System.out.println(" Here are the employees in your list:");
        for (int i = 1; i <= masterList.totalEmployee; i += 1) {
            System.out.println("   " + i + ". " + masterList.employeeList.get(i - 1).getName()
                    + " - " + masterList.employeeList.get(i - 1).getPhoneNum()
                    + " - " + masterList.employeeList.get(i - 1).getStatus() + " STAFF"
                    + " - " + "$" + masterList.employeeList.get(i - 1).getSalary());
        }
        MainUI.printSingleLine();
    }

    public static void printInvalidEmploymentStatusMessage() {
        MainUI.printSingleLine();
        System.out.println("Invalid employment status.");
        System.out.println("Please use one of the three options: perm, temp or adhoc.");
        MainUI.printSingleLine();
    }
}
