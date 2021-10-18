package command.dispense;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Dispense;
import inventory.Medicine;
import inventory.Stock;
import parser.DispenseValidator;
import parser.StockManager;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Dispense medication based on user input.
 * User input includes medication name, quantity to dispense, Customer's NRIC and Staff name.
 */
public class AddDispenseCommand extends Command {

    @Override
    public void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines,
                        Storage storage) {
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
        int quantityToDispense = dispenseQuantity;
        int totalStock = StockManager.getTotalStockQuantity(medicines, medicationName);
        Date dispenseDate = new Date(); //dispense date will be today's date
        ArrayList<Stock> filteredStocks = new ArrayList<>();

        for (Medicine medicine : medicines) {
            if ((medicine instanceof Stock) && (medicine.getMedicineName().equalsIgnoreCase(medicationName))) {
                filteredStocks.add((Stock) medicine);
            }
        }

        if (filteredStocks.isEmpty()) {
            ui.print("Medicine not available!");
            return;
        }

        filteredStocks.sort(new comparators.StockComparator(CommandParameters.EXPIRY_DATE, false));

        for (Stock stock : filteredStocks) {
            int existingQuantity = stock.getQuantity();
            int existingId = stock.getStockID();
            Date existingExpiry = stock.getExpiry();
            int setStockValue = 0;

            if (dispenseQuantity > totalStock) {
                ui.print("Unable to Dispense! Dispense quantity is more than stock available!");
                ui.print("Dispense quantity: " + dispenseQuantity + " Stock available: " + totalStock);
                return;
            }

            if (existingQuantity == quantityToDispense) {
                dispense(ui, medicines, medicationName, customerId, staffName, existingQuantity, dispenseDate,
                        stock, existingId, existingExpiry, setStockValue);
                return;
            }

            if (existingQuantity > quantityToDispense) {
                setStockValue = existingQuantity - quantityToDispense;
                dispense(ui, medicines, medicationName, customerId, staffName, quantityToDispense, dispenseDate,
                        stock, existingId, existingExpiry, setStockValue);
                return;
            }

            if (existingQuantity < dispenseQuantity) {
                quantityToDispense = quantityToDispense - existingQuantity;
                dispense(ui, medicines, medicationName, customerId, staffName, existingQuantity, dispenseDate,
                        stock, existingId, existingExpiry, setStockValue);
            }

        }

    }

    /**
     * Change the stock quantity based on dispense quantity. Add dispense medication to dispense list.
     *
     * @param ui                 Reference to the UI object passed by Main to print messages.
     * @param medicines          Arraylist of all medicines.
     * @param medicationName     Medication to dispense.
     * @param customerId         Customer ID whom medicine will be dispensed to.
     * @param staffName          Staff who dispense the medication.
     * @param quantityToDispense Quantity of medication to dispense.
     * @param dispenseDate       Date which medication is dispensed
     * @param stock              Stock object of the given stock id.
     * @param existingId         Existing id of the stock object.
     * @param existingExpiry     Existing expiry of the stock object.
     * @param setStockValue      Stock quantity to set to after dispensed.
     */
    private void dispense(Ui ui, ArrayList<Medicine> medicines, String medicationName, String customerId,
                          String staffName, int quantityToDispense, Date dispenseDate, Stock stock,
                          int existingId, Date existingExpiry, int setStockValue) {
        stock.setQuantity(setStockValue);
        medicines.add(new Dispense(medicationName, quantityToDispense, customerId, dispenseDate,
                staffName, existingId));
        ui.print("Dispensed:" + medicationName + " Quantity:" + quantityToDispense + " Expiry "
                + "date:" + existingExpiry);
    }

}





