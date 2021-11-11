package command;

import utilities.ui.Ui;

import java.util.ArrayList;

//@@author alvintan01

/**
 * Display help message containing command usage information.
 */
public class HelpCommand extends Command {

    @Override
    public void execute() {
        ArrayList<CommandSyntax> commandSyntaxes = new ArrayList<>();
        commandSyntaxes.add(new CommandSyntax(CommandList.ADD_STOCK, CommandSyntax.ADD_STOCK_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.DELETE_STOCK, CommandSyntax.DELETE_STOCK_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.UPDATE_STOCK, CommandSyntax.UPDATE_STOCK_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.LIST_STOCK, CommandSyntax.LIST_STOCK_COMMAND));

        commandSyntaxes.add(new CommandSyntax(CommandList.ADD_PRESCRIPTION, CommandSyntax.ADD_PRESCRIPTION_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.DELETE_PRESCRIPTION,
                CommandSyntax.DELETE_PRESCRIPTION_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.UPDATE_PRESCRIPTION,
                CommandSyntax.UPDATE_PRESCRIPTION_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.LIST_PRESCRIPTION, CommandSyntax.LIST_PRESCRIPTION_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.ARCHIVE_PRESCRIPTION,
                CommandSyntax.ARCHIVE_PRESCRIPTION_COMMAND));

        commandSyntaxes.add(new CommandSyntax(CommandList.ADD_ORDER, CommandSyntax.ADD_ORDER_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.DELETE_ORDER, CommandSyntax.DELETE_ORDER_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.UPDATE_ORDER, CommandSyntax.UPDATE_ORDER_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.LIST_ORDER, CommandSyntax.LIST_ORDER_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.ARCHIVE_ORDER, CommandSyntax.ARCHIVE_ORDER_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.RECEIVE_ORDER, CommandSyntax.RECEIVE_ORDER_COMMAND));

        commandSyntaxes.add(new CommandSyntax(CommandList.PURGE, CommandSyntax.PURGE_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.HELP, CommandSyntax.HELP_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.EXIT, CommandSyntax.EXIT_COMMAND));

        Ui ui = Ui.getInstance();
        ui.printHelpMessage(commandSyntaxes);
    }
}
