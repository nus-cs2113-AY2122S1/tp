package command.order;

import command.Command;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Order;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Helps to process the list command together with filters and sort.
 */

public class ListOrder extends Command {
    private static Logger logger = Logger.getLogger("ListOrder");

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        logger.log(Level.INFO, "Start listing of order");

        // For now no optional parameters only listorder
        // Will do after I finish storage
        String[] requiredParameters = {};
        String[] optionalParameters = {};

        boolean isInvalidParameter = CommandSyntax.containsInvalidParameters(ui, parameters,
                requiredParameters, optionalParameters, CommandSyntax.LIST_ORDER_COMMAND, false);
        if (isInvalidParameter) {
            logger.log(Level.WARNING, "Invalid parameters given by user");
            return;
        }

        ArrayList<Order> filteredOrders = new ArrayList<>();

        assert (filteredOrders != null) : "Array is not initialised";

        for (Medicine medicine : medicines) {
            if (medicine instanceof Order) { // Ensure that it is a medicine object
                filteredOrders.add((Order) medicine);
            }
        }

        // To implement validation on filteredOrders
        // Will do after I finish storage

        ui.printOrders(filteredOrders);
        logger.log(Level.INFO, "Successful listing of order");
    }
}
