package command;

import command.budget.BudgetCommand;
import command.expense.ExpenseCommand;
import command.income.IncomeCommand;
import picocli.CommandLine.Command;


@Command(name = "", subcommands = {
        BudgetCommand.class,
        ExpenseCommand.class,
        IncomeCommand.class
})
public class MainCommand {
}
