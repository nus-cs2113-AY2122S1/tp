package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Order;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.DateParser;
import utilities.parser.OrderManager;
import utilities.parser.OrderValidator;
import utilities.parser.StockManager;
import utilities.storage.Storage;

import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author jiangweichen835
/**
 * Add order for medication based on user input.
 * User input include medication name, quantity and the order date.
 */

public class AddOrderCommand extends Command {
    private static Logger logger = Logger.getLogger("AddOrder");

    public AddOrderCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start addition of orders");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();
        String[] requiredParameters = {CommandParameters.NAME, CommandParameters.QUANTITY};
        String[] optionalParameter = {CommandParameters.DATE};

        OrderValidator orderValidator = new OrderValidator();
        if (checkValidParameterValues(ui,parameters, medicines, requiredParameters, optionalParameter,
                orderValidator)) {
            return;
        }

        boolean nameExistsInOrder = false;
        boolean nameExistsInStock = false;
        String nameToAdd = parameters.get(CommandParameters.NAME);
        String quantityToAdd = parameters.get(CommandParameters.QUANTITY);
        int orderQuantity = Integer.parseInt(quantityToAdd);
        String dateToAdd = parameters.get(CommandParameters.DATE);
        int maxQuantity = Integer.MAX_VALUE;

        if (orderQuantity == 0) {
            ui.print("Order Quantity cannot be 0.");
            return;
        }

        if (parameters.containsKey(CommandParameters.NAME)) {
            nameToAdd = parameters.get(CommandParameters.NAME);
            for (Medicine medicine : medicines) {
                if (medicine instanceof Order && medicine.getMedicineName().equalsIgnoreCase(nameToAdd)) {
                    nameExistsInOrder = true;
                    break;
                }
            }
        }

        if (nameExistsInOrder) {
            if (parameters.containsKey(CommandParameters.NAME)) {
                nameToAdd = parameters.get(CommandParameters.NAME);
                for (Medicine medicine : medicines) {
                    if (medicine instanceof Stock && medicine.getMedicineName().equalsIgnoreCase(nameToAdd)) {
                        nameExistsInStock = true;
                        break;
                    }
                }
            }

            if (!nameExistsInStock) {
                if (orderQuantity < maxQuantity) {
                    addOrder(ui, medicines, nameToAdd, orderQuantity, addDate(ui, dateToAdd));
                }
            } else {
                int existingOrdersQuantity = OrderManager.getTotalOrderQuantity(medicines, nameToAdd);
                int existingStockQuantity = StockManager.getTotalStockQuantity(medicines, nameToAdd);
                int totalQuantity = existingStockQuantity + existingOrdersQuantity;
                maxQuantity = StockManager.getMaxStockQuantity(medicines, nameToAdd);

                if (orderQuantity + totalQuantity <= maxQuantity) {
                    addOrder(ui, medicines, nameToAdd, orderQuantity, addDate(ui, dateToAdd));
                } else {
                    ui.print("Order for " + nameToAdd + " exists.\nUnable to add order as total order quantity "
                            + "exceeds maximum stock quantity of " + maxQuantity + ".\nExisting quantity in stock: "
                            + existingStockQuantity + "\nPending order quantity: " + existingOrdersQuantity);
                }
            }
        } else {
            addOrder(ui, medicines, nameToAdd, orderQuantity, addDate(ui, dateToAdd));
        }
    }

    /**
     * Add order based on user input.
     *
     * @param ui        Reference to the UI object to print messages.
     * @param medicines Arraylist of all medicines.
     * @param name      Medication name to order.
     * @param quantity  Quantity of medication to order.
     * @param date      Order date.
     */
    private void addOrder(Ui ui, ArrayList<Medicine> medicines, String name, int quantity, Date date) {
        Order order = new Order(name, quantity, date);
        medicines.add(order);
        ui.print("Order added: " + name);
        ui.printOrder(order);

        Storage storage = Storage.getInstance();
        storage.saveData(medicines);
        logger.log(Level.INFO, "Successful addition of order");
    }

    /**
     * Add date based on user input.
     *
     * @param ui        Reference to the UI object to print messages.
     * @param dateToAdd Order date input by user (check if it is in correct date format).
     * @return Default date or order date.
     */
    private Date addDate(Ui ui, String dateToAdd) {
        if (dateToAdd == null) {
            Date defaultDate = null;
            defaultDate = new Date();
            return defaultDate;
        }

        try {
            Date orderDate = null;
            orderDate = DateParser.stringToDate(dateToAdd);
            return orderDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if user inputs are valid.
     *
     * @param ui                    Reference to the UI object to print messages.
     * @param parameters            The parameter that is not found.
     * @param medicines             List of all medicines.
     * @param requiredParameters    The required parameters to check.
     * @param optionalParameters    The optional parameters to check.
     * @param orderValidator        Reference to OrderValidator object.
     * @return Boolean value indicating if parameter and parameter values are valid.
     */
    private boolean checkValidParameterValues(Ui ui, LinkedHashMap<String, String> parameters,
                                            ArrayList<Medicine> medicines, String[] requiredParameters,
                                            String[] optionalParameters, OrderValidator orderValidator) {
        boolean isInvalidParameters = orderValidator.containsInvalidParameters(ui, parameters,
                requiredParameters, optionalParameters, CommandSyntax.ADD_ORDER_COMMAND, false);
        if (isInvalidParameters) {
            logger.log(Level.WARNING, "Invalid parameters given by user");
            return true;
        }

        boolean isInvalidParameterValues = orderValidator.containsInvalidParameterValues(ui, parameters,
                medicines, CommandSyntax.ADD_ORDER_COMMAND);
        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameters values given by user");
            return true;
        }
        return false;
    }
}
