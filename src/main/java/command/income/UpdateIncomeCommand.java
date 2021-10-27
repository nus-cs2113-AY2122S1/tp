package command.income;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import service.IncomeManager;
import terminal.Ui;
import utils.Money;

import java.util.concurrent.Callable;

import static constants.ErrorMessage.updateIncomeErrorMsg;

@Command(name = "update", mixinStandardHelpOptions = true, description = "Updates an income for the current month.")
public class UpdateIncomeCommand implements Callable<Integer> {

    @Parameters(paramLabel = "NAME", arity = "1..*", description = "Name of the income source")
    String[] names;

    @Option(names = {"-v", "--value"}, required = true, description = "Value of the income")
    Double value;

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();

        try {
            String incomeName = String.join(" ", names);
            Double incomeValue = Money.truncate(value);
            IncomeManager.updateIncome(incomeName, incomeValue);

        } catch (Exception error) {
            ui.printMessage(updateIncomeErrorMsg);
            return 1;
        }
        return 0;
    }
}