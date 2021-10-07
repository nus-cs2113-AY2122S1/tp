package seedu.duke.commands;

import seedu.duke.data.records.Expenditure;
import seedu.duke.parser.Parser;

public class AddExpenditureCommand extends Command {

    public static final String COMMAND_WORD = "AddExpenditure";
    public String description;
    public double spending;
    public int month;

    public String getDescription(String rawCommand) {
        return rawCommand.split(" ")[1];
    }

    public double getSpending(String rawCommand) {
        return Double.parseDouble(rawCommand.split(" ")[2]);
    }

    public int getMonth(String rawCommand) {
        return Integer.parseInt(rawCommand.split(" ")[3]);
    }

    public void addBudget(String rawCommand) {
        this.description = getDescription(rawCommand);
        this.spending = getSpending(rawCommand);
        this.month = getMonth(rawCommand);

        budgetList.addBudget(this.description, this.spending, this.month);
    }

    public void execute() {
    }
}
