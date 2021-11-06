package command.invest;

import command.invest.add.AddInvestCommand;
import command.invest.delete.DeleteInvestCommand;
import command.invest.list.ListInvestCommand;
import picocli.CommandLine.Command;

@Command(name = "invest", mixinStandardHelpOptions = true, subcommands = {
        AddInvestCommand.class,
        DeleteInvestCommand.class,
        ListInvestCommand.class
})
public class InvestCommand {
}
