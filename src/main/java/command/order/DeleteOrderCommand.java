package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Order;
import parser.OrderValidator;
import ui.Ui;
import storage.Storage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Delete order based on user input given order id.
 */
public class DeleteOrderCommand extends Command {
    private static Logger logger = Logger.getLogger("Delete Order");

    @Override
    public void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines,
                        Storage storage) {
        logger.log(Level.INFO, "Start deletion of order");

        String[] requiredParameters = {CommandParameters.ID};
        String[] optionalParameters = {};

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters, optionalParameters,
                CommandSyntax.DELETE_ORDER_COMMAND, true)) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of order");
            return;
        }

        String orderIdToDelete = parameters.get(CommandParameters.ID);

        if (!OrderValidator.isValidOrderId(ui, orderIdToDelete, medicines)) {
            logger.log(Level.WARNING, "Invalid order id is specified by user");
            logger.log(Level.INFO, "Unsuccessful deletion of order");
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
        ui.print("Order deleted for Order Id " + orderId);
        logger.log(Level.INFO, "Successful deletion of order");

    }
}

