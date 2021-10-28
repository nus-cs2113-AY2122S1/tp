package command.invest.add;

import picocli.CommandLine;
import service.InvestManager;
import storage.DataManager;
import terminal.Ui;
import utils.Money;

import java.util.concurrent.Callable;

import static constants.ErrorMessage.addIncomeErrorMsg;

@CommandLine.Command(name = "savings", description = "Add a savings account to your portfolio")
public class AddInvestSavingsCommand implements Callable<Integer> {
    @CommandLine.Parameters(paramLabel = "NAME", arity = "1..*", description = "Name of the savings account")
    String[] names;

    @CommandLine.Option(names = {"-v", "--value"}, required = true, description = "Value of the savings account")
    Double value;

    @Override
    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        InvestManager investMgr = InvestManager.getInvestManager();
        DataManager dataMgr = DataManager.getDataMgr();

        try {
            String savingName = String.join(" ", names);
            Double savingValue = Money.truncate(value);
            investMgr.addSaving(savingName, savingValue);
            dataMgr.write();
        } catch (Exception error) {
            ui.printMessage(error.getMessage());
            return 1;
        }
        return 0;
    }
}
