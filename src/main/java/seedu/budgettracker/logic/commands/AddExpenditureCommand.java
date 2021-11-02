package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.data.records.Expenditure;
import seedu.budgettracker.ui.TextUi;

import java.time.LocalDate;

/**
 * Adds an Expenditure record to the RecordList.
 */
public class AddExpenditureCommand extends AddCommand {

    public static final boolean IS_NOT_LOADING_STORAGE = false;
    private final String description;
    private final double spending;
    private final LocalDate date;
    private final Category category;



    public static final String MESSAGE_USAGE = "Adds an expenditure record.\n"
            + "Parameters: -e n/EXPENDITURE_NAME a/COST d/[DATE_OF_EXPENDITURE] c/[CATEGORY]\n"
            + "Note:\n"
            + " * If DATE_OF_EXPENDITURE is not specified, current system date will be the default value.\n"
            + " * If CATEGORY is not specified, GENERAL will be the default category.\n";

    /**
     * Constructor for when the user inputs only two parameters, leaving the optional
     * date parameter empty. In that case, the date is set to "today" by default.
     *
     * @param description description of the expenditure, e.g. chicken rice
     * @param amount amount spent for expenditure
     * @param date date on which expenditure was made
     * @param category category which expenditure falls under
     */
    public AddExpenditureCommand(String description, double amount, LocalDate date, Category category) {
        this.description = description;
        this.spending = amount;
        this.date = date;
        this.category = category;
    }

    public void execute() {
        Expenditure addedExpenditure = allRecordList.addExpenditure(description,
                spending,
                date,
                category,
                IS_NOT_LOADING_STORAGE);
        TextUi.showExpenditureAddedMessage(addedExpenditure, IS_NOT_LOADING_STORAGE, allRecordList);
    }


    /**
     * Adds an expenditure.
     *
     * @param isLoadingStorage boolean to indicate if command is being executed during loading
     *                         or during normal runtime operation
     */
    @Override
    public void execute(boolean isLoadingStorage) {
        Expenditure newExpenditure = new Expenditure(description, spending, date, category);
        allRecordList.addExpenditure(description, spending, date, category, isLoadingStorage);
        TextUi.showExpenditureAddedMessage(newExpenditure, isLoadingStorage, allRecordList);
    }
}
