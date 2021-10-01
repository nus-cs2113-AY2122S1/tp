package command;

import inventory.Stock;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the generic command. Helps to declare the abstract methods. It is inherited by all commands.
 */

public abstract class Command {
    public abstract void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Stock> stocks);
}
