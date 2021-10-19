package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Order;
import inventory.Stock;
import utilities.parser.DateParser;
import utilities.parser.OrderManager;
import utilities.parser.OrderValidator;
import utilities.parser.StockManager;
import utilities.parser.StockValidator;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Update medication information based on user input given order id.
 */

public class UpdateOrderCommand extends Command {
    private static Logger logger = Logger.getLogger("UpdateOrder");

    public UpdateOrderCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start of UpdateOrder command execution.");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();
        Storage storage = Storage.getInstance();

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

        int maxQuantity = StockManager.getMaxStockQuantity(medicines, order.getMedicineName());
        assert maxQuantity >= 0 : "Max quantity must not be less than 0";
        boolean existName = maxQuantity > 0;
        boolean existQuantityParam = parameters.containsKey(CommandParameters.QUANTITY);
        int actualTotalQuantity = 0;

        if (existName && existQuantityParam) {
            int totalQuantity = OrderManager.getTotalOrderQuantity(medicines, order.getMedicineName());
            int orderQuantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
            actualTotalQuantity = totalQuantity - order.getQuantity() + orderQuantity;
            boolean isValidQuantity = StockValidator.quantityValidityChecker(ui, actualTotalQuantity, maxQuantity);
            if (!isValidQuantity) {
                return;
            }
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
