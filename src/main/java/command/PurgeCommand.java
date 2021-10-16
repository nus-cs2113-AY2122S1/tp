package command;

import inventory.Medicine;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Helps to process the purge command and prompts the user for confirmation.
 */

public class PurgeCommand extends Command {
    @Override
    public void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines,
                        Storage storage) {
        ui.print("Are you sure you want to delete all data? (Y/N)");
        Scanner in = new Scanner(System.in);
        if ("Y".equals(in.nextLine())) {
            medicines.clear();
            ui.print("All data has been cleared!");
        } else {
            ui.print("Purge aborted!");
        }
    }
}
