package seedu.budgettracker.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.Category;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewExpenditureTest {
    @Test
    void viewExpenditure_expenditureList_sizeOf1() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        Category category = Category.GENERAL;

        RecordList currentExpenditureList = new RecordList(month);
        currentExpenditureList.addExpenditure("TestExpenditure1", 08.00, date, category);
        currentExpenditureList.addExpenditure("TestExpenditure2", 10.00, date, category);
        //currentExpenditureList.getExpenditureList(1, 2);
        assertEquals(2, currentExpenditureList.getExpenditureListSize());
    }
}
