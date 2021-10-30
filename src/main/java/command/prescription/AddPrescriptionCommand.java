package command.prescription;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Prescription;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.PrescriptionValidator;
import utilities.parser.DateParser;
import utilities.parser.StockManager;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

//@@author deonchung

/**
 * Prescribes medication based on user input.
 * User input includes medication name, quantity to prescribe, Customer's NRIC and Staff name.
 */
public class AddPrescriptionCommand extends Command {

    public AddPrescriptionCommand(LinkedHashMap<String, String> parameters) {
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

        PrescriptionValidator prescriptionValidator = new PrescriptionValidator();

        boolean isInvalidParameters = prescriptionValidator.containsInvalidParameters(ui, parameters,
                requiredParameters,
                optionalParameters, CommandSyntax.ADD_PRESCRIPTION_COMMAND, false);

        if (isInvalidParameters) {
            return;
        }

        boolean isInvalidParameterValues = prescriptionValidator.containsInvalidParameterValues(ui, parameters,
                medicines,
                CommandSyntax.ADD_PRESCRIPTION_COMMAND);
        if (isInvalidParameterValues) {
            return;
        }

        int prescriptionQuantity = Integer.parseInt(quantity);
        int quantityToPrescribe = prescriptionQuantity;

        if (quantityToPrescribe == 0) {
            ui.print("Prescription Quantity cannot be 0.");
            return;
        }


        ArrayList<Stock> filteredStocks = new ArrayList<>();

        for (Medicine medicine : medicines) {
            if ((medicine instanceof Stock) && (medicine.getMedicineName().equalsIgnoreCase(medicationName))
                    && !((Stock) medicine).isDeleted()) {
                filteredStocks.add((Stock) medicine);
            }
        }

        if (filteredStocks.isEmpty()) {
            ui.print("Medicine not available!");
            return;
        }

        filteredStocks.sort(new utilities.comparators.StockComparator(CommandParameters.EXPIRY_DATE, false));
        int totalStock = StockManager.getTotalStockQuantity(medicines, medicationName);

        if (prescriptionQuantity > totalStock) {
            ui.print("Unable to Prescribe! Prescription quantity is more than stock available!");
            ui.print("Prescription quantity: " + prescriptionQuantity + " Stock available: " + totalStock);
            return;
        }
        Date prescribeDate = new Date(); //prescribe date will be today's date
        String prescribeDateString = DateParser.dateToString(prescribeDate);

        for (Stock stock : filteredStocks) {
            int existingQuantity = stock.getQuantity();
            int existingId = stock.getStockId();
            Date existingExpiry = stock.getExpiry();
            String expiryString = DateParser.dateToString(existingExpiry);

            if (existingExpiry.after(prescribeDate) || prescribeDateString.equals(expiryString)) {

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

                if (existingQuantity < prescriptionQuantity && existingQuantity != 0) {
                    quantityToPrescribe = quantityToPrescribe - existingQuantity;
                    prescribe(ui, medicines, medicationName, customerId, staffName, existingQuantity, prescribeDate,
                            stock, existingId, existingExpiry, setStockValue);
                }
            }

        }
        ui.print("Unable to Prescribe! Medicine has expired!");

    }

    /**
     * Change the stock quantity based on prescription quantity. Add prescribed medication to prescription list.
     *
     * @param ui                  Reference to the UI object to print messages.
     * @param medicines           Arraylist of all medicines.
     * @param medicationName      Medication to prescribe.
     * @param customerId          Customer ID whom medicine will be prescribed to.
     * @param staffName           Staff who prescribe the medication.
     * @param quantityToPrescribe Quantity of medication to prescribe.
     * @param prescribeDate       Date which medication is prescribed
     * @param stock               Stock object of the given stock id.
     * @param existingId          Existing id of the stock object.
     * @param existingExpiry      Existing expiry of the stock object.
     * @param setStockValue       Stock quantity to set to after prescription.
     */
    private void prescribe(Ui ui, ArrayList<Medicine> medicines, String medicationName, String customerId,
                           String staffName, int quantityToPrescribe, Date prescribeDate, Stock stock,
                           int existingId, Date existingExpiry, int setStockValue) {
        String expiry = DateParser.dateToString(existingExpiry);
        stock.setQuantity(setStockValue);
        Prescription prescription = new Prescription(medicationName, quantityToPrescribe, customerId, prescribeDate,
                staffName, existingId);
        medicines.add(prescription);
        ui.print("Prescribed:" + medicationName + " Quantity:" + quantityToPrescribe + " Expiry "
                + "Date:" + expiry);
        ui.printPrescription(prescription);
        Storage storage = Storage.getInstance();
        storage.saveData(medicines);
    }

}





