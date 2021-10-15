package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.RecordList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteExpenditureTest {

    @Test
    void deleteExpenditure_expenditureList_sizeOf1() {
        RecordList currentExpenditureList = new RecordList();
        currentExpenditureList.addExpenditure("TestExpenditure1", 08.00, false);
        currentExpenditureList.addExpenditure("TestExpenditure2", 10.00, false);
        currentExpenditureList.deleteExpenditure(1);
        assertEquals(1, currentExpenditureList.getExpenditureListSize());
    }
}
