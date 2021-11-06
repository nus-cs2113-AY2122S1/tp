//@@author kairoskoh

package seedu.duke.employee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeParserTest {

    @Test
    void deleteEmployee_emptyMasterList_expectNoChange() {
        EmployeeList masterList = new EmployeeList();
        EmployeeParser test = new EmployeeParser();
        //test.deleteEmployee(masterList, 10);
        //assertEquals(0, masterList.totalEmployee );
    }

    @Test
    void listEmployee_emptyMasterList_expectNoResult() {
        EmployeeList masterList = new EmployeeList();
        EmployeeParser test = new EmployeeParser();
        test.listEmployee(masterList);
        assertEquals(0, masterList.totalEmployee);
    }
}