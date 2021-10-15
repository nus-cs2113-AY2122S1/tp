package seedu.duke.commands;

//add EXPENDITURE_NAME COST DATE_TIME_OF_EXPENDITURE

import seedu.duke.ui.TextUi;

public class AddBudgetCommand extends AddCommand {
<<<<<<< HEAD

    public static final String MESSAGE_USAGE = ("Adds a budget record.\n"
            + "Parameters: b/ a/AMOUNT m/MONTH [y/YEAR]");

    private final double amount;
    private final int month;

    public AddBudgetCommand(double amount, int month) {
=======
    public static final String MESSAGE_USAGE = ("Adds a budget record.\n"
            + "Parameters: b/ a/AMOUNT m/MONTH [y/YEAR]");
    //public String description;
    public double amount;
    public int month;

    public AddBudgetCommand(double amount) {
        //this.description = description;
>>>>>>> f005599d27bbe7f2d1f7fb66e0cd5172e44fa03f
        this.amount = amount;
        this.month = month;
    }

    public void execute(boolean isLoadingStorage) {
        recordList.addBudget(amount);
        TextUi.showBudgetAddedMessage(amount);
    }
}
