package command.invest.delete;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import service.ExpenseManager;
import storage.DataManager;
import terminal.Ui;

import java.util.concurrent.Callable;

import static constants.ErrorMessage.deleteExpenseErrorMsg;

@Command(name = "delete",
        description = "Deletes investment record(s) from the database by the unique identifier (from list)",
        subcommands = {
        DeleteInvestStockCommand.class,
        DeleteInvestSavingsCommand.class
})
public class DeleteInvestCommand {
}

