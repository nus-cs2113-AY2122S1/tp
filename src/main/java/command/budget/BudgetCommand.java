package command.budget;

import picocli.CommandLine.Command;

@Command(name = "budget", mixinStandardHelpOptions = true, subcommands = {
        AddBudgetCommand.class,
        DeleteBudgetCommand.class,
        ListBudgetCommand.class
})
public class BudgetCommand {
}