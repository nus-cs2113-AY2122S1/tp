//@@author kairoskoh

package seedu.duke.employee;

import seedu.duke.main.MainUI;

import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Processes the different commands which interacts with the list of employees.
 * Enables users to add, remove and list employees.
 */
public class EmployeeParser {

    private static Logger logger = Logger.getLogger("EmployeeParser");

    /**
     * Adds a single employee into the list of employees.
     * Requires critical information of the employee to be added such as name, phone number,
     * employment status and salary.
     *
     * @param command contains the input from users.
     * @param masterList references to the list of employees where changes will be made.
     */
    public void addEmployee(String[] command, EmployeeList masterList) {
        logger.log(Level.FINE, "going to add employee");

        try {
            Employee employee = new Employee(command[1].stripLeading().stripTrailing(),
                    checkNumber(command[2].stripLeading().stripTrailing()),
                    convertToStatus(command[3].stripLeading().stripTrailing()),
                    checkSalary(command[4].stripLeading().stripTrailing()) );

            checkDuplicate(employee, masterList);

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

    /**
     * Checks for duplicate entries by comparing user input with every single existing employee.
     *
     * @param employee contains the user input for the new employee.
     * @param masterList references to the list of employees where changes will be made
     */
    private void checkDuplicate(Employee employee, EmployeeList masterList) {
        for(int i = 0; i < masterList.totalEmployee; i += 1) {
            compareEmployee(employee, masterList, i);
        }
    }

    /**
     * Checks for duplicate entries by comparing user input with a single employee.
     *
     * @param employee contains the user input for the new employee.
     * @param masterList references to the list of employees where changes will be made
     * @param i refers to the index of the current employee in the existing list that the
     *          new employee is being compared to.
     */
    private void compareEmployee(Employee employee, EmployeeList masterList, int i) {
        if (Objects.equals(employee.getName(), masterList.employeeList.get(i).getName())) {
            if (Objects.equals(employee.getPhoneNum(), masterList.employeeList.get(i).getPhoneNum())) {
                if (Objects.equals(employee.getStatus(), masterList.employeeList.get(i).getStatus())) {
                    if(Objects.equals(employee.getSalary(), masterList.employeeList.get(i).getSalary())) {
                        EmployeeUI.printDuplicateEntryMessage(i);
                        throw new InvalidParameterException();
                    }
                }
            }
        }
    }

    /**
     * Checks if the format for salary is valid - must be a positive integer.
     * If format is valid, string format is converted into integer format.
     *
     * @param number contains the user input for salary.
     * @return the user input in integer format.
     * @throws NumberFormatException If input from user is invalid.
     */
    private int checkSalary(String number) {
        int num = Integer.parseInt(number);
        if (num <= 0) {
            EmployeeUI.printInvalidSalaryMessage();
            throw new NumberFormatException();
        }
        return num;
    }

    /**
     * Checks if the format for phone number is valid - must be a positive integer.
     * If format is valid, string format is converted into integer format.
     *
     * @param number contains the user input for phone number.
     * @return the user input in integer format.
     * @throws NumberFormatException If input from user is invalid.
     */
    private int checkNumber(String number) {
        int num = Integer.parseInt(number);
        if (num <= 0) {
            EmployeeUI.printInvalidPhoneNumberMessage();
            throw new NumberFormatException();
        }
        return num;
    }

    /**
     * Adds a single employee into the list of employees - from Duke.txt (storage location).
     * Requires critical information of the employee to be added such as name, phone number,
     * employment status and salary.
     *
     * @param command contains the input from storage.
     * @param masterList references to the list of employees where changes will be made.
     */
    public void addEmployeeFromStorage(String[] command, EmployeeList masterList) {
        logger.log(Level.FINE, "going to add employee");

        Employee.employmentStatus status = convertToStatus(command[3]);
        Employee employee = new Employee(command[1], Integer.parseInt(command[2]), status, Integer.parseInt(command[4]));

        masterList.employeeList.add(employee);
        masterList.totalEmployee += 1;

        logger.log(Level.FINE, "end of adding employee");
        assert masterList.totalEmployee >= 0 : "total employee should be equals to or greater than zero";
    }

    /**
     * Converts employee status from string format to enum.
     * Employment status can only be perm, temp or adhoc, as specified in Employee.java.
     *
     * @param input contains the employment status specified by the users.
     * @return enum of employment status.
     * @throws InvalidParameterException If input from user is invalid.
     */
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
            EmployeeUI.printInvalidEmploymentStatusMessage();
            throw new InvalidParameterException();
        }
    }

    /**
     * Deletes a single employee from the list of employees.
     * Requires information on which employee to remove, which must be specified by user.
     *
     * @param command contains the input from storage.
     * @param masterList references to the list of employees where changes will be made.
     */
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

    /**
     * List all the employees in the list and their respective information.
     * Information that are listed includes: name, phone number, employment status and salary.
     *
     * @param masterList references to the list of employees where changes will be made.
     */
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
