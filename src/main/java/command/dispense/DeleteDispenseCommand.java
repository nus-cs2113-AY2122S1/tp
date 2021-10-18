package command.dispense;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Dispense;
import inventory.Medicine;
import inventory.Stock;
import parser.DispenseValidator;

import storage.Storage;
import ui.Ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Delete dispense based on user input given dispense id.
 */
public class DeleteDispenseCommand extends Command {
    private static Logger logger = Logger.getLogger("DeleteDispense");

    @Override
    public void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines,
                        Storage storage) {
        logger.log(Level.INFO, "Start deletion of dispense");

        String[] requiredParameters = {CommandParameters.ID};
        String[] optionalParameters = {};

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters, optionalParameters,
                CommandSyntax.DELETE_DISPENSE_COMMAND, true)) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of dispense");
            return;
        }
        String dispenseIdToDelete = parameters.get(CommandParameters.ID);

        if (!DispenseValidator.isValidDispenseId(ui, dispenseIdToDelete, medicines)) {
            logger.log(Level.WARNING, "Invalid dispense id is specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of dispense");
            return;
        }

        int dispenseId = Integer.parseInt(dispenseIdToDelete);

        assert dispenseId <= Dispense.getDispenseCount() : "Dispense Id should not exceed max dispense count";
        
        int stockIdToDispense;
        int dispenseQuantity;
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Dispense)) {
                continue;
            }
            Dispense dispense = (Dispense) medicine;
            if (dispense.getDispenseId() == dispenseId) {
                stockIdToDispense = dispense.getStockId();
                dispenseQuantity = dispense.getQuantity();
                if (setStockQuantity(ui, medicines, stockIdToDispense, dispenseQuantity)) {
                    return;
                }
                medicines.remove(dispense);
                return;
            }
        }

        ui.print("Dispense deleted for Dispense Id " + dispenseId);
        logger.log(Level.INFO, "Successful deletion of Dispense");

    }

    /**
     * Check stock if stock exist. If stock exist, add the quantity to the stock quantity.
     *
     * @param ui                Reference to the UI object passed by Main to print messages.
     * @param medicines         Arraylist of medicines
     * @param stockIdToDispense Stock ID dispensed.
     * @param dispenseQuantity  Quantity dispensed.
     * @return Boolean value indicating if stock id exist.
     */
    private boolean setStockQuantity(Ui ui, ArrayList<Medicine> medicines, int stockIdToDispense,
                                     int dispenseQuantity) {
        for (Medicine med : medicines) {
            if (!(med instanceof Stock)) {
                continue;
            }
            Stock stock = (Stock) med;
            if (stock.getStockID() == stockIdToDispense) {
                int quantityToRestore = stock.getQuantity() + dispenseQuantity;
                if (quantityToRestore > stock.getMaxQuantity()) {
                    ui.print("Unable to delete dispense. Quantity added will exceed maximum quantity.");
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

