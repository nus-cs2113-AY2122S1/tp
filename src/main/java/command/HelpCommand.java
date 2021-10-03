package command;

import inventory.Stock;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Display help message containing command usage information.
 */

public class HelpCommand extends Command {

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Stock> stocks) {
        String LS = System.lineSeparator();
        String HELP = "Welcome to the help page." + LS
                + "Note that parameters enclosed in [] are optional." + LS
                + "Note that date format is dd-MM-yyyy." + LS + LS
                + "|   COMMAND   |   USAGE" + LS
                + "|   ADD       |   " + CommandSyntax.ADD_COMMAND + LS
                + "|   LIST      |   " + CommandSyntax.LIST_COMMAND + LS
                + "|   UPDATE    |   " + CommandSyntax.UPDATE_COMMAND + LS
                + "|   DELETE    |   " + CommandSyntax.DELETE_COMMAND + LS
                + "|   HELP      |   " + CommandSyntax.HELP_COMMAND + LS
                + "|   PURGE     |   " + CommandSyntax.PURGE_COMMAND + LS
                + "|   EXIT      |   " + CommandSyntax.EXIT_COMMAND + LS + LS
                + "For more information, refer to User Guide: https://ay2122s1-cs2113t-t10-1.github.io/tp/";
        ui.print(HELP);
    }
}
