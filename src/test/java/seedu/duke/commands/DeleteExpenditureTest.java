package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.AllRecordList;
import seedu.duke.data.RecordList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteExpenditureTest {

    @Test
    void deleteExpenditure_expenditureList_sizeOf1() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();

        RecordList currentExpenditureList = new RecordList(month);
        currentExpenditureList.addExpenditure("TestExpenditure1", 08.00, date, false);
        currentExpenditureList.addExpenditure("TestExpenditure2", 10.00, date,false);
        currentExpenditureList.deleteExpenditure(1);
        assertEquals(1, currentExpenditureList.getExpenditureListSize());
    }
}
