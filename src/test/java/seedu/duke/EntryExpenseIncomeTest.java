package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.model.entries.Expense;
import seedu.duke.model.entries.ExpenseCategory;
import seedu.duke.model.entries.Income;
import seedu.duke.model.entries.IncomeCategory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryExpenseIncomeTest {
    @Test
    void setCategoryOfExpense_validCategory_returnOverwrittenCategory() {
        Expense expense = new Expense("test", LocalDate.now(), 10, ExpenseCategory.FOOD);
        Expense constructor = new Expense(expense);
        constructor.setCategory(ExpenseCategory.GIFT);
        assertEquals(constructor.getCategory(), ExpenseCategory.GIFT);
    }

    @Test
    void setCategoryOfIncome_validCategory_returnOverwrittenCategory() {
        Income income = new Income("mcdonald", LocalDate.now(), 10, IncomeCategory.WAGES);
        Income constructor = new Income(income);
        constructor.setCategory(IncomeCategory.GIFT);
        assertEquals(constructor.getCategory(), IncomeCategory.GIFT);
    }
}
