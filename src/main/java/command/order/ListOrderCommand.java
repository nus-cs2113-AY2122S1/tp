package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Order;
import utilities.comparators.OrderComparator;
import utilities.parser.DateParser;
import utilities.parser.MedicineValidator;
import utilities.parser.OrderValidator;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//@@author RemusTeo

/**
 * Helps to process the listorder command together with filters and sort.
 */
public class ListOrderCommand extends Command {
    private static Logger logger = Logger.getLogger("ListOrder");

    public ListOrderCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start listing of order records");
        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        String[] requiredParameters = {};
        String[] optionalParameters = {CommandParameters.ID, CommandParameters.NAME, CommandParameters.QUANTITY,
                CommandParameters.DATE, CommandParameters.STATUS, CommandParameters.SORT,
                CommandParameters.REVERSED_SORT};

        MedicineValidator validator = new OrderValidator();
        boolean isInvalidInput = validator.containsInvalidParametersAndValues(ui, medicines, parameters,
                requiredParameters, optionalParameters, CommandSyntax.LIST_ORDER_COMMAND, false, validator);
        if (isInvalidInput) {
            return;
        }

        ArrayList<Order> filteredOrders = new ArrayList<>();

        assert (filteredOrders != null) : "Array is not initialised";

        for (Medicine medicine : medicines) {
            if (medicine instanceof Order) {
                filteredOrders.add((Order) medicine);
            }
        }
        filteredOrders = filterOrders(parameters, filteredOrders);

        ui.printOrders(filteredOrders);
        logger.log(Level.INFO, "Successful listing of order");
    }


    /**
     * Helps to filter order records based on the user's input.
     *
     * @param parameters     HashMap Key-Value set for parameter and user specified parameter value.
     * @param filteredOrders Arraylist of Order objects.
     * @return Arraylist of filtered Order objects based on the user's parameters values.
     */
    private ArrayList<Order> filterOrders(LinkedHashMap<String, String> parameters, ArrayList<Order> filteredOrders) {
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.ID:
                filteredOrders = (ArrayList<Order>) filteredOrders.stream()
                        .filter((m) -> (m).getOrderId() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.NAME:
                filteredOrders = (ArrayList<Order>) filteredOrders.stream()
                        .filter((m) -> (m.getMedicineName().toUpperCase()).contains(parameterValue.toUpperCase()))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.QUANTITY:
                filteredOrders = (ArrayList<Order>) filteredOrders.stream().filter((m) ->
                        m.getQuantity() == Integer.parseInt(parameterValue)).collect(Collectors.toList());
                break;
            case CommandParameters.DATE:
                try {
                    Date date = DateParser.stringToDate(parameterValue);
                    filteredOrders = (ArrayList<Order>) filteredOrders.stream()
                            .filter((m) -> (m).getDate().equals(date))
                            .collect(Collectors.toList());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case CommandParameters.STATUS:
                filteredOrders = (ArrayList<Order>) filteredOrders.stream()
                        .filter((m) -> (m.getStatus()).equalsIgnoreCase(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.SORT:
                filteredOrders.sort(new OrderComparator(parameterValue.toLowerCase(), false));
                break;
            case CommandParameters.REVERSED_SORT:
                filteredOrders.sort(new OrderComparator(parameterValue.toLowerCase(), true));
                break;
            default:
                return filteredOrders;
            }
        }
        return filteredOrders;
    }
}
