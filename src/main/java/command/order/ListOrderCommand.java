package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Order;
import parser.DateParser;
import parser.OrderValidator;
import storage.Storage;
import ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Helps to process the list command together with filters and sort.
 */

public class ListOrderCommand extends Command {
    private static Logger logger = Logger.getLogger("ListOrder");

    @Override
    public void execute(Ui ui, LinkedHashMap<String, String> parameters, ArrayList<Medicine> medicines,
                        Storage storage) {
        logger.log(Level.INFO, "Start listing of order");

        String[] requiredParameters = {};
        String[] optionalParameters = {CommandParameters.ID, CommandParameters.NAME, CommandParameters.QUANTITY,
                CommandParameters.DATE, CommandParameters.STATUS};

        boolean isInvalidParameter = CommandSyntax.containsInvalidParameters(ui, parameters,
                requiredParameters, optionalParameters, CommandSyntax.LIST_ORDER_COMMAND, false);
        if (isInvalidParameter) {
            logger.log(Level.WARNING, "Invalid parameters given by user");
            return;
        }

        boolean isInvalidParameterValues = OrderValidator.containsInvalidParameterValues(ui, parameters,
                medicines, CommandSyntax.LIST_ORDER_COMMAND);
        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameters values given by user");
            return;
        }

        ArrayList<Order> filteredOrders = new ArrayList<>();

        assert (filteredOrders != null) : "Array is not initialised";

        for (Medicine medicine : medicines) {
            if (medicine instanceof Order) {
                filteredOrders.add((Order) medicine);
            }

            for (String parameter : parameters.keySet()) {
                String parameterValue = parameters.get(parameter);
                switch (parameter) {
                case CommandParameters.ID:
                    filteredOrders = (ArrayList<Order>) filteredOrders.stream().filter((m) ->
                            (m).getOrderId() == Integer.parseInt(parameterValue)).collect(Collectors.toList());
                    break;
                case CommandParameters.NAME:
                    filteredOrders = (ArrayList<Order>) filteredOrders.stream().filter((m) ->
                                    (m.getMedicineName().toUpperCase()).contains(parameterValue.toUpperCase()))
                                    .collect(Collectors.toList());
                    break;
                case CommandParameters.QUANTITY:
                    filteredOrders = (ArrayList<Order>) filteredOrders.stream().filter((m) ->
                            m.getQuantity() == Integer.parseInt(parameterValue)).collect(Collectors.toList());
                    break;
                case CommandParameters.DATE:
                    try {
                        Date date = DateParser.stringToDate(parameterValue);
                        filteredOrders = (ArrayList<Order>) filteredOrders.stream().filter((m) ->
                                (m).getDate().toInstant().isBefore(date.toInstant())
                                || (m).getDate().toInstant().equals(date.toInstant())).collect(Collectors.toList());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case CommandParameters.STATUS:
                    filteredOrders = (ArrayList<Order>) filteredOrders.stream().filter((m) ->
                                    (m.getStatus()).equalsIgnoreCase(parameterValue))
                                    .collect(Collectors.toList());
                    break;
                default:
                    return;
                }
            }
        }

        ui.printOrders(filteredOrders);
        logger.log(Level.INFO, "Successful listing of order");
    }
}
