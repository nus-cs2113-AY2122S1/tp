package command.dispense;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Dispense;
import inventory.Medicine;
import inventory.Stock;
import parser.DispenseValidator;
import parser.MedicineManager;
import ui.Ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Dispense medication based on user input.
 * User input includes medication name, quantity to dispense, Customer's NRIC and Staff name.
 */
public class AddDispense extends Command {

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        String medicationName = parameters.get(CommandParameters.NAME);
        String quantity = parameters.get(CommandParameters.QUANTITY);
        String customerId = parameters.get(CommandParameters.CUSTOMER_ID);
        String staffName = parameters.get(CommandParameters.STAFF);

        String[] requiredParameters = {CommandParameters.NAME, CommandParameters.QUANTITY,
                                       CommandParameters.CUSTOMER_ID, CommandParameters.STAFF};
        String[] optionalParameters = {};

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters, optionalParameters,
                CommandSyntax.ADD_DISPENSE_COMMAND, false)) {
            return;
        }

        if (DispenseValidator.containsInvalidParameterValues(ui, parameters, medicines,
                CommandSyntax.ADD_DISPENSE_COMMAND)) {
            return;
        }

        int dispenseQuantity = Integer.parseInt(quantity);
        int quantityLeftToDispense = dispenseQuantity;
        int totalStock = MedicineManager.getTotalStockQuantity(medicines, medicationName);
        Date dispenseDate = new Date(); //dispense date will be today's date
        ArrayList<Stock> filteredStocks = new ArrayList<>();

        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock) { //Ensure that it is a medicine object
                filteredStocks.add((Stock) medicine);
            }
        }

        filteredStocks.sort(new comparators.StockComparator(CommandParameters.EXPIRY_DATE, false));

        for (Stock stock : filteredStocks) {
            String existingName = stock.getMedicineName().toUpperCase();
            int existingQuantity = stock.getQuantity();
            int existingId = stock.getStockID();
            Date existingExpiry = stock.getExpiry();
            boolean medicationExist = existingName.equals(medicationName.toUpperCase());

            if (medicationExist && (dispenseQuantity > totalStock)) {
                ui.print("Unable to Dispense! Dispense quantity is more than stock available!");
                ui.print("Dispense quantity: " + dispenseQuantity + " Stock available: " + totalStock);
                return;
            }

            if (!medicationExist) {
                continue;
            }

            if (existingQuantity >= quantityLeftToDispense) {
                stock.setQuantity(existingQuantity - quantityLeftToDispense);
                medicines.add(new Dispense(medicationName, dispenseQuantity, customerId, dispenseDate,
                        staffName, existingId));
                ui.print("Dispensed:" + medicationName + " Quantity:" + quantityLeftToDispense + " Expiry "
                        + "date:" + existingExpiry);
                return;
            }

            if (existingName.equalsIgnoreCase(medicationName) && existingQuantity < dispenseQuantity) {
                quantityLeftToDispense = quantityLeftToDispense - existingQuantity;
                stock.setQuantity(0);
                ui.print("Dispensed:" + medicationName + " Quantity:" + existingQuantity + " Expiry "
                        + "date:" + existingExpiry);
            }

        }

        ui.print("Medicine not available!");
    }

}





