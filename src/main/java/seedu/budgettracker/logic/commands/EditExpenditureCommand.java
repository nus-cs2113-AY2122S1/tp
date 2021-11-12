package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

import java.time.LocalDate;

import static seedu.budgettracker.common.Messages.MESSAGE_AMOUNT_EXCEEDED;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_INDEX_OF_EXPENDITURE;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_YEAR;
import static seedu.budgettracker.common.Messages.MESSAGE_AMOUNT_ZERO_OR_NEGATIVE;

//@@author jyxhazcake
/**
 * Command that edits the target expenditure in the record list.
 */
public class EditExpenditureCommand extends EditCommand {

    public int month;
    public int index;
    public double amount;
    public LocalDate date;
    public String description;
    public Category category;

    public static final String MESSAGE_USAGE = "Edits an expenditure record.\n"
            + "Parameters: -e m/MONTH i/INDEX [a/AMOUNT] [d/DATE_OF_EXPENDITURE] [n/DESCRIPTION] [c/CATEGORY]\n"
            + "Note:\n"
            + " * AMOUNT, DATE_OF_EXPENDITURE, DESCRIPTION and CATEGORY are optional"
            + " but at least one must exist to edit.\n";

    public EditExpenditureCommand(int month, int index, double amount, LocalDate date,
                                  String description, Category category) {
        this.month = month;
        this.index = index;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
    }

    /**
     * Sets new parameters for the target expenditure in the record list.
     *
     * @throws CommandException if parameters are invalid
     */
    public void execute() throws CommandException {
        checkValidParams();
        Expenditure targetExpenditure = allRecordList.editExpenditure(month, index,
                amount, description, date, category);
        TextUi.showExpenditureEditedMessage(targetExpenditure, allRecordList);
    }

    /**
     * Checks the parameters if they valid.
     *
     * @throws CommandException if parameters are invalid
     */
    private void checkValidParams() throws CommandException {
        if (amount <= 0) {
            throw new CommandException(MESSAGE_AMOUNT_ZERO_OR_NEGATIVE);
        }
        if (amount > AMOUNT_MAX_VALUE) {
            throw new CommandException(MESSAGE_AMOUNT_EXCEEDED);
        }
        if (index < 0 || index >= allRecordList.getExpenditureListSize(month)) {
            throw new CommandException(MESSAGE_INVALID_INDEX_OF_EXPENDITURE);
        }
        int currentYear = allRecordList.getYear();
        if (date.getYear() != currentYear && !date.isEqual(DATE_EMPTY)) {
            throw new CommandException(MESSAGE_INVALID_YEAR);
        }
    }
}
