package command;

import inventory.Medicine;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Helps to process the exit command and prints the exit message.
 */


public class ExitCommand extends Command {

    @Override
    public void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines,
                        Storage storage) {
        storage.saveData(medicines);
        ui.printExit();
    }
}
