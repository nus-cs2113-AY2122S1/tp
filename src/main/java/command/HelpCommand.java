package command;

import inventory.Medicine;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Display help message containing command usage information.
 */

public class HelpCommand extends Command {

    @Override
    public void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines, Storage storage) {
        ArrayList<CommandSyntax> commandSyntaxes = new ArrayList<>();
        commandSyntaxes.add(new CommandSyntax(CommandList.ADD_STOCK, CommandSyntax.ADD_STOCK_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.LIST_STOCK, CommandSyntax.LIST_STOCK_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.UPDATE_STOCK, CommandSyntax.UPDATE_STOCK_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.DELETE_STOCK, CommandSyntax.DELETE_STOCK_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.ADD_DISPENSE, CommandSyntax.ADD_DISPENSE_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.LIST_DISPENSE, CommandSyntax.LIST_DISPENSE_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.LIST_ORDER, CommandSyntax.LIST_ORDER_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.EXIT, CommandSyntax.EXIT_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.HELP, CommandSyntax.HELP_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.PURGE, CommandSyntax.PURGE_COMMAND));

        ui.printHelpMessage(commandSyntaxes);
    }
}
