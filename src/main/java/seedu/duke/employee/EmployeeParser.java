package seedu.duke.employee;

import seedu.duke.main.MainUI;

import java.util.logging.*;

public class EmployeeParser {

    private static Logger logger = Logger.getLogger("EmployeeParser");

    public void addEmployee(String[] command, EmployeeList masterList) {
        logger.log(Level.INFO, "going to add employee");
        boolean isEmptyCommand = command[1].stripLeading().stripTrailing().equals("")
                || command[2].stripLeading().stripTrailing().equals("");

        if (isEmptyCommand) {
            MainUI.printWrongCommandMessage();
            logger.log(Level.INFO, "one or more fields left empty");
            logger.log(Level.INFO, "unable to add employee");
            return;
        }

        Employee employee = new Employee(command[1], command[2]);

        masterList.employeeList.add(employee);
        masterList.totalEmployee += 1;

        System.out.println("I have added: ");
        System.out.println(masterList.employeeList.get(masterList.totalEmployee - 1));
        System.out.println("You now have " + masterList.totalEmployee + " employees.");
        MainUI.printSingleLine();
        logger.log(Level.INFO, "end of adding employee");
        assert masterList.totalEmployee >= 0 : "total employee should be equals to or greater than zero";
    }

    public void loadEmployeeFromStorage(EmployeeList masterList, Employee employee) {
        masterList.employeeList.add(employee);
        masterList.totalEmployee += 1;
    }

    public void deleteEmployee(String[] command, EmployeeList masterList) {
        logger.log(Level.INFO, "going to delete employee");
        boolean isEmptyCommand = command[1].stripLeading().stripTrailing().equals("");

        if (isEmptyCommand) {
            MainUI.printWrongCommandMessage();
            logger.log(Level.INFO, "one or more fields left empty");
            logger.log(Level.INFO, "unable to delete employee");
            return;
        }

        int employeeIndex = Integer.parseInt(command[1]) - 1;
        if (masterList.totalEmployee < 1) {
            MainUI.printSingleLine();
            System.out.println("You have no employee.");
            MainUI.printSingleLine();
            return;
        }
        if (employeeIndex < 0 || employeeIndex >= masterList.totalEmployee) {
            MainUI.printSingleLine();
            System.out.println("Invalid input detected.");
            System.out.println("Please include employee index found in \"list-employee\".");
            MainUI.printSingleLine();
            logger.log(Level.INFO, "index from user input for removing employee is out of range");
            return;
        }
        System.out.println("I have deleted: ");
        System.out.println(masterList.employeeList.get(employeeIndex));
        MainUI.printSingleLine();

        masterList.employeeList.remove(employeeIndex);
        masterList.totalEmployee -= 1;
        logger.log(Level.INFO, "end of deleting employee");
        assert masterList.totalEmployee >= 0 : "total employee should be equals to or greater than zero";
    }

    public void listEmployee(EmployeeList masterList) {
        if (masterList.totalEmployee < 1) {
            MainUI.printSingleLine();
            System.out.println("You have no employee.");
            MainUI.printSingleLine();
            return;
        }
        MainUI.printSingleLine();
        System.out.println("Here are the employees in your list:");
        for (int i = 1; i <= masterList.totalEmployee; i += 1) {
            System.out.println(i + " - " + masterList.employeeList.get(i - 1).getName()
                    + " - " + masterList.employeeList.get(i - 1).getPhoneNum());
        }
        MainUI.printSingleLine();
    }

}
