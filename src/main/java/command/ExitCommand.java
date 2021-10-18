package command;

import inventory.Medicine;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

/**
 * Helps to process the exit command and prints the exit message.
 */

public class ExitCommand extends Command {

    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();
        Storage storage = Storage.getInstance();

        storage.saveData(medicines);
        ui.printExit();
    }
}
