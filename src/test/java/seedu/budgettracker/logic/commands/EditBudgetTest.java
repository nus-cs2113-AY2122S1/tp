package seedu.budgettracker.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.storage.Storage;

import java.time.LocalDate;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditBudgetTest {
    @Test
    void editBudget_toAmount_expectAmountToChange() {
        AllRecordList recordListNew = new AllRecordList();
        recordListNew.storageDirectory = "data/2019.txt";

        double initialAmount = recordListNew.getBudget(1).getAmount();

        recordListNew.editBudget(1, 13);

        double finalAmount = recordListNew.getBudget(1).getAmount();

        assertTrue(initialAmount != finalAmount);
    }
}
