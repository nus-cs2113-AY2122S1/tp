package seedu.duke.commands;

import seedu.duke.data.records.Expenditure;
import seedu.duke.ui.TextUi;

import java.time.LocalDate;

public class AddExpenditureCommand extends AddCommand {

    public String description;
    public double spending;
    LocalDate date;

    public static final String MESSAGE_USAGE = "Adds an expenditure record.\n"
            + "Parameters: e/EXPENDITURE_NAME a/COST [d/DATE_OF_EXPENDITURE]";

    public AddExpenditureCommand(String description, double amount) {
        this.description = description;
        this.spending = amount;
        //this.date = date;
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        Expenditure newExpenditure = new Expenditure(description, spending);
        recordList.addExpenditure(description, spending, isLoadingStorage);
        TextUi.showExpenditureAddedMessage(newExpenditure);
    }
}
