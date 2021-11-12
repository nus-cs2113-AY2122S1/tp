package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Loan;
import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

import java.time.LocalDate;

import static seedu.budgettracker.common.Messages.MESSAGE_AMOUNT_EXCEEDED;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_INDEX_OF_EXPENDITURE;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_INDEX_OF_LOAN;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_YEAR;
import static seedu.budgettracker.common.Messages.MESSAGE_AMOUNT_ZERO_OR_NEGATIVE;
//@@author jyxhazcake

/**
 * Command that edits a loan in the record list.
 */
public class EditLoanCommand extends EditCommand {
    public int month;
    public int index;
    public double amount;
    public LocalDate date;
    public String name;

    public static final String MESSAGE_USAGE = "Edits a loan record.\n"
            + "Parameters: -l m/MONTH i/INDEX [a/AMOUNT] [d/DATE_OF_LOAN] [n/BORROWER_NAME]\n"
            + "Note:\n"
            + " * AMOUNT, DATE_OF_LOAN and BORROWER_NAME are optional, but at least one must exist to edit.\n";

    public EditLoanCommand(int month, int index, double amount, LocalDate date, String name) {
        this.month = month;
        this.index = index;
        this.amount = amount;
        this.date = date;
        this.name = name;
    }

    /**
     * Sets new parameters for the target loan in the record list.
     *
     * @throws CommandException if parameters are invalid
     */
    public void execute() throws CommandException {
        checkValidParams();
        Loan targetLoan = allRecordList.editLoan(month, index, amount, name, date);
        TextUi.showLoanEditedMessage(targetLoan);
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
        if (index < 0 || index >= allRecordList.getLoanListSize(month)) {
            throw new CommandException(MESSAGE_INVALID_INDEX_OF_LOAN);
        }
        int currentYear = allRecordList.getYear();
        if (date.getYear() != currentYear && !date.isEqual(DATE_EMPTY)) {
            throw new CommandException(MESSAGE_INVALID_YEAR);
        }
    }
}
