package seedu.duke.commands;

import seedu.duke.data.records.Expenditure;
import seedu.duke.ui.TextUi;

import java.time.LocalDate;

/**
 * Adds an Expenditure record to the RecordList.
 */
public class AddExpenditureCommand extends AddCommand {

    public String description;
    public double spending;
    LocalDate date;

    public static final String MESSAGE_USAGE = "Adds an expenditure record.\n"
            + "Parameters: e/EXPENDITURE_NAME a/COST [d/DATE_OF_EXPENDITURE]";

    /**
     * Constructor for when the user inputs only two parameters, leaving the optional
     * date parameter empty. In that case, the date is set to "today" by default.
     *
     * @param description description of the expenditure, e.g. chicken rice
     * @param amount amount spent for expenditure
     */
    public AddExpenditureCommand(String description, double amount) {
        this.description = description;
        this.spending = amount;
        //this.date = date;
    }

    /**
     * Adds an expenditure.
     *
     * @param isLoadingStorage boolean to indicate if command is being executed during loading
     *                         or during normal runtime operation
     */
    @Override
    public void execute(boolean isLoadingStorage) {
        Expenditure newExpenditure = new Expenditure(description, spending);
        recordList.addExpenditure(description, spending, isLoadingStorage);
        TextUi.showExpenditureAddedMessage(newExpenditure, isLoadingStorage);
    }
}
