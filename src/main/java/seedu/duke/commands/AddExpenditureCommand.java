package seedu.duke.commands;

import seedu.duke.data.records.Expenditure;
import seedu.duke.parser.Parser;

public class AddExpenditureCommand extends Command {

    public static final String COMMAND_WORD = "AddExpenditure";

    private final String expenditureParams;

    public AddExpenditureCommand(String expenditureParams) {
        this.expenditureParams = expenditureParams;
    }

    public void execute() {
        String[] splitExpenditureParams = Parser.splitExpenditureParams(expenditureParams);
        String expenditureDescription = splitExpenditureParams[0].trim();
        double expenditureAmount = Double.parseDouble(splitExpenditureParams[1].trim());
        int expenditureMonth = Integer.parseInt(splitExpenditureParams[2].trim());
        Expenditure newExpenditure = new Expenditure(expenditureDescription, expenditureAmount, expenditureMonth);
        budgetList.addExpenditure(newExpenditure);
    }
}
