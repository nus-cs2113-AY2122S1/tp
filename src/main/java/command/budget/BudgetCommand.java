package command.budget;

import command.expense.AddExpenseCommand;
import command.expense.DeleteExpenseCommand;
import command.expense.ListExpenseCommand;
import picocli.CommandLine.Command;


@Command(name = "budget", mixinStandardHelpOptions = true, subcommands = {
        AddBudgetCommand.class,
        DeleteBudgetCommand.class,
        ListBudgetCommand.class
})
public class BudgetCommand {
}