package command.stock;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Stock;
import utilities.comparators.StockComparator;
import utilities.parser.DateParser;
import utilities.parser.MedicineValidator;
import utilities.parser.StockManager;
import utilities.parser.StockValidator;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//@@author jiangweichen835

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
                CommandParameters.MAX_QUANTITY, CommandParameters.SORT, CommandParameters.REVERSED_SORT,
                CommandParameters.EXPIRING, CommandParameters.LOW};

        ArrayList<Medicine> medicines = Medicine.getInstance();
        if (!checkValidParameterValues(ui, medicines, requiredParameter, optionalParameters)) {
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
        }
        filteredStocks = filterStocks(filteredStocks, medicines);
        ui.printStocks(filteredStocks, medicines);
        logger.log(Level.INFO, "Successful listing of stock");
    }

    /**
     * Helps to filter stocks based on the user's input.
     *
     * @param filteredStocks Arraylist of Stock objects.
     * @param medicines Arraylist of Medicines objects.
     * @return Arraylist of filtered Stock objects based on the user's parameters values.
     */
    private ArrayList<Stock> filterStocks(ArrayList<Stock> filteredStocks, ArrayList<Medicine> medicines) {
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.ID:
                filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                        m.getStockId() == Integer.parseInt(parameterValue)).collect(Collectors.toList());
                break;
            case CommandParameters.NAME:
                filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                                (m.getMedicineName().toUpperCase()).contains(parameterValue.toUpperCase()))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.PRICE:
                filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                        m.getPrice() == Double.parseDouble(parameterValue)).collect(Collectors.toList());
                break;
            case CommandParameters.LOW:
                filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                        StockManager.getTotalStockQuantity(medicines, m.getMedicineName())
                                <= Integer.parseInt(parameterValue)).collect(Collectors.toList());
                break;
            case CommandParameters.QUANTITY:
                filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                        m.getQuantity() == Integer.parseInt(parameterValue)).collect(Collectors.toList());
                break;
            case CommandParameters.EXPIRING:
                try {
                    Date expiryDate = DateParser.stringToDate(parameterValue);
                    filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                                    m.getExpiry().before(expiryDate) || m.getExpiry().equals(expiryDate))
                            .collect(Collectors.toList());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case CommandParameters.EXPIRY_DATE:
                try {
                    Date expiryDate = DateParser.stringToDate(parameterValue);
                    filteredStocks = (ArrayList<Stock>) filteredStocks.stream().filter((m) ->
                            m.getExpiry().equals(expiryDate)).collect(Collectors.toList());
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
                return filteredStocks;
            }
        }
        return filteredStocks;
    }

    /**
     * Checks if user input are valid.
     *
     * @param ui                 Reference to the UI object to print messages.
     * @param medicines          List of all medicines.
     * @param requiredParameters The required parameters to check.
     * @param optionalParameters The optional parameters to check.
     * @return Boolean value indicating if parameter and parameter values are valid.
     */
    private boolean checkValidParameterValues(Ui ui, ArrayList<Medicine> medicines, String[] requiredParameters,
                                              String[] optionalParameters) {
        MedicineValidator validator = new StockValidator();
        boolean isInvalidInput = validator.containsInvalidParametersAndValues(ui, medicines, parameters,
                requiredParameters, optionalParameters, CommandSyntax.LIST_STOCK_COMMAND, false, validator);
        if (isInvalidInput) {
            return false;
        }

        return true;
    }
}
