package command.prescription;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Prescription;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.PrescriptionValidator;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author deonchung

/**
 * Delete prescription based on user input given prescription id.
 */
public class DeletePrescriptionCommand extends Command {
    private static Logger logger = Logger.getLogger("DeletePrescription");

    public DeletePrescriptionCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start deletion of prescription");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        String prescriptionIdToDelete = parameters.get(CommandParameters.ID);

        if (isValidPrescriptionParameters(ui, medicines, prescriptionIdToDelete)) {
            return;
        }

        int prescriptionId = Integer.parseInt(prescriptionIdToDelete);

        assert prescriptionId <= Prescription.getPrescriptionCount() : "Prescription Id should not exceed max "
                + "prescription count";

        int stockIdToPrescribe;
        int prescribeQuantity;

        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Prescription)) {
                continue;
            }

            Prescription prescription = (Prescription) medicine;

            if (prescription.getPrescriptionId() == prescriptionId) {
                stockIdToPrescribe = prescription.getStockId();
                prescribeQuantity = prescription.getQuantity();

                if (setStockQuantity(ui, medicines, stockIdToPrescribe, prescribeQuantity)) {
                    return;
                }

                medicines.remove(prescription);
                ui.print("Prescription deleted for Prescription Id " + prescriptionId);
                Storage storage = Storage.getInstance();
                storage.saveData(medicines);
                logger.log(Level.INFO, "Successful deletion of Prescription");
                return;

            }
        }
    }

    /**
     * Check if parameters values for Prescription are valid and if Prescription ID exist.
     *
     * @param ui                     Reference to the UI object to print messages.
     * @param medicines              Arraylist of medicines.
     * @param prescriptionIdToDelete Prescription ID to delete.
     * @return Boolean Value indicating if parameters values for Prescription are valid and Prescription ID exist.
     */
    private boolean isValidPrescriptionParameters(Ui ui, ArrayList<Medicine> medicines, String prescriptionIdToDelete) {

        PrescriptionValidator prescriptionValidator = new PrescriptionValidator();
        String[] requiredParameters = {CommandParameters.ID};
        String[] optionalParameters = {};

        boolean isInvalidParameter = prescriptionValidator.containsInvalidParameters(ui, parameters, requiredParameters,
                optionalParameters, CommandSyntax.DELETE_PRESCRIPTION_COMMAND, true);

        if (isInvalidParameter) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of prescription");
            return true;
        }

        boolean isValidPrescriptionId = prescriptionValidator.isValidPrescriptionId(ui, prescriptionIdToDelete,
                medicines);

        if (!isValidPrescriptionId) {
            logger.log(Level.WARNING, "Invalid prescription id is specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of prescription");
            return true;
        }
        return false;
    }

    /**
     * Check stock if stock exist. If stock exist, add the quantity to the stock quantity.
     *
     * @param ui                 Reference to the UI object to print messages.
     * @param medicines          Arraylist of medicines
     * @param stockIdToPrescribe Stock ID prescribe.
     * @param prescribedQuantity Quantity prescribed.
     * @return Boolean value indicating if stock id exist.
     */
    private boolean setStockQuantity(Ui ui, ArrayList<Medicine> medicines, int stockIdToPrescribe,
                                     int prescribedQuantity) {
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            Stock stock = (Stock) medicine;

            if (stock.getStockId() == stockIdToPrescribe) {
                if (stock.isDeleted()) {
                    stock.setDeleted(false);
                }

                int quantityToRestore = stock.getQuantity() + prescribedQuantity;

                if (quantityToRestore > stock.getMaxQuantity()) {
                    ui.print("Unable to delete prescription. Quantity added will exceed maximum quantity.");
                    ui.print("Maximum quantity: " + stock.getMaxQuantity() + " Total Quantity after deletion: "
                            + quantityToRestore);
                    return true;
                }
                stock.setQuantity(quantityToRestore);
            }
        }
        return false;
    }

}

