package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.utility.DataManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataManagerTest {

    @Test
    public void save_validEntries_correctFileContent() {
        Parser parser = new Parser();
        FinancialTracker financialTracker = new FinancialTracker();
        Ui ui = new Ui();
        LocalDate date = LocalDate.parse("2121-11-11", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        financialTracker.addExpense(new Expense("qwe", 12.5, "qwer", date));
        financialTracker.addExpense(new Expense("qwe", .5, "qwer", date));
        financialTracker.addIncome(new Income("qwe", 12.5, "qwer", date));
        financialTracker.addIncome(new Income("qwe", 12.5, "qwer", date));
        DataManager dataManager = new DataManager();
        dataManager.save(parser, financialTracker, ui);
    }

    @Test
    public void load_validFileContent_correctEntries() {
        save_validEntries_correctFileContent();
        Parser parser = new Parser();
        FinancialTracker financialTracker = new FinancialTracker();
        Ui ui = new Ui();
        DataManager dataManager = new DataManager();
        dataManager.load(parser, financialTracker, ui);
        assertEquals(12.5, financialTracker.listExpenses().get(0).getValue());
        assertEquals("qwe", financialTracker.listExpenses().get(0).getDescription());
        assertEquals("qwer", financialTracker.listExpenses().get(0).getCategory());
        
        assertEquals(.5, financialTracker.listExpenses().get(1).getValue());
        assertEquals("qwe", financialTracker.listExpenses().get(1).getDescription());

        assertEquals(12.5, financialTracker.listIncomes().get(0).getValue());
        assertEquals("qwe", financialTracker.listIncomes().get(0).getDescription());
        
        assertEquals(12.5, financialTracker.listIncomes().get(1).getValue());
        assertEquals("qwe", financialTracker.listIncomes().get(1).getDescription());
    }

    @Test
    public void load_invalidFileContent_detectInvalidDataEntriesAndOutputWarningMessages() {
        Parser parser = new Parser();
        FinancialTracker financialTracker = new FinancialTracker();
        Ui ui = new Ui();
        LocalDate date = LocalDate.parse("2121-11-11", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        financialTracker.addExpense(new Expense("qwe", 12.5, "qwer", date));
        financialTracker.addIncome(new Income("qwe", 12.5, "qwer", date));
        financialTracker.addIncome(new Income("", 12.5, "qwer", date));
        DataManager dataManager = new DataManager();
        dataManager.save(parser, financialTracker, ui);
        dataManager.load(parser, financialTracker, ui);
    }
}
