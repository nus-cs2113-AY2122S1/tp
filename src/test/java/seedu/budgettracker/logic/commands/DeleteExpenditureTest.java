package seedu.budgettracker.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.Category;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteExpenditureTest {

    @Test
    void deleteExpenditure_expenditureList_sizeOf1() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        Category category = Category.GENERAL;

        RecordList currentExpenditureList = new RecordList(month);
        currentExpenditureList.addExpenditure("TestExpenditure1", 08.00, date, category);
        currentExpenditureList.addExpenditure("TestExpenditure2", 10.00, date, category);
        currentExpenditureList.deleteExpenditure(1);
        assertEquals(1, currentExpenditureList.getExpenditureListSize());
    }
}
