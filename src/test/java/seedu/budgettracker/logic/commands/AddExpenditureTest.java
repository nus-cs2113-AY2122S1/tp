package seedu.budgettracker.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.Category;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddExpenditureTest {

    @Test
    void addBudget_rawCommand_expenditureList_sizeOf1() {
        String description = "JanuaryExpenditure";
        double spending = 20.00;
        LocalDate date = LocalDate.of(2021, 10, 8);
        int month = date.getMonthValue();
        Category category = Category.GENERAL;

        RecordList currentExpenditureList = new RecordList(month);
        currentExpenditureList.addExpenditure(description, spending, date, category);
        assertEquals(1, currentExpenditureList.getExpenditureListSize());
    }
}
