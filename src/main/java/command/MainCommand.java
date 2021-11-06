package command;

import command.budget.BudgetCommand;
import command.expense.ExpenseCommand;
import command.income.IncomeCommand;
import command.invest.InvestCommand;
import picocli.CommandLine.Command;


@Command(name = "", subcommands = {
        BudgetCommand.class,
        ExpenseCommand.class,
        IncomeCommand.class,
        InvestCommand.class,
        ExitCommand.class,
        HelpCommand.class
})
public class MainCommand {
}
