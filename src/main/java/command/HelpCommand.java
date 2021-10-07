package command;

import inventory.Medicine;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Display help message containing command usage information.
 */

public class HelpCommand extends Command {

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        ArrayList<CommandSyntax> commandSyntaxes = new ArrayList<>();
        commandSyntaxes.add(new CommandSyntax(CommandList.ADD, CommandSyntax.ADD_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.LIST, CommandSyntax.LIST_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.UPDATE, CommandSyntax.UPDATE_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.DELETE, CommandSyntax.DELETE_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.EXIT, CommandSyntax.EXIT_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.HELP, CommandSyntax.HELP_COMMAND));
        commandSyntaxes.add(new CommandSyntax(CommandList.PURGE, CommandSyntax.PURGE_COMMAND));

        ui.printHelpMessage(commandSyntaxes);
    }
}
