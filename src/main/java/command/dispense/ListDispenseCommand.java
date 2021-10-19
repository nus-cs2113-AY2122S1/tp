package command.dispense;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import utilities.comparators.DispenseComparator;
import inventory.Dispense;
import inventory.Medicine;
import utilities.parser.DateParser;
import utilities.parser.DispenseValidator;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Helps to process the listdispense command together with filters and sort.
 */

public class ListDispenseCommand extends Command {
    private static Logger logger = Logger.getLogger("ListDispenseCommand");

    public ListDispenseCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start listing of dispense records");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        String[] requiredParameter = {};
        String[] optionalParameters = {CommandParameters.ID, CommandParameters.NAME, CommandParameters.QUANTITY,
                CommandParameters.CUSTOMER_ID, CommandParameters.DATE, CommandParameters.STAFF,
                CommandParameters.STOCK_ID,
                CommandParameters.SORT, CommandParameters.REVERSED_SORT};

        boolean isInvalidParameter = CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameter,
                optionalParameters, CommandSyntax.LIST_DISPENSE_COMMAND, false);

        if (isInvalidParameter) {
            logger.log(Level.WARNING, "Invalid parameters given by user");
            return;
        }

        boolean isInvalidParameterValues = DispenseValidator.containsInvalidParameterValues(ui, parameters, medicines,
                CommandSyntax.LIST_DISPENSE_COMMAND);

        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameters values given by user");
            return;
        }

        ArrayList<Dispense> filteredDispense = new ArrayList<>();

        assert (filteredDispense != null) : "Array is not initialised";

        for (Medicine medicine : medicines) {
            if (medicine instanceof Dispense) {
                filteredDispense.add((Dispense) medicine);
            }
        }
        filteredDispense = filterDispense(parameters, filteredDispense);

        ui.printDispenses(filteredDispense);
        logger.log(Level.INFO, "Successful listing of dispense");
    }


    /**
     * Helps to filter dispense records based on the user's input.
     *
     * @param parameters       HashMap Key-Value set for parameter and user specified parameter value.
     * @param filteredDispense Arraylist of Dispense objects.
     * @return Arraylist of filtered Dispense objects based on the user's parameters values.
     */
    private ArrayList<Dispense> filterDispense(LinkedHashMap<String, String> parameters,
                                               ArrayList<Dispense> filteredDispense) {
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.ID:
                filteredDispense = (ArrayList<Dispense>) filteredDispense.stream()
                        .filter((d) -> d.getDispenseId() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.NAME:
                filteredDispense = (ArrayList<Dispense>) filteredDispense.stream()
                        .filter((d) -> d.getMedicineName().toUpperCase().contains(parameterValue.toUpperCase()))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.QUANTITY:
                filteredDispense = (ArrayList<Dispense>) filteredDispense.stream()
                        .filter((d) -> d.getQuantity() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.CUSTOMER_ID:
                filteredDispense = (ArrayList<Dispense>) filteredDispense.stream()
                        .filter((d) -> d.getCustomerId().toUpperCase().contains(parameterValue.toUpperCase()))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.DATE:
                try {
                    Date date = DateParser.stringToDate(parameterValue);
                    filteredDispense = (ArrayList<Dispense>) filteredDispense.stream()
                            .filter((m) -> m.getDate().equals(date))
                            .collect(Collectors.toList());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case CommandParameters.STAFF:
                filteredDispense = (ArrayList<Dispense>) filteredDispense.stream()
                        .filter((d) -> d.getStaff().toUpperCase().contains(parameterValue.toUpperCase()))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.STOCK_ID:
                filteredDispense = (ArrayList<Dispense>) filteredDispense.stream()
                        .filter((d) -> d.getStockId() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.SORT:
                filteredDispense.sort(new DispenseComparator(parameterValue.toLowerCase(), false));
                break;
            case CommandParameters.REVERSED_SORT:
                filteredDispense.sort(new DispenseComparator(parameterValue.toLowerCase(), true));
                break;
            default:
                return filteredDispense;
            }
        }
        return filteredDispense;
    }
}
