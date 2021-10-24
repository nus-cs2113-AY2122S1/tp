package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.AllRecordList;
import seedu.duke.data.RecordList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBudgetTest {

    @Test
    void addBudget_rawCommand_budgetListAmount_20() {
        double spendingLimit = 20.00;
        int month = 12;
        RecordList currentBudgetList = new RecordList(month);
        currentBudgetList.addBudget(spendingLimit, month, false);
        assertEquals(20.00, currentBudgetList.getBudget().getAmount());
    }

    
}
