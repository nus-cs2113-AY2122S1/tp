package command.invest.list;

import picocli.CommandLine.Command;

@Command(name = "list", description = "Shows a list of all income sources for the current month.",
        subcommands = {
        ListInvestStockCommand.class,
        ListInvestSavingCommand.class
})
public class ListInvestCommand {
}
