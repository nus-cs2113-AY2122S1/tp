package command.prescription;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Prescription;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.DateParser;
import utilities.parser.PrescriptionManager;
import utilities.parser.PrescriptionValidator;
import utilities.parser.StockManager;
import utilities.parser.StockValidator;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.text.ParseException;
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

        String[] requiredParameter = {CommandParameters.ID};
        String[] optionalParameters = {CommandParameters.NAME, CommandParameters.QUANTITY,
                CommandParameters.CUSTOMER_ID, CommandParameters.STAFF, CommandParameters.DATE};

        PrescriptionValidator prescriptionValidator = new PrescriptionValidator();
        boolean isInvalidParameter = prescriptionValidator.containsInvalidParameters(ui, parameters, requiredParameter,
                optionalParameters, CommandSyntax.UPDATE_PRESCRIPTION_COMMAND, true);
        if (isInvalidParameter) {
            return;
        }

        ArrayList<Medicine> medicines = Medicine.getInstance();
        boolean isInvalidParameterValues = prescriptionValidator.containsInvalidParameterValues(ui, parameters,
                medicines,
                CommandSyntax.UPDATE_PRESCRIPTION_COMMAND);
        if (isInvalidParameterValues) {
            return;
        }

        Prescription prescription = PrescriptionManager.extractPrescriptionObject(parameters, medicines);
        assert (prescription != null) : "Prescription object should not be null";
        boolean hasNameParam = parameters.containsKey(CommandParameters.NAME);
        if (hasNameParam) {
            String currentName = prescription.getMedicineName();
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
            date = prescription.getDate();
        }
        assert date != null : "Date should not be null";

        boolean hasCustomerParam = parameters.containsKey(CommandParameters.CUSTOMER_ID);
        if (hasCustomerParam) {
            customerId = parameters.get(CommandParameters.CUSTOMER_ID);
        } else {
            customerId = prescription.getCustomerId();
        }

        boolean hasStaffParam = parameters.containsKey(CommandParameters.STAFF);
        if (hasStaffParam) {
            staffName = parameters.get(CommandParameters.STAFF);
        } else {
            staffName = prescription.getStaff();
        }

        StockValidator stockValidator = new StockValidator();
        boolean hasQuantityParam = parameters.containsKey(CommandParameters.QUANTITY);
        boolean isSuccessfulUpdate = false;
        if (hasNameParam && hasQuantityParam) {
            isSuccessfulUpdate = processGivenNameAndQuantity(medicines, prescription, customerId, date, staffName,
                    stockValidator);
        } else if (hasNameParam && !hasQuantityParam) {
            isSuccessfulUpdate = processGivenName(medicines, prescription, customerId, date, staffName, stockValidator);
        } else if (!hasNameParam && hasQuantityParam) {
            isSuccessfulUpdate = processGivenQuantity(medicines, prescription, customerId, date, staffName,
                    stockValidator);
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
     * @param medicines      Arraylist of all medicines.
     * @param prescription   The associated prescription object.
     * @param customerId     CustomerId of customers.
     * @param date           Date of prescription.
     * @param staffName      Staff responsible for the prescription of medication.
     * @param stockValidator Reference to StockValidator object.
     * @return Boolean value true if update is successful.
     */
    private boolean processGivenNameAndQuantity(ArrayList<Medicine> medicines, Prescription prescription,
                                                String customerId,
                                                Date date, String staffName, StockValidator stockValidator) {
        Ui ui = Ui.getInstance();
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

        ArrayList<Stock> targetPrescriptionStocks = StockManager.getFilteredStocksByName(medicines, updatedName);
        if (targetPrescriptionStocks.isEmpty()) {
            ui.print("Medicine name does not exist in stock!");
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
        // Guarantee is be able to restore & prescription
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
     * @param medicines      Arraylist of all medicines.
     * @param prescription   The associated prescription object.
     * @param customerId     CustomerId of customers.
     * @param date           Date of prescription.
     * @param staffName      Staff responsible for the prescription of medication.
     * @param stockValidator Reference to StockValidator object.
     * @return Boolean value true if update is successful.
     */
    private boolean processGivenName(ArrayList<Medicine> medicines, Prescription prescription, String customerId,
                                     Date date,
                                     String staffName, StockValidator stockValidator) {
        Ui ui = Ui.getInstance();
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

        ArrayList<Stock> targetPrescriptionStocks = StockManager.getFilteredStocksByName(medicines, updatedName);
        if (targetPrescriptionStocks.isEmpty()) {
            ui.print("Medicine name does not exist in stock!");
            return false;
        }
        int availableQuantity = StockManager.getTotalStockQuantity(medicines, updatedName);
        boolean isValidPrescription = stockValidator.quantityValidityChecker(ui, restoredQuantity, availableQuantity);
        if (!isValidPrescription) {
            ui.print("Prescription of medication aborted!");
            return false;
        }

        // Guarantee to be able to restore & prescription
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
     * @param medicines      Arraylist of all medicines.
     * @param prescription   The associated prescription object.
     * @param customerId     CustomerId of customers.
     * @param date           Date of prescription.
     * @param staffName      Staff responsible for the prescription of medication.
     * @param stockValidator Reference to StockValidator object.
     * @return Boolean value true if update is successful.
     */
    private boolean processGivenQuantity(ArrayList<Medicine> medicines, Prescription prescription, String customerId,
                                         Date date, String staffName, StockValidator stockValidator) {
        Ui ui = Ui.getInstance();
        String currentName = prescription.getMedicineName();
        int currentStockId = prescription.getStockId();
        int currentQuantity = prescription.getQuantity();
        int stockId = prescription.getStockId();
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
            boolean isValidRestore = stockValidator.quantityValidityChecker(ui, totalQuantity, stockMaxQuantity);
            if (!isValidRestore) {
                ui.print("Restoring of medication aborted!");
                return false;
            }
            // guarantee can restore
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

        } else if (updatedQuantity > currentQuantity) {
            ArrayList<Stock> targetPrescriptionStocks = StockManager.getFilteredStocksByName(medicines, currentName);
            if (targetPrescriptionStocks.isEmpty()) {
                ui.print("Medicine name does not exist in stock!");
                return false;
            }
            int prescribedQuantity = updatedQuantity - currentQuantity;
            int stockQuantity = StockManager.getTotalStockQuantity(medicines, currentName);
            boolean isValidPrescription = stockValidator.quantityValidityChecker(ui, prescribedQuantity, stockQuantity);
            if (!isValidPrescription) {
                ui.print("Prescription of medication aborted!");
                return false;
            }
            // guarantee can prescription
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
        }
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
                                       Date date,
                                       String staffName) {
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
