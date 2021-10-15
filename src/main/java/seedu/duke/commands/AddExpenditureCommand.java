package seedu.duke.commands;

import seedu.duke.data.records.Expenditure;
import seedu.duke.ui.TextUi;

import java.time.LocalDate;

/**
 * Adds an Expenditure record to the RecordList.
 */
public class AddExpenditureCommand extends AddCommand {

    public static final String MESSAGE_USAGE = "Adds an expenditure record.\n"
            + "Parameters: e/EXPENDITURE_NAME a/COST [d/DATE_OF_EXPENDITURE]";
    public String description;
    public double spending;
    LocalDate date;

<<<<<<< HEAD
    public static final String MESSAGE_USAGE = "Adds an expenditure record.\n"
            + "Parameters: e/EXPENDITURE_NAME a/COST d/[DATE_OF_EXPENDITURE]\n"
            + "Note: If DATE_OF_EXPENDITURE is not specified, the current system date will be the "
            + "default value.";

=======
>>>>>>> f005599d27bbe7f2d1f7fb66e0cd5172e44fa03f
    /**
     * Constructor for when the user inputs only two parameters, leaving the optional
     * date parameter empty. In that case, the date is set to "today" by default.
     *
     * @param description description of the expenditure, e.g. chicken rice
<<<<<<< HEAD
     * @param amount amount spent for expenditure
     * @param date date on which expenditure was made
=======
     * @param amount      amount spent for expenditure
>>>>>>> f005599d27bbe7f2d1f7fb66e0cd5172e44fa03f
     */
    public AddExpenditureCommand(String description, double amount, LocalDate date) {
        this.description = description;
        this.spending = amount;
        this.date = date;
    }

    /**
     * Adds an expenditure.
     *
     * @param isLoadingStorage boolean to indicate if command is being executed during loading
     *                         or during normal runtime operation
     */
    @Override
    public void execute(boolean isLoadingStorage) {
        Expenditure newExpenditure = new Expenditure(description, spending, date);
        recordList.addExpenditure(description, spending, date, isLoadingStorage);
        TextUi.showExpenditureAddedMessage(newExpenditure, isLoadingStorage);
    }
}
