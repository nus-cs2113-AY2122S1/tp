package command.dispense;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Dispense;
import inventory.Medicine;
import inventory.Stock;
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

        if (CommandSyntax.containsInvalidParameterValues(ui, parameters, medicines,
                CommandSyntax.ADD_DISPENSE_COMMAND)) {
            return;
        }

        int dispenseQuantity = Integer.parseInt(quantity);
        int quantityLeftToDispense = dispenseQuantity;
        int totalStock = MedicineManager.getTotalStockQuantity(medicines, medicationName);
        int totalColumnLeft = MedicineManager.getTotalRows(medicines, medicationName);
        Date dispenseDate = new Date(); //dispense date will be today's date
        ArrayList<Stock> filteredMedicines = new ArrayList<>();

        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock) { //Ensure that it is a medicine object
                filteredMedicines.add((Stock) medicine);
            }
        }

        filteredMedicines.sort(new comparators.StockComparator(CommandParameters.EXPIRY_DATE, false));

        for (Medicine medicine : filteredMedicines) {
            Stock existingStock = (Stock) medicine;
            String existingName = existingStock.getMedicineName().toUpperCase();
            int existingQuantity = existingStock.getQuantity();
            int existingId = existingStock.getStockID();
            Date existingExpiry = existingStock.getExpiry();
            boolean medicationExist = existingName.equals(medicationName.toUpperCase());

            if (medicationExist && (dispenseQuantity > totalStock)) {
                ui.print("Unable to Dispense! Dispense quantity is more than stock available!");
                ui.print("Dispense quantity: " + dispenseQuantity + " Stock available: " + totalStock);
                return;
            }

            if (medicationExist) {

                if (existingQuantity >= quantityLeftToDispense) {
                    existingStock.setQuantity(existingQuantity - quantityLeftToDispense);
                    medicines.add(new Dispense(medicationName, dispenseQuantity, customerId, dispenseDate,
                            staffName, existingId));
                    ui.print("Dispensed:" + medicationName + " Quantity:" + quantityLeftToDispense + " Expiry "
                            + "date:" + existingExpiry);
                    return;
                }

                if (totalColumnLeft > 1 && existingQuantity < dispenseQuantity) {
                    quantityLeftToDispense = quantityLeftToDispense - existingQuantity;
                    existingStock.setQuantity(0);
                    ui.print("Dispensed:" + medicationName + " Quantity:" + existingQuantity + " Expiry "
                            + "date:" + existingExpiry);
                }

                totalColumnLeft--;
            }

        }

        ui.print("Medicine not available!");
    }

}



