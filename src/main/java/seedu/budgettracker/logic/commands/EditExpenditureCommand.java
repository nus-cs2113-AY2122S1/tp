package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.ui.TextUi;

import java.time.LocalDate;

import static seedu.budgettracker.common.Messages.MESSAGE_AMOUNT_EXCEEDED;

public class EditExpenditureCommand extends EditCommand {
    public int month;
    public int index;
    public double amount;
    public LocalDate date;
    public String description;

    public static final String MESSAGE_USAGE = "Edits an expenditure record.\n"
            + "Parameters: -e m/MONTH i/INDEX [a/AMOUNT] [d/DATE_OF_EXPENDITURE] [n/DESCRIPTION]\n"
            + "Note:\n"
            + " * If DATE_OF_EXPENDITURE is not specified, current system date will be the default value.\n"
            + " * If CATEGORY is not specified, GENERAL will be the default category.\n";

    public EditExpenditureCommand(int month, int index, double amount, LocalDate date, String description) {
        this.month = month;
        this.index = index;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public void execute() throws CommandException {
        if (amount > 1000000000) {
            throw new CommandException(MESSAGE_AMOUNT_EXCEEDED);
        }
        Expenditure targetExpenditure = allRecordList.editExpenditure(month, index,
                amount, description, date);
        TextUi.showExpenditureEditedMessage(targetExpenditure, allRecordList);
    }
}
