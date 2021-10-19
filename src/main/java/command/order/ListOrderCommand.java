package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Order;
import utilities.comparators.OrderComparator;
import utilities.parser.DateParser;
import utilities.parser.OrderValidator;
import utilities.ui.Ui;

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

    public ListOrderCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start listing of order");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        String[] requiredParameters = {};
        String[] optionalParameters = {CommandParameters.ID, CommandParameters.NAME, CommandParameters.QUANTITY,
                CommandParameters.DATE, CommandParameters.STATUS, CommandParameters.SORT,
                CommandParameters.REVERSED_SORT};

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
                                (m).getDate().equals(date)).collect(Collectors.toList());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case CommandParameters.STATUS:
                    filteredOrders = (ArrayList<Order>) filteredOrders.stream().filter((m) ->
                            (m.getStatus()).equalsIgnoreCase(parameterValue)).collect(Collectors.toList());
                    break;
                case CommandParameters.SORT:
                    filteredOrders.sort(new OrderComparator(parameterValue.toLowerCase(), false));
                    break;
                case CommandParameters.REVERSED_SORT:
                    filteredOrders.sort(new OrderComparator(parameterValue.toLowerCase(), true));
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
