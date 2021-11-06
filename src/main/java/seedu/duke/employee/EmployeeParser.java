//@@author kairoskoh

package seedu.duke.employee;

import seedu.duke.main.MainUI;

import java.security.InvalidParameterException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class EmployeeParser {

    private static Logger logger = Logger.getLogger("EmployeeParser");

    public void addEmployee(String[] command, EmployeeList masterList) {
        logger.log(Level.FINE, "going to add employee");

        try {
            Employee employee = new Employee(command[1].stripLeading().stripTrailing(),
                    Integer.parseInt(command[2]),
                    convertToStatus(command[3].stripLeading().stripTrailing()),
                    Integer.parseInt(command[4]));

            masterList.employeeList.add(employee);
            masterList.totalEmployee += 1;

            EmployeeUI.printAddEmployeeMessage(masterList);
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.FINE, "error input: out of bound index");
        } catch (NumberFormatException e) {
            logger.log(Level.FINE, "error input: number format incorrect");
        } catch (InvalidParameterException e) {
            logger.log(Level.FINE, "error input: employee status format incorrect");
        }
        logger.log(Level.FINE, "end of adding employee");
        assert masterList.totalEmployee >= 0 : "total employee should be equals to or greater than zero";
    }

    public void addEmployeeFromStorage(String[] command, EmployeeList masterList) {
        logger.log(Level.FINE, "going to add employee");

        Employee.employmentStatus status = convertToStatus(command[3]);
        Employee employee = new Employee(command[1], Integer.parseInt(command[2]), status, Integer.parseInt(command[4]));

        masterList.employeeList.add(employee);
        masterList.totalEmployee += 1;

        logger.log(Level.FINE, "end of adding employee");
        assert masterList.totalEmployee >= 0 : "total employee should be equals to or greater than zero";
    }

    private Employee.employmentStatus convertToStatus(String input) throws InvalidParameterException {
        logger.log(Level.FINE, "going to convert employee status from string to enum");
        switch (input) {
        case "perm":
        case "PERM":
            return Employee.employmentStatus.PERM;
        case "temp":
        case "TEMP":
            return Employee.employmentStatus.TEMP;
        case "adhoc":
        case "ADHOC":
            return Employee.employmentStatus.ADHOC;
        default:
            throw new InvalidParameterException();
        }
    }

    public void deleteEmployee(String[] command, EmployeeList masterList) {
        logger.log(Level.FINE, "going to delete employee");
        try {

            int employeeIndex = Integer.parseInt(command[1]) - 1;
            if (masterList.totalEmployee < 1) {
                EmployeeUI.printNoEmployeeMessage();
                return;
            }
            EmployeeUI.printRemoveEmployeeMessage(masterList, employeeIndex);

            masterList.employeeList.remove(employeeIndex);
            masterList.totalEmployee -= 1;

        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.FINE, "index from user input for removing employee is out of range");
        }
        logger.log(Level.FINE, "end of deleting employee");
        assert masterList.totalEmployee >= 0 : "total employee should be equals to or greater than zero";
    }

    public void listEmployee(EmployeeList masterList) {
        logger.log(Level.FINE, "going to list employee");
        if (masterList.totalEmployee < 1) {
            EmployeeUI.printNoEmployeeMessage();
            return;
        }
        EmployeeUI.printEmployeeListMessage(masterList);
        logger.log(Level.FINE, "end of listing employee");
    }

}
