package command.income;

import picocli.CommandLine.Command;

@Command(name = "income", mixinStandardHelpOptions = true, subcommands = {
        AddIncomeCommand.class,
        DeleteIncomeCommand.class,
        ListIncomeCommand.class,
        UpdateIncomeCommand.class
})
public class IncomeCommand {
}
