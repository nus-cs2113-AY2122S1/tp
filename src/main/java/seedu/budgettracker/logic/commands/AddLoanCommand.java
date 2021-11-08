package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Loan;
import seedu.budgettracker.ui.TextUi;

import java.time.LocalDate;

//@@author jyxhazcake

/**
 * Command which adds a loan to the record list.
 */
public class AddLoanCommand extends AddCommand {
    public String name;
    public double amount;
    LocalDate date;

    public static final String MESSAGE_USAGE = "Adds a loan record.\n"
            + "Parameters: -l n/BORROWER_NAME a/AMOUNT d/[DATE_OF_LOAN]\n"
            + "Note: \n"
            + " * If DATE_OF_LOAN is not specified, the current system date will be the default value.";

    /**
     * Constructor for when the user inputs only two parameters, leaving the optional
     * date parameter empty. In that case, the date is set to "today" by default.
     *
     * @param name name of the borrower
     * @param amount amount loaned out
     * @param date date on which the loan was made
     */
    public AddLoanCommand(String name, double amount, LocalDate date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public void execute() {
        Loan newLoan = new Loan(name, amount, date);
        allRecordList.addLoan(name, amount, date, false);
        TextUi.showLoanAddedMessage(newLoan, false);
    }

    /**
     * Adds a loan to the record list.
     *
     * @param isLoadingStorage boolean to indicate if command is being executed during loading
     *                         or during normal runtime operation
     */
    @Override
    public void execute(boolean isLoadingStorage) {
        Loan newLoan = new Loan(name, amount, date);
        allRecordList.addLoan(name, amount, date, isLoadingStorage);
        TextUi.showLoanAddedMessage(newLoan, isLoadingStorage);
    }
}
