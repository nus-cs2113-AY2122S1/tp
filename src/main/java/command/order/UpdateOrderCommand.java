package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Order;
import utilities.parser.DateParser;
import utilities.parser.MedicineValidator;
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

//@@author a-tph

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

        String[] requiredParameters = {CommandParameters.ID};
        String[] optionalParameters = {CommandParameters.NAME, CommandParameters.QUANTITY, CommandParameters.DATE};

        MedicineValidator validator = new OrderValidator();
        boolean isInvalidInput = validator.containsInvalidParametersAndValues(ui, medicines, parameters,
                requiredParameters, optionalParameters, CommandSyntax.UPDATE_ORDER_COMMAND, true, validator);
        if (isInvalidInput) {
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

        if (existName && existQuantityParam) {
            boolean isValidQuantity = checkUpdateQuantity(ui, medicines, order, maxQuantity);
            if (!isValidQuantity) {
                return;
            }
        }

        ArrayList<Order> filteredOrders = OrderManager.getFilteredOrdersByName(medicines, order.getMedicineName());

        // Default value for updating all affected rows
        int rowsAffected = filteredOrders.size();
        if (!parameters.containsKey(CommandParameters.NAME)) {
            filteredOrders.clear();
            filteredOrders.add(order);
            rowsAffected = filteredOrders.size();
        }

        setUpdatesByOrderId(filteredOrders, order);
        ui.print("Updated! Number of rows affected: " + rowsAffected);
        ui.printOrders(filteredOrders);
        Storage storage = Storage.getInstance();
        storage.saveData(medicines);
        logger.log(Level.INFO, "End of UpdateOrder command execution.");
    }

    /**
     * Checks if the updated order quantity exceeds the maximum quantity.
     *
     * @param ui          Reference to the UI object to print messages.
     * @param medicines   Arraylist of all medicines.
     * @param order       Order object to be updated.
     * @param maxQuantity Maximum quantity for the provided stock.
     * @return Boolean true if quantity given can be updated.
     */
    private boolean checkUpdateQuantity(Ui ui, ArrayList<Medicine> medicines, Order order, int maxQuantity) {
        int totalQuantity = OrderManager.getTotalOrderQuantity(medicines, order.getMedicineName());
        int orderQuantity = Integer.parseInt(parameters.get(CommandParameters.QUANTITY));
        int actualTotalQuantity = totalQuantity - order.getQuantity() + orderQuantity;
        StockValidator stockValidator = new StockValidator();
        boolean isValidQuantity = stockValidator.quantityValidityChecker(ui, actualTotalQuantity, maxQuantity);
        if (!isValidQuantity) {
            return false;
        }
        return true;
    }

    /**
     * Update values provided by user for a given order id.
     *
     * @param filteredOrders Arraylist of filtered medicine orders.
     * @param order          Order object of the given order id.
     */
    private void setUpdatesByOrderId(ArrayList<Order> filteredOrders, Order order) {
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
