package command.dispense;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Dispense;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.DateParser;
import utilities.parser.DispenseManager;
import utilities.parser.DispenseValidator;
import utilities.parser.StockManager;
import utilities.parser.StockValidator;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

/**
 * Update dispense information based on user input given dispense id.
 */
public class UpdateDispenseCommand extends Command {
    private static Logger logger = Logger.getLogger("UpdateDispense");

    public UpdateDispenseCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();
        Storage storage = Storage.getInstance();

        String[] requiredParameter = {CommandParameters.ID};
        String[] optionalParameters = {CommandParameters.NAME, CommandParameters.QUANTITY,
                CommandParameters.CUSTOMER_ID, CommandParameters.STAFF, CommandParameters.DATE};

        boolean isInvalidParameter = CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameter,
                optionalParameters, CommandSyntax.UPDATE_DISPENSE_COMMAND, true);
        if (isInvalidParameter) {
            return;
        }

        boolean isInvalidParameterValues = DispenseValidator.containsInvalidParameterValues(ui, parameters, medicines,
                CommandSyntax.UPDATE_DISPENSE_COMMAND);
        if (isInvalidParameterValues) {
            return;
        }

        Dispense dispense = DispenseManager.extractDispenseObject(parameters, medicines);
        assert (dispense != null) : "Dispense object should not be null";
        boolean hasNameParam = parameters.containsKey(CommandParameters.NAME);
        if (hasNameParam) {
            String currentName = dispense.getMedicineName();
            String updatedName = parameters.get(CommandParameters.NAME);
            if (updatedName.equalsIgnoreCase(currentName)) {
                hasNameParam = false;
            }
        }

        boolean hasDateParam = parameters.containsKey(CommandParameters.DATE);
        Date date = null;
        String customerId;
        String staffName;
        if (hasDateParam) {
            try {
                date = DateParser.stringToDate(parameters.get(CommandParameters.DATE));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            date = dispense.getDate();
        }
        assert date != null : "Date should not be null";

        boolean hasCustomerParam = parameters.containsKey(CommandParameters.CUSTOMER_ID);
        if (hasCustomerParam) {
            customerId = parameters.get(CommandParameters.CUSTOMER_ID);
        } else {
            customerId = dispense.getCustomerId();
        }

        boolean hasStaffParam = parameters.containsKey(CommandParameters.STAFF);
        if (hasStaffParam) {
            staffName = parameters.get(CommandParameters.STAFF);
        } else {
            staffName = dispense.getStaff();
        }

        boolean hasQuantityParam = parameters.containsKey(CommandParameters.QUANTITY);
        boolean isSuccessfulUpdate = false;
        if (hasNameParam && hasQuantityParam) {
            isSuccessfulUpdate = processGivenNameAndQuantity(medicines, dispense, customerId, date, staffName);
        } else if (hasNameParam && !hasQuantityParam) {
            isSuccessfulUpdate = processGivenName(medicines, dispense, customerId, date, staffName);
        } else if (!hasNameParam && hasQuantityParam) {
            isSuccessfulUpdate = processGivenQuantity(medicines, dispense, customerId, date, staffName);
        } else {
            isSuccessfulUpdate = processOtherFields(medicines, dispense, customerId, date, staffName);
        }

        if (!isSuccessfulUpdate) {
            return;
        }

    }

    /**
     * Processes name and quantity field provided by user for updating dispense information.
     *
     * @param medicines  Arraylist of all medicines.
     * @param dispense   The associated dispense object.
     * @param customerId CustomerId of customers.
     * @param date       Date of dispense.
     * @param staffName  Staff responsible for the dispensing of medication.
     * @return Boolean value true if update is successful.
     */
    private boolean processGivenNameAndQuantity(ArrayList<Medicine> medicines, Dispense dispense, String customerId,
                                                Date date, String staffName) {
        Ui ui = Ui.getInstance();
        String currentName = dispense.getMedicineName();
        int currentStockId = dispense.getStockId();
        String updatedName = parameters.get(CommandParameters.NAME);

        Stock targetRestoreStock = StockManager.extractStockObject(medicines, currentName, currentStockId);
        if (targetRestoreStock == null) {
            ui.print("Medicine not found in stock");
            return false;
        }
        int restoreStockQuantity = targetRestoreStock.getQuantity();
        int restoredQuantity = dispense.getQuantity();
        int totalQuantity = restoredQuantity + restoreStockQuantity;
        int restoreMaxQuantity = targetRestoreStock.getMaxQuantity();
        boolean isValidRestore = StockValidator.quantityValidityChecker(ui, totalQuantity, restoreMaxQuantity);
        if (!isValidRestore) {
            ui.print("Restoring of medication aborted!");
            return false;
        }

        ArrayList<Stock> targetDispenseStocks = StockManager.getFilteredStocksByName(medicines, updatedName);
        if (targetDispenseStocks.isEmpty()) {
            ui.print("Medicine name does not exist in stock!");
            return false;
        }
        String updatedQuantity = parameters.get(CommandParameters.QUANTITY);
        int dispenseQuantity = Integer.parseInt(updatedQuantity);
        int availableQuantity = StockManager.getTotalStockQuantity(medicines, updatedName);
        boolean isValidDispense = StockValidator.quantityValidityChecker(ui, dispenseQuantity, availableQuantity);
        if (!isValidDispense) {
            ui.print("Dispensing of medication aborted!");
            return false;
        }
        // Guarantee is be able to restore & dispense
        DispenseManager.restoreStock(targetRestoreStock, totalQuantity);
        ArrayList<Dispense> updatedDispenses = DispenseManager.dispenseStock(medicines, targetDispenseStocks,
                dispenseQuantity, customerId, date, staffName);
        // Add to dispense
        for (Dispense updatedDispense : updatedDispenses) {
            medicines.add(updatedDispense);
        }
        medicines.remove(dispense);

        ui.print("Restored " + restoredQuantity + " " + targetRestoreStock.getMedicineName());
        ui.print("Updated dispense information!");
        ui.printDispenses(updatedDispenses);
        return true;
    }

    /**
     * Processes name field provided by user for updating dispense information.
     *
     * @param medicines  Arraylist of all medicines.
     * @param dispense   The associated dispense object.
     * @param customerId CustomerId of customers.
     * @param date       Date of dispense.
     * @param staffName  Staff responsible for the dispensing of medication.
     * @return Boolean value true if update is successful.
     */
    private boolean processGivenName(ArrayList<Medicine> medicines, Dispense dispense, String customerId, Date date,
                                     String staffName) {
        Ui ui = Ui.getInstance();
        String currentName = dispense.getMedicineName();
        int currentStockId = dispense.getStockId();
        String updatedName = parameters.get(CommandParameters.NAME);
        Stock targetRestoreStock = StockManager.extractStockObject(medicines, currentName, currentStockId);
        if (targetRestoreStock == null) {
            ui.print("Medicine not found in stock");
            return false;
        }
        int restoreStockQuantity = targetRestoreStock.getQuantity();
        int restoredQuantity = dispense.getQuantity();
        int totalQuantity = restoredQuantity + restoreStockQuantity;
        int restoreMaxQuantity = targetRestoreStock.getMaxQuantity();
        boolean isValidRestore = StockValidator.quantityValidityChecker(ui, totalQuantity, restoreMaxQuantity);
        if (!isValidRestore) {
            ui.print("Restoring of medication aborted!");
            return false;
        }

        ArrayList<Stock> targetDispenseStocks = StockManager.getFilteredStocksByName(medicines, updatedName);
        if (targetDispenseStocks.isEmpty()) {
            ui.print("Medicine name does not exist in stock!");
            return false;
        }
        int availableQuantity = StockManager.getTotalStockQuantity(medicines, updatedName);
        boolean isValidDispense = StockValidator.quantityValidityChecker(ui, restoredQuantity, availableQuantity);
        if (!isValidDispense) {
            ui.print("Dispensing of medication aborted!");
            return false;
        }

        // Guarantee to be able to restore & dispense
        DispenseManager.restoreStock(targetRestoreStock, totalQuantity);
        ArrayList<Dispense> updatedDispenses = DispenseManager.dispenseStock(medicines, targetDispenseStocks,
                restoredQuantity, customerId, date,
                staffName);

        // Add to dispense
        for (Dispense updatedDispense : updatedDispenses) {
            medicines.add(updatedDispense);
        }
        medicines.remove(dispense);
        ui.print("Restored " + restoredQuantity + " " + targetRestoreStock.getMedicineName());
        ui.print("Updated dispense information!");
        ui.printDispenses(updatedDispenses);
        return true;
    }

    /**
     * Processes quantity field provided by user for updating dispense information.
     *
     * @param medicines  Arraylist of all medicines.
     * @param dispense   The associated dispense object.
     * @param customerId CustomerId of customers.
     * @param date       Date of dispense.
     * @param staffName  Staff responsible for the dispensing of medication.
     * @return Boolean value true if update is successful.
     */
    private boolean processGivenQuantity(ArrayList<Medicine> medicines, Dispense dispense, String customerId,
                                         Date date, String staffName) {
        Ui ui = Ui.getInstance();
        String currentName = dispense.getMedicineName();
        int currentStockId = dispense.getStockId();
        int currentQuantity = dispense.getQuantity();
        int stockId = dispense.getStockId();
        int updatedQuantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
        if (updatedQuantity < currentQuantity) {
            Stock stock = StockManager.extractStockObject(medicines, currentName, currentStockId);
            if (stock == null) {
                ui.print("Medicine not found in stock");
                return false;
            }
            int restoreQuantity = currentQuantity - updatedQuantity;
            int stockQuantity = stock.getQuantity();
            int stockMaxQuantity = stock.getMaxQuantity();
            int totalQuantity = stockQuantity + restoreQuantity;
            boolean isValidRestore = StockValidator.quantityValidityChecker(ui, totalQuantity, stockMaxQuantity);
            if (!isValidRestore) {
                ui.print("Restoring of medication aborted!");
                return false;
            }
            // guarantee can restore
            DispenseManager.restoreStock(stock, totalQuantity);
            medicines.remove(dispense);
            Dispense newDispense = new Dispense(currentName, updatedQuantity, customerId, date, staffName,
                    stockId);
            medicines.add(newDispense);
            ArrayList<Dispense> updatedDispenses = new ArrayList<>();
            updatedDispenses.add(newDispense);
            ui.print("Restored " + restoreQuantity + " " + stock.getMedicineName());
            ui.print("Updated dispense information!");
            ui.printDispenses(updatedDispenses);

        } else if (updatedQuantity > currentQuantity) {
            ArrayList<Stock> targetDispenseStocks = StockManager.getFilteredStocksByName(medicines, currentName);
            if (targetDispenseStocks.isEmpty()) {
                ui.print("Medicine name does not exist in stock!");
                return false;
            }
            int dispensedQuantity = updatedQuantity - currentQuantity;
            int stockQuantity = StockManager.getTotalStockQuantity(medicines, currentName);
            boolean isValidDispense = StockValidator.quantityValidityChecker(ui, dispensedQuantity, stockQuantity);
            if (!isValidDispense) {
                ui.print("Dispensing of medication aborted!");
                return false;
            }
            // guarantee can dispense
            ArrayList<Dispense> updatedDispenses = DispenseManager.dispenseStock(medicines, targetDispenseStocks,
                    dispensedQuantity, customerId, date, staffName);

            medicines.remove(dispense);

            for (Dispense updatedDispense : updatedDispenses) {
                if (updatedDispense.getStockId() == stockId) {
                    int newQuantity = currentQuantity + updatedDispense.getQuantity();
                    updatedDispense.setQuantity(newQuantity);
                    break;
                }
            }

            // Add to dispense
            for (Dispense updatedDispense : updatedDispenses) {
                medicines.add(updatedDispense);
            }
            ui.print("Updated dispense information!");
            ui.printDispenses(updatedDispenses);
        }
        return true;
    }

    /**
     * Processes other fields provided by user for updating dispense information.
     * The other field are the customerId, date and staffName.
     *
     * @param medicines  Arraylist of all medicines.
     * @param dispense   The associated dispense object.
     * @param customerId CustomerId of customers.
     * @param date       Date of dispense.
     * @param staffName  Staff responsible for the dispensing of medication.
     * @return Boolean value true if update is successful.
     */
    private boolean processOtherFields(ArrayList<Medicine> medicines, Dispense dispense, String customerId, Date date,
                                       String staffName) {
        if (dispense == null) {
            return false;
        }
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Dispense)) {
                continue;
            }
            Dispense targetDispense = (Dispense) medicine;
            boolean isSameStockId = targetDispense.getStockId() == dispense.getStockId();
            if (isSameStockId) {
                ((Dispense) medicine).setCustomerId(customerId);
                ((Dispense) medicine).setDate(date);
                ((Dispense) medicine).setStaff(staffName);
            }
        }
        ArrayList<Dispense> newDispenses = new ArrayList<>();
        newDispenses.add(dispense);
        Ui ui = Ui.getInstance();
        ui.print("Updated dispense information!");
        ui.printDispenses(newDispenses);
        return true;
    }

}
