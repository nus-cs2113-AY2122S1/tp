package seedu.duke.commands;

import seedu.duke.data.records.Expenditure;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;

import java.time.LocalDate;

public class AddExpenditureCommand extends Command {


    public static final String COMMAND_WORD = "addexpenditure";
    public String description;
    public double spending;
    LocalDate date;

    public AddExpenditureCommand(String description, double amount, LocalDate date) {
        this.description = description;
        this.spending = amount;
        this.date = date;
    }


    public void execute() {
        Expenditure newExpenditure = new Expenditure(description, spending, date);
        budgetList.addExpenditure(description, spending, date);
        TextUi.showExpenditureAddedMessage(newExpenditure);
    }
}
