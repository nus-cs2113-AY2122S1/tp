package command.prescription;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Prescription;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.MedicineValidator;
import utilities.parser.PrescriptionManager;
import utilities.parser.PrescriptionValidator;
import utilities.parser.StockManager;
import utilities.parser.StockValidator;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

//@@author a-tph

/**
 * Update prescription information based on user input given prescription id.
 */
public class UpdatePrescriptionCommand extends Command {
    private static Logger logger = Logger.getLogger("UpdatePrescription");

    public UpdatePrescriptionCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        String[] requiredParameters = {CommandParameters.ID};
        String[] optionalParameters = {CommandParameters.NAME, CommandParameters.QUANTITY,
                CommandParameters.CUSTOMER_ID, CommandParameters.STAFF, CommandParameters.DATE};

        MedicineValidator validator = new PrescriptionValidator();
        boolean isInvalidInput = validator.containsInvalidParametersAndValues(ui, parameters, requiredParameters,
                optionalParameters, CommandSyntax.UPDATE_PRESCRIPTION_COMMAND, true, validator);
        if (isInvalidInput) {
            return;
        }

        Prescription prescription = PrescriptionManager.extractPrescriptionObject(parameters, medicines);
        assert (prescription != null) : "Prescription object should not be null";

        boolean hasNameParam = validator.hasNameParamChecker(parameters, prescription.getMedicineName());
        Date date = PrescriptionManager.getUpdatedDate(parameters, prescription.getDate());
        String customerId = PrescriptionManager.getUpdatedCustomerId(parameters, prescription.getCustomerId());
        String staffName = PrescriptionManager.getUpdatedStaff(parameters, prescription.getStaff());
        boolean hasQuantityParam = validator.hasQuantityParamChecker(parameters, prescription.getQuantity());
        boolean isSuccessfulUpdate = false;

        if (hasNameParam && hasQuantityParam) {
            isSuccessfulUpdate = processGivenNameAndQuantity(medicines, prescription, customerId, date, staffName);
        } else if (hasNameParam && !hasQuantityParam) {
            isSuccessfulUpdate = processGivenName(medicines, prescription, customerId, date, staffName);
        } else if (!hasNameParam && hasQuantityParam) {
            isSuccessfulUpdate = processGivenQuantity(medicines, prescription, customerId, date, staffName);
        } else {
            isSuccessfulUpdate = processOtherFields(medicines, prescription, customerId, date, staffName);
        }

        if (!isSuccessfulUpdate) {
            return;
        }

        Storage storage = Storage.getInstance();
        storage.saveData(medicines);
    }

    /**
     * Processes name and quantity field provided by user for updating prescription information.
     *
     * @param medicines    Arraylist of all medicines.
     * @param prescription The associated prescription object.
     * @param customerId   CustomerId of customers.
     * @param date         Date of prescription.
     * @param staffName    Staff responsible for the prescription of medication.
     * @return Boolean value true if update is successful.
     */
    private boolean processGivenNameAndQuantity(ArrayList<Medicine> medicines, Prescription prescription,
                                                String customerId, Date date, String staffName) {
        Ui ui = Ui.getInstance();
        StockValidator stockValidator = new StockValidator();
        String currentName = prescription.getMedicineName();
        int currentStockId = prescription.getStockId();
        String updatedName = parameters.get(CommandParameters.NAME);

        Stock targetRestoreStock = StockManager.extractStockObject(medicines, currentName, currentStockId);
        if (targetRestoreStock == null) {
            ui.print("Medicine not found in stock");
            return false;
        }

        int restoreStockQuantity = targetRestoreStock.getQuantity();
        int restoredQuantity = prescription.getQuantity();
        int totalQuantity = restoredQuantity + restoreStockQuantity;
        int restoreMaxQuantity = targetRestoreStock.getMaxQuantity();
        boolean isValidRestore = stockValidator.quantityValidityChecker(ui, totalQuantity, restoreMaxQuantity);
        if (!isValidRestore) {
            ui.print("Restoring of medication aborted!");
            return false;
        }

        ArrayList<Stock> targetPrescriptionStocks = StockManager.getUnexpiredFilteredStocksByName(medicines,
                updatedName);
        if (targetPrescriptionStocks.isEmpty()) {
            ui.print("Action aborted! Either medication not found or stock has expired.");
            return false;
        }

        String updatedQuantity = parameters.get(CommandParameters.QUANTITY);
        int prescriptionQuantity = Integer.parseInt(updatedQuantity);
        int availableQuantity = StockManager.getTotalStockQuantity(medicines, updatedName);
        boolean isValidPrescription = stockValidator.quantityValidityChecker(ui, prescriptionQuantity,
                availableQuantity);
        if (!isValidPrescription) {
            ui.print("Prescription of medication aborted!");
            return false;
        }

        // Guarantee is be able to restore & prescribe
        PrescriptionManager.restoreStock(targetRestoreStock, totalQuantity);
        ArrayList<Prescription> updatedPrescriptions = PrescriptionManager.prescribeStock(medicines,
                targetPrescriptionStocks, prescriptionQuantity, customerId, date, staffName);

        // Add to prescription
        for (Prescription updatedPrescription : updatedPrescriptions) {
            medicines.add(updatedPrescription);
        }
        medicines.remove(prescription);

        ui.print("Restored " + restoredQuantity + " " + targetRestoreStock.getMedicineName());
        ui.print("Updated prescription information!");
        ui.printPrescriptions(updatedPrescriptions);
        return true;
    }

    /**
     * Processes name field provided by user for updating prescription information.
     *
     * @param medicines    Arraylist of all medicines.
     * @param prescription The associated prescription object.
     * @param customerId   CustomerId of customers.
     * @param date         Date of prescription.
     * @param staffName    Staff responsible for the prescription of medication.
     * @return Boolean value true if update is successful.
     */
    private boolean processGivenName(ArrayList<Medicine> medicines, Prescription prescription, String customerId,
                                     Date date, String staffName) {
        Ui ui = Ui.getInstance();
        StockValidator stockValidator = new StockValidator();
        String currentName = prescription.getMedicineName();
        int currentStockId = prescription.getStockId();
        String updatedName = parameters.get(CommandParameters.NAME);
        Stock targetRestoreStock = StockManager.extractStockObject(medicines, currentName, currentStockId);
        if (targetRestoreStock == null) {
            ui.print("Medicine not found in stock");
            return false;
        }
        int restoreStockQuantity = targetRestoreStock.getQuantity();
        int restoredQuantity = prescription.getQuantity();
        int totalQuantity = restoredQuantity + restoreStockQuantity;
        int restoreMaxQuantity = targetRestoreStock.getMaxQuantity();
        boolean isValidRestore = stockValidator.quantityValidityChecker(ui, totalQuantity, restoreMaxQuantity);
        if (!isValidRestore) {
            ui.print("Restoring of medication aborted!");
            return false;
        }

        ArrayList<Stock> targetPrescriptionStocks = StockManager.getUnexpiredFilteredStocksByName(medicines,
                updatedName);
        if (targetPrescriptionStocks.isEmpty()) {
            ui.print("Action aborted! Either medication not found or stock has expired.");
            return false;
        }
        int availableQuantity = StockManager.getTotalStockQuantity(medicines, updatedName);
        boolean isValidPrescription = stockValidator.quantityValidityChecker(ui, restoredQuantity, availableQuantity);
        if (!isValidPrescription) {
            ui.print("Prescription of medication aborted!");
            return false;
        }

        // Guarantee to be able to restore & prescribe
        PrescriptionManager.restoreStock(targetRestoreStock, totalQuantity);
        ArrayList<Prescription> updatedPrescriptions = PrescriptionManager.prescribeStock(medicines,
                targetPrescriptionStocks, restoredQuantity, customerId, date, staffName);

        // Add to prescription
        for (Prescription updatedPrescription : updatedPrescriptions) {
            medicines.add(updatedPrescription);
        }
        medicines.remove(prescription);
        ui.print("Restored " + restoredQuantity + " " + targetRestoreStock.getMedicineName());
        ui.print("Updated prescription information!");
        ui.printPrescriptions(updatedPrescriptions);
        return true;
    }

    /**
     * Processes quantity field provided by user for updating prescription information.
     *
     * @param medicines    Arraylist of all medicines.
     * @param prescription The associated prescription object.
     * @param customerId   CustomerId of customers.
     * @param date         Date of prescription.
     * @param staffName    Staff responsible for the prescription of medication.
     * @return Boolean value true if update is successful.
     */
    private boolean processGivenQuantity(ArrayList<Medicine> medicines, Prescription prescription, String customerId,
                                         Date date, String staffName) {
        int currentQuantity = prescription.getQuantity();
        int updatedQuantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
        boolean isSuccessful = false;
        if (updatedQuantity < currentQuantity) {
            isSuccessful = processRestoration(medicines, prescription, customerId, date, staffName);
        } else if (updatedQuantity > currentQuantity) {
            isSuccessful = processPrescription(medicines, prescription, customerId, date, staffName);
        }
        if (!isSuccessful) {
            return false;
        }
        return true;
    }

    /**
     * Process prescription of medication for a prescription record.
     *
     * @param medicines    Arraylist of all medicines.
     * @param prescription The associated prescription object.
     * @param customerId   CustomerId of customers.
     * @param date         Date of prescription.
     * @param staffName    Staff responsible for the prescription of medication.
     * @return Boolean value true if prescription is successful.
     */
    private boolean processPrescription(ArrayList<Medicine> medicines, Prescription prescription, String customerId,
                                        Date date, String staffName) {
        Ui ui = Ui.getInstance();

        StockValidator stockValidator = new StockValidator();
        String currentName = prescription.getMedicineName();
        int currentQuantity = prescription.getQuantity();
        int stockId = prescription.getStockId();
        int updatedQuantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));

        ArrayList<Stock> targetPrescriptionStocks = StockManager.getUnexpiredFilteredStocksByName(medicines,
                currentName);
        if (targetPrescriptionStocks.isEmpty()) {
            ui.print("Action aborted! Either medication not found or stock has expired.");
            return false;
        }

        int prescribedQuantity = updatedQuantity - currentQuantity;
        int stockQuantity = StockManager.getTotalStockQuantity(medicines, currentName);
        boolean isValidPrescription = stockValidator.quantityValidityChecker(ui, prescribedQuantity, stockQuantity);
        if (!isValidPrescription) {
            ui.print("Prescription of medication aborted!");
            return false;
        }

        // guarantee can prescribe
        ArrayList<Prescription> updatedPrescriptions = PrescriptionManager.prescribeStock(medicines,
                targetPrescriptionStocks, prescribedQuantity, customerId, date, staffName);

        medicines.remove(prescription);
        for (Prescription updatedPrescription : updatedPrescriptions) {
            if (updatedPrescription.getStockId() == stockId) {
                int newQuantity = currentQuantity + updatedPrescription.getQuantity();
                updatedPrescription.setQuantity(newQuantity);
                break;
            }
        }

        // Add to prescription
        for (Prescription updatedPrescription : updatedPrescriptions) {
            medicines.add(updatedPrescription);
        }
        ui.print("Updated prescription information!");
        ui.printPrescriptions(updatedPrescriptions);
        return true;
    }

    /**
     * Process restoration for a prescription record.
     *
     * @param medicines    Arraylist of all medicines.
     * @param prescription The associated prescription object.
     * @param customerId   CustomerId of customers.
     * @param date         Date of prescription.
     * @param staffName    Staff responsible for the prescription of medication.
     * @return Boolean value true if restoration is successful.
     */
    private boolean processRestoration(ArrayList<Medicine> medicines, Prescription prescription, String customerId,
                                       Date date, String staffName) {
        Ui ui = Ui.getInstance();
        StockValidator stockValidator = new StockValidator();
        String currentName = prescription.getMedicineName();
        int currentStockId = prescription.getStockId();
        int currentQuantity = prescription.getQuantity();
        int updatedQuantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
        Stock stock = StockManager.extractStockObject(medicines, currentName, currentStockId);
        if (stock == null) {
            ui.print("Medicine not found in stock");
            return false;
        }

        int restoreQuantity = currentQuantity - updatedQuantity;
        int stockQuantity = stock.getQuantity();
        int stockMaxQuantity = stock.getMaxQuantity();
        int totalQuantity = stockQuantity + restoreQuantity;
        boolean isValidRestore = stockValidator.quantityValidityChecker(ui, totalQuantity, stockMaxQuantity);
        if (!isValidRestore) {
            ui.print("Restoring of medication aborted!");
            return false;
        }

        // guarantee can restore
        int stockId = prescription.getStockId();
        PrescriptionManager.restoreStock(stock, totalQuantity);

        medicines.remove(prescription);
        Prescription newPrescription = new Prescription(currentName, updatedQuantity, customerId, date, staffName,
                stockId);
        medicines.add(newPrescription);

        ArrayList<Prescription> updatedPrescriptions = new ArrayList<>();
        updatedPrescriptions.add(newPrescription);
        ui.print("Restored " + restoreQuantity + " " + stock.getMedicineName());
        ui.print("Updated prescription information!");
        ui.printPrescriptions(updatedPrescriptions);
        return true;
    }

    /**
     * Processes other fields provided by user for updating prescription information.
     * The other field are the customerId, date and staffName.
     *
     * @param medicines    Arraylist of all medicines.
     * @param prescription The associated prescription object.
     * @param customerId   CustomerId of customers.
     * @param date         Date of prescription.
     * @param staffName    Staff responsible for the prescription of medication.
     * @return Boolean value true if update is successful.
     */
    private boolean processOtherFields(ArrayList<Medicine> medicines, Prescription prescription, String customerId,
                                       Date date, String staffName) {
        if (prescription == null) {
            return false;
        }
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Prescription)) {
                continue;
            }
            Prescription targetPrescription = (Prescription) medicine;
            boolean isSameStockId = targetPrescription.getStockId() == prescription.getStockId();
            if (isSameStockId) {
                ((Prescription) medicine).setCustomerId(customerId);
                ((Prescription) medicine).setDate(date);
                ((Prescription) medicine).setStaff(staffName);
                break;
            }
        }
        ArrayList<Prescription> newPrescriptions = new ArrayList<>();
        newPrescriptions.add(prescription);
        Ui ui = Ui.getInstance();
        ui.print("Updated prescription information!");
        ui.printPrescriptions(newPrescriptions);
        return true;
    }

}
