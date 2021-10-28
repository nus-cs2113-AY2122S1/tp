package command.income;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import service.IncomeManager;
import terminal.Ui;
import utils.Money;

import java.util.concurrent.Callable;

import static constants.ErrorMessage.addIncomeErrorMsg;

@Command(name = "add", mixinStandardHelpOptions = true, description = "Adds an income source.")
public class AddIncomeCommand implements Callable<Integer> {

    @Parameters(paramLabel = "NAME", arity = "1..*", description = "Name of the income source")
    String[] names;

    @Option(names = {"-v", "--value"}, required = true, description = "Value of the income")
    Double value;

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        IncomeManager incomeMgr = IncomeManager.getIncomeManager();

        try {
            String incomeName = String.join(" ", names);
            Double incomeValue = Money.truncate(value);
            incomeMgr.addIncome(incomeName, incomeValue);

        } catch (Exception error) {
            ui.printMessage(addIncomeErrorMsg);
            return 1;
        }
        return 0;
    }
}
