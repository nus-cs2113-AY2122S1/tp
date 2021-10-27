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
            logger.log(Level.FINE, "end of adding employee");
            assert masterList.totalEmployee >= 0 : "total employee should be equals to or greater than zero";
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.FINE, "error input: out of bound index");
        } catch (NumberFormatException e) {
            logger.log(Level.FINE, "error input: number format incorrect");
        } catch (InvalidParameterException e) {
            logger.log(Level.FINE, "error input: employee status format incorrect");
        }

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

    private Employee.employmentStatus convertToStatus(String input) throws InvalidParameterException{
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
        boolean isEmptyCommand = command[1].stripLeading().stripTrailing().equals("");

        if (isEmptyCommand) {
            MainUI.printWrongCommandMessage();
            logger.log(Level.FINE, "one or more fields left empty");
            logger.log(Level.FINE, "unable to delete employee");
            return;
        }

        int employeeIndex = Integer.parseInt(command[1]) - 1;
        if (masterList.totalEmployee < 1) {
            EmployeeUI.printNoEmployeeMessage();
            return;
        }
        if (employeeIndex < 0 || employeeIndex >= masterList.totalEmployee) {
            EmployeeUI.printInvalidRemoveMessage();
            logger.log(Level.FINE, "index from user input for removing employee is out of range");
            return;
        }
        EmployeeUI.printRemoveEmployeeMessage(masterList, employeeIndex);

        masterList.employeeList.remove(employeeIndex);
        masterList.totalEmployee -= 1;

        logger.log(Level.FINE, "end of deleting employee");
        assert masterList.totalEmployee >= 0 : "total employee should be equals to or greater than zero";
    }

    public void listEmployee(EmployeeList masterList) {
        if (masterList.totalEmployee < 1) {
            EmployeeUI.printNoEmployeeMessage();
            return;
        }
        EmployeeUI.printEmployeeListMessage(masterList);
    }

}
