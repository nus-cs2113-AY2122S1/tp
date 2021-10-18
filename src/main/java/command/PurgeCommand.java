package command;

import inventory.Medicine;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helps to process the purge command and prompts the user for confirmation.
 */

public class PurgeCommand extends Command {
    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();
        Storage storage = Storage.getInstance();

        ui.print("Are you sure you want to delete all data? (Y/N)");
        Scanner in = new Scanner(System.in);
        if ("Y".equals(in.nextLine())) {
            medicines.clear();
            ui.print("All data has been cleared!");
            storage.saveData(medicines);
        } else {
            ui.print("Purge aborted!");
        }
    }
}
