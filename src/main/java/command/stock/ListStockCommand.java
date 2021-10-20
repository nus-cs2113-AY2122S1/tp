package command.stock;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import utilities.comparators.StockComparator;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.DateParser;
import utilities.parser.StockValidator;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Helps to process the liststock command together with filters and sort.
 */

public class ListStockCommand extends Command {
    private static Logger logger = Logger.getLogger("ListStock");

    public ListStockCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start listing of available stock");

        Ui ui = Ui.getInstance();

        String[] requiredParameter = {};
        String[] optionalParameters = {CommandParameters.ID, CommandParameters.NAME, CommandParameters.PRICE,
                CommandParameters.QUANTITY, CommandParameters.EXPIRY_DATE, CommandParameters.DESCRIPTION,
                CommandParameters.MAX_QUANTITY, CommandParameters.SORT, CommandParameters.REVERSED_SORT};

        boolean isInvalidParameter = CommandSyntax.containsInvalidParameters(ui, parameters,
                requiredParameter, optionalParameters, CommandSyntax.LIST_STOCK_COMMAND, false);
        if (isInvalidParameter) {
            logger.log(Level.WARNING, "Invalid parameters given by user");
            return;
        }

        ArrayList<Medicine> medicines = Medicine.getInstance();

        boolean isInvalidParameterValues = StockValidator.containsInvalidParameterValues(ui, parameters,
                medicines, CommandSyntax.LIST_STOCK_COMMAND);
        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameters values given by user");
            return;
        }

        ArrayList<Stock> filteredStocks = new ArrayList<>();

        assert (filteredStocks != null) : "Array is not initialised";

        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock) { // Ensure that it is a medicine object
                Stock stock = (Stock) medicine;
                if (!stock.isDeleted()) {
                    filteredStocks.add(stock);
                }
            }

            for (String parameter : parameters.keySet()) {
                String parameterValue = parameters.get(parameter);
                switch (parameter) {
                case CommandParameters.ID:
                    filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                            (m).getStockID() == Integer.parseInt(parameterValue)).collect(Collectors.toList());
                    break;
                case CommandParameters.NAME:
                    filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                                    (m.getMedicineName().toUpperCase()).contains(parameterValue.toUpperCase()))
                            .collect(Collectors.toList());
                    break;
                case CommandParameters.PRICE:
                    filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                            (m).getPrice() == Double.parseDouble(parameterValue)).collect(Collectors.toList());
                    break;
                case CommandParameters.QUANTITY:
                    filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                            m.getQuantity() == Integer.parseInt(parameterValue)).collect(Collectors.toList());
                    break;
                case CommandParameters.EXPIRY_DATE:
                    try {
                        Date expiryDate = DateParser.stringToDate(parameterValue);
                        filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                                (m).getExpiry().equals(expiryDate)).collect(Collectors.toList());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case CommandParameters.DESCRIPTION:
                    filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                                    (m.getDescription().toUpperCase()).contains(parameterValue.toUpperCase()))
                            .collect(Collectors.toList());
                    break;
                case CommandParameters.MAX_QUANTITY:
                    filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                            m.getMaxQuantity() == Integer.parseInt(parameterValue)).collect(Collectors.toList());
                    break;
                case CommandParameters.SORT:
                    filteredStocks.sort(new StockComparator(parameterValue.toLowerCase(), false));
                    break;
                case CommandParameters.REVERSED_SORT:
                    filteredStocks.sort(new StockComparator(parameterValue.toLowerCase(), true));
                    break;
                default:
                    return;
                }
            }
        }
        ui.printStocks(filteredStocks, medicines);
        logger.log(Level.INFO, "Successful listing of stock");
    }
}
