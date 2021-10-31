package command;

import inventory.Medicine;
import inventory.Order;
import inventory.Prescription;
import inventory.Stock;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

//@@author alvintan01
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
            // Reset the IDs for Stock, Prescription and Orders
            Stock.setStockCount(0);
            Prescription.setPrescriptionCount(0);
            Order.setOrderCount(0);
            ui.print("All data has been cleared!");
            storage.saveData(medicines);
        } else {
            ui.print("Purge aborted!");
        }
    }
}
