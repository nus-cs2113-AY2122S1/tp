package seedu.duke.commands;

import seedu.duke.data.BudgetList;

//add EXPENDITURE_NAME COST DATE_TIME_OF_EXPENDITURE

public class AddBudgetCommand extends Command {
    public static final String COMMAND_WORD = "AddBudget";
    public String description;
    public double amount;
    public int month;

    public String getDescription(String rawCommand) {
        return rawCommand.split(" ")[1];
    }

    public double getAmount(String rawCommand) {
        return Double.parseDouble(rawCommand.split(" ")[2]);
    }

    public int getMonth(String rawCommand) {
        return Integer.parseInt(rawCommand.split(" ")[3]);
    }

    public void addBudget(String rawCommand) {
        this.description = getDescription(rawCommand);
        this.amount = getAmount(rawCommand);
        this.month = getMonth(rawCommand);

        budgetList.addBudget(this.description, this.amount, this.month);
    }

    public void execute() {

    }
}
