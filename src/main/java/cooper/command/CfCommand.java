package cooper.command;

import cooper.exceptions.InvalidAccessException;
import cooper.finance.FinanceCommand;
import cooper.parser.CommandParser;
import cooper.resources.ResourcesManager;
import cooper.storage.StorageManager;
import cooper.ui.Ui;
import cooper.verification.SignInDetails;

public class CfCommand extends Command {

    public CfCommand() {
        super();
    }

    @Override
    public void execute(SignInDetails signInDetails, ResourcesManager resourcesManager,
                        StorageManager storageManager) throws InvalidAccessException {
        CommandParser.financeFlag = FinanceCommand.CF;
        Ui.initiateCashFlowStatement();
    }
}
