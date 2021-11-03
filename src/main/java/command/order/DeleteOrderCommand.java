package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Order;
import utilities.parser.MedicineValidator;
import utilities.parser.OrderValidator;
import utilities.ui.Ui;
import utilities.storage.Storage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author deonchung

/**
 * Delete order based on user input given order id.
 */
public class DeleteOrderCommand extends Command {
    private static Logger logger = Logger.getLogger("Delete Order");

    public DeleteOrderCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start deletion of order");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();
        String orderIdToDelete = parameters.get(CommandParameters.ID);

        if (!isValidOrderParameters(ui, medicines, orderIdToDelete)) {
            return;
        }

        int orderId = Integer.parseInt(orderIdToDelete);

        assert orderId <= Order.getOrderCount() : "order Id should not exceed max order count";

        for (Medicine medicine : medicines) {

            if (!(medicine instanceof Order)) {
                continue;
            }

            Order order = (Order) medicine;

            if (order.getOrderId() == orderId) {
                medicines.remove(order);
                logger.log(Level.INFO, "Order id found and deleted");
                break;
            }
        }

        ui.print("Order deleted for Order ID " + orderId);
        Storage storage = Storage.getInstance();
        storage.saveData(medicines);
        logger.log(Level.INFO, "Successful deletion of order");

    }

    /**
     * Check if parameters values for Order are valid and if Order ID exist.
     *
     * @param ui              Reference to the UI object to print messages.
     * @param medicines       Arraylist of medicines
     * @param orderIdToDelete Order ID to delete.
     * @return Boolean Value indicating if parameters values for Order are valid and Order ID exist.
     */
    private boolean isValidOrderParameters(Ui ui, ArrayList<Medicine> medicines, String orderIdToDelete) {

        MedicineValidator validator = new OrderValidator();

        String[] requiredParameters = {CommandParameters.ID};
        String[] optionalParameters = {};

        boolean isInvalidInput = validator.containsInvalidParametersAndValues(ui, medicines, parameters,
                requiredParameters, optionalParameters, CommandSyntax.DELETE_ORDER_COMMAND, false, validator);

        if (isInvalidInput) {
            logger.log(Level.WARNING, "Invalid parameter or value specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of order");
            return false;
        }

        return true;
    }
}

