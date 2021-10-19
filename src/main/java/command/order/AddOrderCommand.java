package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Order;
import inventory.Medicine;
import utilities.parser.DateParser;
import utilities.parser.OrderValidator;
import utilities.storage.Storage;

import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Date;
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

        String[] requiredParameters = {CommandParameters.NAME, CommandParameters.QUANTITY, CommandParameters.DATE};
        String[] optionalParameter = {};

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters,
                optionalParameter, CommandSyntax.ADD_ORDER_COMMAND, false)) {
            logger.log(Level.WARNING, "Invalid parameters given by user");
            return;
        }

        if (OrderValidator.containsInvalidParameterValues(ui, parameters, medicines,
                CommandSyntax.ADD_ORDER_COMMAND)) {
            logger.log(Level.WARNING, "Invalid parameters values given by user");
            return;
        }

        String nameToAdd = parameters.get(CommandParameters.NAME);
        String quantityToAdd = parameters.get(CommandParameters.QUANTITY);
        String dateToAdd = parameters.get(CommandParameters.DATE);

        Date orderDate = null;
        try {
            orderDate = DateParser.stringToDate(dateToAdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int orderQuantity = Integer.parseInt(quantityToAdd);
        addOrder(ui, medicines, nameToAdd, orderQuantity, orderDate);
    }

    /**
     * Add order based on user input.
     * 
     * @param ui        Reference to the UI object passed by Main to print messages.
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
