package command.medicine;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import comparators.StockComparator;
import inventory.Medicine;
import inventory.Stock;
import parser.DateParser;
import ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Helps to process the list command together with filters and sort.
 */

public class ListStocks extends Command {
    private static Logger logger = Logger.getLogger("ListCommand");

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        logger.log(Level.INFO, "Start listing of available stock");

        String[] requiredParameter = {};
        String[] optionalParameters = {CommandParameters.STOCK_ID, CommandParameters.NAME, CommandParameters.PRICE,
            CommandParameters.QUANTITY, CommandParameters.EXPIRY_DATE, CommandParameters.DESCRIPTION,
            CommandParameters.MAX_QUANTITY, CommandParameters.SORT, CommandParameters.REVERSED_SORT};

        boolean isInvalidParameter = CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameter,
                optionalParameters, CommandSyntax.LIST_COMMAND, false);
        if (isInvalidParameter) {
            logger.log(Level.WARNING, "Invalid parameters given by user");
            return;
        }

        boolean isInvalidParameterValues = CommandSyntax.containsInvalidParameterValues(ui, parameters, medicines);
        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameters values given by user");
            return;
        }

        ArrayList<Stock> filteredStocks = new ArrayList<>();

        assert (filteredStocks != null) : "Array is not initialised";

        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock) { // Ensure that it is a medicine object
                filteredStocks.add((Stock) medicine);
            }
        }
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.STOCK_ID:
                filteredStocks = (ArrayList<Stock>) filteredStocks.stream()
                        .filter((m) -> (m).getStockID() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.NAME:
                filteredStocks = (ArrayList<Stock>) filteredStocks.stream()
                        .filter((m) -> (m.getMedicineName().toUpperCase()).contains(parameterValue.toUpperCase()))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.PRICE:
                filteredStocks = (ArrayList<Stock>) filteredStocks.stream()
                        .filter((m) -> (m).getPrice() == Double.parseDouble(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.QUANTITY:
                filteredStocks = (ArrayList<Stock>) filteredStocks.stream()
                        .filter((m) -> m.getQuantity() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.EXPIRY_DATE:
                try {
                    Date expiryDate = DateParser.stringToDate(parameterValue);
                    filteredStocks = (ArrayList<Stock>) filteredStocks.stream()
                            .filter((m) -> (m).getExpiry().equals(expiryDate))
                            .collect(Collectors.toList());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case CommandParameters.DESCRIPTION:
                filteredStocks = (ArrayList<Stock>) filteredStocks.stream()
                        .filter((m) -> (m.getDescription().toUpperCase())
                                .contains(parameterValue.toUpperCase())).collect(Collectors.toList());
                break;
            case CommandParameters.MAX_QUANTITY:
                filteredStocks = (ArrayList<Stock>) filteredStocks.stream()
                        .filter((m) -> m.getMaxQuantity() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.SORT:
                filteredStocks.sort(new StockComparator(parameterValue.toLowerCase(), false));
                break;
            case CommandParameters.REVERSED_SORT:
                filteredStocks.sort(new StockComparator(parameterValue.toLowerCase(), true));
                break;
            default:
                ui.printInvalidParameter(parameter, CommandSyntax.LIST_COMMAND);
                return;
            }
        }
        //ui.printStocks(filteredStocks);
        logger.log(Level.INFO, "Successful listing of stock");
    }
}
