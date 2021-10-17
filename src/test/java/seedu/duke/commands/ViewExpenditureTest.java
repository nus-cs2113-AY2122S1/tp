package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.AllRecordList;
import seedu.duke.data.RecordList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewExpenditureTest {
    @Test
    void viewExpenditure_expenditureList_sizeOf1() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();

        AllRecordList currentExpenditureList = new AllRecordList();
        currentExpenditureList.addExpenditure("TestExpenditure1", 08.00, date, false);
        currentExpenditureList.addExpenditure("TestExpenditure2", 10.00, date,false);
        //currentExpenditureList.getExpenditureList(1, 2);
        assertEquals(2, currentExpenditureList.getExpenditureListSize(month));
    }
}
