package command.dispense;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Dispense;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.DispenseValidator;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Delete prescription based on user input given prescription id.
 */
public class DeleteDispenseCommand extends Command {
    private static Logger logger = Logger.getLogger("DeletePrescription");

    public DeleteDispenseCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start deletion of prescription");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        String[] requiredParameters = {CommandParameters.ID};
        String[] optionalParameters = {};

        DispenseValidator dispenseValidator = new DispenseValidator();
        boolean isInvalidParameter = dispenseValidator.containsInvalidParameters(ui, parameters, requiredParameters,
                optionalParameters, CommandSyntax.DELETE_DISPENSE_COMMAND, true);

        if (isInvalidParameter) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of prescription");
            return;
        }
        String prescriptionIdToDelete = parameters.get(CommandParameters.ID);

        boolean isValidPrescriptionId = dispenseValidator.isValidDispenseId(ui, prescriptionIdToDelete, medicines);
        if (!isValidPrescriptionId) {
            logger.log(Level.WARNING, "Invalid prescription id is specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of prescription");
            return;
        }

        int prescriptionId = Integer.parseInt(prescriptionIdToDelete);

        assert prescriptionId <= Dispense.getDispenseCount() : "Prescription Id should not exceed max prescription "
                + "count";

        int stockIdToPrescribe;
        int prescribeQuantity;
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Dispense)) {
                continue;
            }
            Dispense dispense = (Dispense) medicine;
            if (dispense.getDispenseId() == prescriptionId) {
                stockIdToPrescribe = dispense.getStockId();
                prescribeQuantity = dispense.getQuantity();
                if (setStockQuantity(ui, medicines, stockIdToPrescribe, prescribeQuantity)) {
                    return;
                }
                medicines.remove(dispense);
                ui.print("Prescription deleted for Prescription ID " + prescriptionId);
                Storage storage = Storage.getInstance();
                storage.saveData(medicines);
                logger.log(Level.INFO, "Successful deletion of prescription");
                return;
            }
        }
    }

    /**
     * Check stock if stock exist. If stock exist, add the quantity to the stock quantity.
     *
     * @param ui                 Reference to the UI object to print messages.
     * @param medicines          Arraylist of medicines
     * @param stockIdToPrescribe Stock ID prescribed.
     * @param prescribeQuantity  Quantity prescribed.
     * @return Boolean value indicating if stock id exist.
     */
    private boolean setStockQuantity(Ui ui, ArrayList<Medicine> medicines, int stockIdToPrescribe,
                                     int prescribeQuantity) {
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            Stock stock = (Stock) medicine;
            if (stock.isDeleted()) {
                stock.setDeleted(false);
            }
            if (stock.getStockId() == stockIdToPrescribe) {
                int quantityToRestore = stock.getQuantity() + prescribeQuantity;
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

