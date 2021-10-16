package command;

import inventory.Medicine;
import ui.Ui;
import storage.Storage;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Represents the generic command. Helps to declare the abstract methods. It is inherited by all commands.
 */

public abstract class Command {
    public abstract void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines, Storage storage);
}
