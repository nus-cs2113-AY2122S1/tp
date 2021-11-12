//@@author YEOWEIHNGWHYELAB

package seedu.budgettracker.logic.commands;

import java.time.LocalDate;

public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final LocalDate DATE_EMPTY = LocalDate.of(9898, 1, 1);
    public static final int AMOUNT_MAX_VALUE = 1000000000;

    public static final String MESSAGE_USAGE = (EditBudgetCommand.MESSAGE_USAGE
            + System.lineSeparator()
            + EditExpenditureCommand.MESSAGE_USAGE
            + System.lineSeparator()
            + EditLoanCommand.MESSAGE_USAGE);
}
