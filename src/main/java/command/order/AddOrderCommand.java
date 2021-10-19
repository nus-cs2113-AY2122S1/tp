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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Storage storage = Storage.getInstance();

        String[] requiredParameters = {CommandParameters.NAME, CommandParameters.QUANTITY};
        String[] optionalParameter = {CommandParameters.DATE};

        boolean isInvalidParameters = CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters,
                optionalParameter, CommandSyntax.ADD_ORDER_COMMAND, false);
        if (isInvalidParameters) {
            logger.log(Level.WARNING, "Invalid parameters given by user");
            return;
        }

        boolean isInvalidParameterValues = OrderValidator.containsInvalidParameterValues(ui, parameters, medicines,
                CommandSyntax.ADD_ORDER_COMMAND);
        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameters values given by user");
            return;
        }

        String nameToAdd = parameters.get(CommandParameters.NAME);
        String quantityToAdd = parameters.get(CommandParameters.QUANTITY);
        String dateToAdd = parameters.get(CommandParameters.DATE);

        int maxQuantity = StockManager.getMaxStockQuantity(medicines, nameToAdd);
        try {
            int orderQuantity = Integer.parseInt(quantityToAdd);
            if (orderQuantity > maxQuantity) {
                ui.print("Unable to add order! Order quantity more than maximum order quantity.");
                return;
            }

            if (dateToAdd == null) {
                Date defaultDate = null;
                defaultDate = new Date(); //order date will be today's date if no user input for date parameter
                addOrder(ui, medicines, nameToAdd, orderQuantity, defaultDate);
                return;
            }

            Date orderDate = null;
            orderDate = DateParser.stringToDate(dateToAdd);
            addOrder(ui, medicines, nameToAdd, orderQuantity, orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
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
        ui.printOrder(order);
        logger.log(Level.INFO, "Successful addition of order");
    }
}
