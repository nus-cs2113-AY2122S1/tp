package command.invest.add;

import picocli.CommandLine.Command;

@Command(name = "add", mixinStandardHelpOptions = true, description = "Adds an investment source.", subcommands = {
        AddInvestStockCommand.class,
        AddInvestSavingsCommand.class
})
public class AddInvestCommand {
}
