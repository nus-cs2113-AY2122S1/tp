package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Expenditure;

import java.time.LocalDate;

public class EditExpenditureCommand extends EditCommand {
    public int month;
    public int index;
    public double amount;
    public LocalDate date;
    public String description;

    public EditExpenditureCommand(int month, int index, double amount, LocalDate date, String description) {
        this.month = month;
        this.index = index;

        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public void execute(boolean isLoadingStorage) {
        Expenditure targetExpenditure = allRecordList.editExpenditure(month, index,
                amount, description, date, isLoadingStorage);

        System.out.println("Expenditure has been successfully edited!");
        System.out.println("New values: " + targetExpenditure);
    }
}
