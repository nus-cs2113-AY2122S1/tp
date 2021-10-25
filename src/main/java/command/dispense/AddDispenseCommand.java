package command.dispense;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Dispense;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.DispenseValidator;
import utilities.parser.DateParser;
import utilities.parser.StockManager;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Prescription medication based on user input.
 * User input includes medication name, quantity to prescribe, Customer's NRIC and Staff name.
 */
public class AddDispenseCommand extends Command {

    public AddDispenseCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        String medicationName = parameters.get(CommandParameters.NAME);
        String quantity = parameters.get(CommandParameters.QUANTITY);
        String customerId = parameters.get(CommandParameters.CUSTOMER_ID);
        String staffName = parameters.get(CommandParameters.STAFF);

        String[] requiredParameters = {CommandParameters.NAME, CommandParameters.QUANTITY,
                CommandParameters.CUSTOMER_ID, CommandParameters.STAFF};
        String[] optionalParameters = {};

        DispenseValidator dispenseValidator = new DispenseValidator();

        boolean isInvalidParameters = dispenseValidator.containsInvalidParameters(ui, parameters, requiredParameters,
                optionalParameters, CommandSyntax.ADD_DISPENSE_COMMAND, false);

        if (isInvalidParameters) {
            return;
        }

        boolean isInvalidParameterValues = dispenseValidator.containsInvalidParameterValues(ui, parameters, medicines,
                CommandSyntax.ADD_DISPENSE_COMMAND);
        if (isInvalidParameterValues) {
            return;
        }

        int prescribeQuantity = Integer.parseInt(quantity);
        int quantityToPrescribe = prescribeQuantity;

        if (prescribeQuantity == 0) {
            ui.print("Prescription Quantity cannot be 0.");
            return;
        }

        Date prescribeDate = new Date(); //prescribe date will be today's date
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

        filteredStocks.sort(new utilities.comparators.StockComparator(CommandParameters.EXPIRY_DATE, false));
        int totalStock = StockManager.getTotalStockQuantity(medicines, medicationName);
        if (prescribeQuantity > totalStock) {
            ui.print("Unable to prescribe! Prescription quantity is more than stock available!");
            ui.print("Prescription quantity: " + prescribeQuantity + " Stock available: " + totalStock);
            return;
        }

        for (Stock stock : filteredStocks) {
            int existingQuantity = stock.getQuantity();
            int existingId = stock.getStockId();
            Date existingExpiry = stock.getExpiry();

            int setStockValue = 0;

            if (existingQuantity == quantityToPrescribe) {
                prescribe(ui, medicines, medicationName, customerId, staffName, existingQuantity, prescribeDate,
                        stock, existingId, existingExpiry, setStockValue);
                return;
            }

            if (existingQuantity > quantityToPrescribe) {
                setStockValue = existingQuantity - quantityToPrescribe;
                prescribe(ui, medicines, medicationName, customerId, staffName, quantityToPrescribe, prescribeDate,
                        stock, existingId, existingExpiry, setStockValue);
                return;
            }

            if (existingQuantity < prescribeQuantity) {
                quantityToPrescribe = quantityToPrescribe - existingQuantity;
                prescribe(ui, medicines, medicationName, customerId, staffName, existingQuantity, prescribeDate,
                        stock, existingId, existingExpiry, setStockValue);
            }

        }

    }

    /**
     * Change the stock quantity based on Prescription quantity. Add prescribed medication to prescription list.
     *
     * @param ui                  Reference to the UI object to print messages.
     * @param medicines           Arraylist of all medicines.
     * @param medicationName      Medication to prescribe.
     * @param customerId          Customer ID whom medicine will be prescribed to.
     * @param staffName           Staff who prescribe the medication.
     * @param quantityToPrescribe Quantity of medication to prescribe.
     * @param prescribeDate       Date which medication is prescribed.
     * @param stock               Stock object of the given stock id.
     * @param existingId          Existing id of the stock object.
     * @param existingExpiry      Existing expiry of the stock object.
     * @param setStockValue       Stock quantity to set to after prescribed.
     */
    private void prescribe(Ui ui, ArrayList<Medicine> medicines, String medicationName, String customerId,
                           String staffName, int quantityToPrescribe, Date prescribeDate, Stock stock,
                           int existingId, Date existingExpiry, int setStockValue) {
        String expiry = DateParser.dateToString(existingExpiry);
        stock.setQuantity(setStockValue);
        medicines.add(new Dispense(medicationName, quantityToPrescribe, customerId, prescribeDate,
                staffName, existingId));
        ui.print("Prescribed:" + medicationName + " Quantity:" + quantityToPrescribe + " Expiry "
                + "Date:" + expiry);
        Storage storage = Storage.getInstance();
        storage.saveData(medicines);
    }

}





