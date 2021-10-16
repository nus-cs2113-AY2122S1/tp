package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Order;
import parser.DateParser;
import parser.OrderManager;
import parser.OrderValidator;
import storage.Storage;
import ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Update medication information based on user input given order id.
 */

public class UpdateOrder extends Command {
    private static Logger logger = Logger.getLogger("UpdateOrder");

    @Override
    public void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines, Storage storage) {
        logger.log(Level.INFO, "Start of UpdateOrder command execution.");
        String[] requiredParameter = {CommandParameters.ID};
        String[] optionalParameters = {CommandParameters.NAME, CommandParameters.QUANTITY, CommandParameters.DATE};

        boolean isInvalidParameter = CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameter,
                optionalParameters, CommandSyntax.UPDATE_ORDER_COMMAND, true);
        if (isInvalidParameter) {
            return;
        }

        boolean isInvalidParameterValues = OrderValidator.containsInvalidParameterValues(ui, parameters, medicines,
                CommandSyntax.UPDATE_ORDER_COMMAND);
        if (isInvalidParameterValues) {
            return;
        }

        Order order = OrderManager.extractOrderObject(parameters, medicines);
        if (order.isDelivered()) {
            ui.print("Update aborted! Unable to update order that has been delivered");
            return;
        }

        ArrayList<Order> filteredOrders = new ArrayList<>();
        for (Medicine medicine : medicines) {
            boolean isOrderInstance = medicine instanceof Order;
            if (isOrderInstance) {
                boolean isSameName = medicine.getMedicineName().equalsIgnoreCase(order.getMedicineName());
                boolean isPending = !(((Order) medicine).isDelivered());
                if (isSameName && isPending) {
                    filteredOrders.add((Order) medicine);
                }
            }
        }

        // Default value for updating all affected rows
        int rowsAffected = filteredOrders.size();
        if (!parameters.containsKey(CommandParameters.NAME)) {
            filteredOrders.clear();
            filteredOrders.add(order);
            rowsAffected = filteredOrders.size();
        }

        setUpdatesByOrderId(parameters, filteredOrders, order);
        ui.print("Updated! Number of rows affected: " + rowsAffected);
        ui.printOrders(filteredOrders);
        logger.log(Level.INFO, "End of UpdateOrder command execution.");
    }

    /**
     * Update values provided by user for a given order id.
     *
     * @param parameters     LinkedHashMap Key-Value set for parameter and user specified parameter value.
     * @param filteredOrders Arraylist of filtered medicine orders.
     * @param order          Order object of the given order id.
     */
    private void setUpdatesByOrderId(LinkedHashMap<String, String> parameters, ArrayList<Order> filteredOrders,
                                     Order order) {
        logger.log(Level.INFO, "Attempt to update order information.");
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.NAME:
                for (Order targetOrder : filteredOrders) {
                    targetOrder.setMedicineName(parameterValue);
                }
                break;
            case CommandParameters.QUANTITY:
                order.setQuantity(Integer.parseInt(parameterValue));
                break;
            case CommandParameters.DATE:
                try {
                    order.setDate(DateParser.stringToDate(parameterValue));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
            }
        }
        logger.log(Level.INFO, "Updated order information with given user input.");
    }

}
