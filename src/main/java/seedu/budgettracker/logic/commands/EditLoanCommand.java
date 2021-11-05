package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Loan;
import seedu.budgettracker.ui.TextUi;

import java.time.LocalDate;

public class EditLoanCommand extends EditCommand {
    public int month;
    public int index;
    public double amount;
    public LocalDate date;
    public String name;

    public EditLoanCommand(int month, int index, double amount, LocalDate date, String name) {
        this.month = month;
        this.index = index;

        this.amount = amount;
        this.date = date;
        this.name = name;
    }

    public void execute() {
        Loan targetLoan = allRecordList.editLoan(month, index, amount, name, date);
        TextUi.showLoanEditedMessage(targetLoan);
    }
}
