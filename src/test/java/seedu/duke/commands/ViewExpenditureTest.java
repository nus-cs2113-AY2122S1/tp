package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.RecordList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewExpenditureTest {
    @Test
    void deleteExpenditure_expenditureList_sizeOf1() {
        RecordList currentExpenditureList = new RecordList();
        currentExpenditureList.addExpenditure("TestExpenditure1", 08.00, 01);
        currentExpenditureList.addExpenditure("TestExpenditure2", 10.00, 02);
        currentExpenditureList.getExpenditureList(1, 2);
        assertEquals(2, currentExpenditureList.getSize());
    }
}
