package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.utility.DataManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataManagerTest {

    @Test
    public void save_validExpenses_correctFileContent() {
        Parser parser = new Parser();
        FinancialTracker financialTracker = new FinancialTracker();
        Ui ui = new Ui();
        financialTracker.addExpense(new Expense("qwe", 12.5));
        financialTracker.addExpense(new Expense("qwe", .5));
        financialTracker.addIncome(new Income("qwe", 12.5));
        financialTracker.addIncome(new Income("qwe", 12.5));
        DataManager dataManager = new DataManager();
        dataManager.save(parser, financialTracker, ui);
    }

    @Test
    public void load_validFileContent_correctEntries() {
        Parser parser = new Parser();
        FinancialTracker financialTracker = new FinancialTracker();
        Ui ui = new Ui();
        DataManager dataManager = new DataManager();
        dataManager.load(parser, financialTracker, ui);
        assertEquals(12.5, financialTracker.listExpenses().get(0).getValue());
        assertEquals("qwe", financialTracker.listExpenses().get(0).getDescription());
        
        assertEquals(.5, financialTracker.listExpenses().get(1).getValue());
        assertEquals("qwe", financialTracker.listExpenses().get(1).getDescription());

        assertEquals(12.5, financialTracker.listIncomes().get(0).getValue());
        assertEquals("qwe", financialTracker.listIncomes().get(0).getDescription());
        
        assertEquals(12.5, financialTracker.listIncomes().get(1).getValue());
        assertEquals("qwe", financialTracker.listIncomes().get(1).getDescription());
        
    }
}
