package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.AllRecordList;
import seedu.duke.data.RecordList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBudgetTest {
    //    @Test
    //    void getDescription_rawCommand_expectDescription() {
    //        //String inputCommand = "AddBudget JanuaryBudget 20.00 12";
    //        AddBudgetCommand currentBudget = new AddBudgetCommand();
    //        assertEquals("JanuaryBudget", currentBudget.getDescription("AddBudget JanuaryBudget 20.00 12"));
    //    }
    //
    //    @Test
    //    void getAmount_rawCommand_expectAmount() {
    //        //String inputCommand = "AddBudget JanuaryBudget 20.00 12";
    //        AddBudgetCommand currentBudget = new AddBudgetCommand();
    //        assertEquals(20.00, currentBudget.getAmount("AddBudget JanuaryBudget 20.00 12"));
    //    }
    //
    //    @Test
    //    void getMonth_rawCommand_expectMonth() {
    //        //String inputCommand = "AddBudget JanuaryBudget 20.00 12";
    //        AddBudgetCommand currentBudget = new AddBudgetCommand();
    //        assertEquals(12, currentBudget.getMonth("AddBudget JanuaryBudget 20.00 12"));
    //    }

    @Test
    void addBudget_rawCommand_budgetListAmount_20() {
        double spendingLimit = 20.00;
        int month = 12;
        RecordList currentBudgetList = new RecordList(month);
        currentBudgetList.addBudget(spendingLimit, month, false);
        assertEquals(20.00, currentBudgetList.getBudget().getAmount());
    }
}
