//@@author kairoskoh

package seedu.duke.employee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeParserTest {

    @Test
    void deleteEmployee_emptyMasterList_expectNoChange() {
        EmployeeList masterList = new EmployeeList();
        EmployeeParser test = new EmployeeParser();
        String[] command = { "remove-employee", "10" };
        test.deleteEmployee(command, masterList);
        assertEquals(0, masterList.totalEmployee);
    }

    @Test
    void listEmployee_emptyMasterList_expectNoResult() {
        EmployeeList masterList = new EmployeeList();
        EmployeeParser test = new EmployeeParser();
        test.listEmployee(masterList);
        assertEquals(0, masterList.totalEmployee);
    }

    @Test
    void compareEmployee_emptyMasterList_expectFalse() {
        Employee employee = new Employee("name", 12345678, Employee.EmploymentStatus.PERM, 1000);
        EmployeeList masterList = new EmployeeList();
        EmployeeParser test = new EmployeeParser();
        masterList.employeeList.add(employee);
        assertTrue(test.isDuplicateEmployee(employee, masterList, 0));
    }

    @Test
    void checkSalary_positiveIntegerString_expectInteger() {
        String num = "123";
        int number = 123;
        EmployeeParser test = new EmployeeParser();
        assertEquals(number, test.checkSalary(num));
    }

    @Test
    void checkNumber_positiveIntegerString_expectInteger() {
        String num = "100";
        int number = 100;
        EmployeeParser test = new EmployeeParser();
        assertEquals(number, test.checkSalary(num));
    }

    @Test
    void convertToStatus_stringPerm_enumEmploymentStatus() {
        EmployeeParser test = new EmployeeParser();
        assertEquals(Employee.EmploymentStatus.PERM, test.convertToStatus("perm"));
    }

    @Test
    void convertToStatus_stringTemp_enumEmploymentStatus() {
        EmployeeParser test = new EmployeeParser();
        assertEquals(Employee.EmploymentStatus.TEMP, test.convertToStatus("temp"));
    }

    @Test
    void convertToStatus_stringAdhoc_enumEmploymentStatus() {
        EmployeeParser test = new EmployeeParser();
        assertEquals(Employee.EmploymentStatus.ADHOC, test.convertToStatus("adhoc"));
    }

}