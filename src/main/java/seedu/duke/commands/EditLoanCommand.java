package seedu.duke.commands;

import seedu.duke.data.records.Expenditure;
import seedu.duke.data.records.Loan;

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

    public void execute(boolean isLoadingStorage) {
        Loan targetLoan = allRecordList.editLoan(month, index, amount, name, date, isLoadingStorage);
        System.out.println("Loan has been successfully edited!");
        System.out.println("New values: " + targetLoan);
    }
}
