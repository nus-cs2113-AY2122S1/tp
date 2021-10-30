package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import command.stock.AddStockCommand;
import inventory.Medicine;
import inventory.Order;
import inventory.Stock;
import utilities.parser.DateParser;
import utilities.parser.OrderValidator;
import utilities.parser.StockValidator;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author alvintan01

/**
 * Helps to add an order to stocks and mark the order as delivered.
 */

public class ReceiveOrderCommand extends Command {
    private static Logger logger = Logger.getLogger("ReceiveOrder");

    public ReceiveOrderCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        if (!checkOrderIdExist(ui, medicines)) {
            return;
        }
        int orderId = Integer.parseInt(parameters.get(CommandParameters.ID));
        parameters.remove(CommandParameters.ID); // Remove ID to prevent checks against stock ID
        String name = "";
        Order existingOrder = null;

        for (Medicine medicine : medicines) {
            if (medicine instanceof Order && ((Order) medicine).getOrderId() == orderId
                    && !((Order) medicine).isDelivered()) {
                existingOrder = (Order) medicine; // Found existing order, add to parameters
                name = existingOrder.getMedicineName();
                parameters.put(CommandParameters.NAME, name);
                parameters.put(CommandParameters.QUANTITY, String.valueOf(existingOrder.getQuantity()));
                break;
            }
        }

        if (!isStockParametersValid(ui, medicines, name)) {
            return;
        }

        new AddStockCommand(parameters).execute(); // Add to stock

        assert (existingOrder != null) : "Order object is not initialised!";
        boolean expiryExist = isExpiryExist(medicines, name);

        if (expiryExist) {
            existingOrder.setDelivered();
            return;
        }

        int currentStockCount = Stock.getStockCount();

        if (Stock.getStockCount() == currentStockCount + 1) { // The stock was added
            existingOrder.setDelivered(); // Set order as completed
        }
    }

    /**
     * Check if same medication name and expiry date exist.
     *
     * @param medicines Arraylist of all medicines.
     * @param name Medication name to be searched.
     * @return Boolean value indicating if the same medication name and same expiry date exists.
     */
    private boolean isExpiryExist(ArrayList<Medicine> medicines, String name) {
        boolean expiryExist = false;
        String expiryToAdd = parameters.get(CommandParameters.EXPIRY_DATE);
        Date formatExpiry = null;
        try {
            formatExpiry = DateParser.stringToDate(expiryToAdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock && medicine.getMedicineName().equalsIgnoreCase(name)
                    && !((Stock) medicine).isDeleted() && ((Stock) medicine).getExpiry().equals(formatExpiry)) {
                expiryExist = true;
                break;
            }
        }
        return expiryExist;
    }

    /**
     * Checks if a medication exists in stock.
     *
     * @param medicines Arraylist of all medicines.
     * @param name      Medication name to be searched.
     * @return Boolean value indicating if the stock exists.
     */
    private boolean checkStockExist(ArrayList<Medicine> medicines, String name) {
        // Search if current medication exist in stock
        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock && medicine.getMedicineName().equalsIgnoreCase(name)
                    && !((Stock) medicine).isDeleted()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an order ID exists and is not delivered.
     *
     * @param ui        Reference to UI object to print messages.
     * @param medicines Arraylist of all medicines.
     * @return Boolean value indicating if order ID is valid.
     */
    private boolean checkOrderIdExist(Ui ui, ArrayList<Medicine> medicines) {
        OrderValidator orderValidator = new OrderValidator();
        String[] orderRequiredParameters = {CommandParameters.ID};
        String[] optionalParameters = {};

        boolean orderIdNotProvided = orderValidator.containsInvalidParameters(ui, parameters,
                orderRequiredParameters, optionalParameters, CommandSyntax.RECEIVE_ORDER_COMMAND, false);
        if (orderIdNotProvided) {
            logger.log(Level.WARNING, "Order id is not specified by user!");
            return false;
        }
        LinkedHashMap<String, String> orderParameters = new LinkedHashMap<>();
        orderParameters.put(CommandParameters.ID, parameters.get(CommandParameters.ID)); // Only check for Order ID

        boolean isInvalidOrderId = orderValidator.containsInvalidParameterValues(ui, orderParameters,
                medicines, CommandSyntax.RECEIVE_ORDER_COMMAND);
        if (isInvalidOrderId) {
            logger.log(Level.WARNING, "Invalid order id specified by user!");
            return false;
        }

        int orderId = Integer.parseInt(parameters.get(CommandParameters.ID));
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Order)) {
                continue;
            }
            Order order = (Order) medicine;
            if (order.getOrderId() == orderId) {
                if (order.isDelivered()) {
                    ui.print("Order is already delivered!");
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helps to ensure that the parameters values for Stock are valid.
     *
     * @param ui        Reference to UI object to print messages.
     * @param medicines Arraylist of all medicines.
     * @param name      Medication name to be searched.
     * @return Boolean value indicating if Stock parameters are valid.
     */
    private boolean isStockParametersValid(Ui ui, ArrayList<Medicine> medicines, String name) {
        StockValidator stockValidator = new StockValidator();
        String[] optionalParameters = {};
        if (checkStockExist(medicines, name)) {
            String[] requiredParameters = {CommandParameters.PRICE, CommandParameters.EXPIRY_DATE};
            boolean isInvalidParameters = stockValidator.containsInvalidParameters(ui, parameters, requiredParameters,
                    optionalParameters, CommandSyntax.RECEIVE_ORDER_COMMAND, false);
            if (isInvalidParameters) {
                logger.log(Level.WARNING, "Invalid parameter is specified by user");
                return false;
            }
        } else {
            String[] requiredParameters = {CommandParameters.PRICE, CommandParameters.EXPIRY_DATE,
                    CommandParameters.DESCRIPTION, CommandParameters.MAX_QUANTITY};
            boolean isInvalidParameters = stockValidator.containsInvalidParameters(ui, parameters, requiredParameters,
                    optionalParameters, CommandSyntax.RECEIVE_ORDER_COMMAND, false);
            if (isInvalidParameters) {
                logger.log(Level.WARNING, "Invalid parameter is specified by user");
                return false;
            }
        }

        boolean isInvalidParameterValues = stockValidator.containsInvalidParameterValues(ui, parameters,
                medicines, CommandSyntax.RECEIVE_ORDER_COMMAND);
        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameters values given by user!");
            return false;
        }
        return true;
    }
}