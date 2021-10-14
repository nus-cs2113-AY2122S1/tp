package seedu.duke.commands;

//add EXPENDITURE_NAME COST DATE_TIME_OF_EXPENDITURE

public class AddBudgetCommand extends AddCommand {
    //public String description;
    public double amount;
    public int month;

    public static final String MESSAGE_USAGE = ("Adds a budget record.\n"
            + "Parameters: b/ a/AMOUNT m/MONTH [y/YEAR]");

    public AddBudgetCommand(double amount) {
        //this.description = description;
        this.amount = amount;
        //this.month = month;
    }
    //    public String getDescription(String rawCommand) {
    //        return rawCommand.split(" ")[1];
    //    }
    //
    //    public double getAmount(String rawCommand) {
    //        return Double.parseDouble(rawCommand.split(" ")[2]);
    //    }
    //
    //    public int getMonth(String rawCommand) {
    //        return Integer.parseInt(rawCommand.split(" ")[3]);
    //    }

    //    public void addBudget(String rawCommand) {
    //
    //
    //    }

    public void execute(boolean isLoadingStorage) {
        recordList.addBudget(amount);
    }
}
