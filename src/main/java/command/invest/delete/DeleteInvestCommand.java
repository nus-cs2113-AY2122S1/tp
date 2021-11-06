package command.invest.delete;

import picocli.CommandLine.Command;

@Command(name = "delete",
        description = "Deletes investment record(s) from the database by the unique identifier (from list)",
        subcommands = {
        DeleteInvestStockCommand.class,
        DeleteInvestSavingsCommand.class
})
public class DeleteInvestCommand {
}

