package seedu.duke.commands;

import seedu.duke.data.records.Expenditure;
import seedu.duke.ui.TextUi;

import java.time.LocalDate;

public class AddExpenditureCommand extends AddCommand {

    public String description;
    public double spending;
    LocalDate date;

    public AddExpenditureCommand(String description, double amount) {
        this.description = description;
        this.spending = amount;
        //this.date = date;
    }


    public void execute(boolean isLoadingStorage) {
        Expenditure newExpenditure = new Expenditure(description, spending);
        recordList.addExpenditure(description, spending, isLoadingStorage);
        TextUi.showExpenditureAddedMessage(newExpenditure);
    }
}
