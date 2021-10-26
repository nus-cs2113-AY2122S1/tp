package seedu.duke.commands;

import java.time.LocalDate;

public class EditExpenditureCommand extends EditCommand {
    public int month;
    public int index;
    public double amount;
    public LocalDate date;
    public String description;

    public EditExpenditureCommand(int month, int index, double amount, LocalDate date, String description) {
        this.month = month;
        this.amount = amount;
        this.index = index;
        this.date = date;
        this.description = description;
    }

    public void execute(boolean isLoadingStorage) {
        // recordList.editExpenditure(this.month, this.amount); // Not done yet
    }
}
