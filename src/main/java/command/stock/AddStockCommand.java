package command.stock;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Stock;
import utilities.parser.DateParser;
import utilities.parser.MedicineValidator;
import utilities.parser.StockManager;
import utilities.parser.StockValidator;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author deonchung

/**
 * Add medication based on user input.
 * User input include name, price, quantity, expiry date, description and maximum quantity of medication.
 */
public class AddStockCommand extends Command {
    private static Logger logger = Logger.getLogger("AddCommand");

    public AddStockCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start addition of stock");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();
        StockValidator stockValidator = new StockValidator();
        ArrayList<Stock> filteredStocks = new ArrayList<>();

        String[] optionalParameters = {};
        String nameToAdd = parameters.get(CommandParameters.NAME);
        boolean nameExist = false;

        if (parameters.containsKey(CommandParameters.NAME)) {
            nameToAdd = parameters.get(CommandParameters.NAME);
            for (Medicine medicine : medicines) {
                if (medicine instanceof Stock && medicine.getMedicineName().equalsIgnoreCase(nameToAdd)
                        && !((Stock) medicine).isDeleted()) {
                    filteredStocks.add((Stock) medicine);
                    nameExist = true;
                }
            }
        }

        if (nameExist) {
            String[] requiredParameters = {CommandParameters.NAME, CommandParameters.QUANTITY,
                    CommandParameters.EXPIRY_DATE};

            if (!checkValidParametersAndValues(ui, parameters, medicines, requiredParameters,
                    optionalParameters)) {
                return;
            }

            String expiryDate = parameters.get(CommandParameters.EXPIRY_DATE);
            Date formatExpiryDate = checkExpiryDate(ui, expiryDate);

            if (formatExpiryDate == null) { //if medication has expired
                return;
            }

            String quantityToAdd = parameters.get(CommandParameters.QUANTITY);

            int totalStock = StockManager.getTotalStockQuantity(medicines, nameToAdd);
            assert totalStock > 0 : "Total Stock should be more than 0";

            if (isExpiryExist(ui, stockValidator, filteredStocks, quantityToAdd, formatExpiryDate, totalStock)) {
                return;
            }

            String[] requiredParams = {CommandParameters.NAME, CommandParameters.PRICE,
                    CommandParameters.QUANTITY, CommandParameters.EXPIRY_DATE};

            if (!checkValidParametersAndValues(ui, parameters, medicines, requiredParams,
                    optionalParameters)) {
                return;
            }

            if (addSameMedicine(ui, medicines, nameToAdd, stockValidator, filteredStocks, quantityToAdd,
                    formatExpiryDate, totalStock)) {
                return;
            }
        } else {
            String[] requiredParameters = {CommandParameters.NAME, CommandParameters.PRICE,
                    CommandParameters.QUANTITY, CommandParameters.EXPIRY_DATE,
                    CommandParameters.DESCRIPTION, CommandParameters.MAX_QUANTITY};

            if (!checkValidParametersAndValues(ui, parameters, medicines, requiredParameters,
                    optionalParameters)) {
                return;
            }

            String priceToAdd = parameters.get(CommandParameters.PRICE);
            String quantityToAdd = parameters.get(CommandParameters.QUANTITY);
            String descriptionToAdd = parameters.get(CommandParameters.DESCRIPTION);
            String maxQuantityToAdd = parameters.get(CommandParameters.MAX_QUANTITY);
            String expiryDate = parameters.get(CommandParameters.EXPIRY_DATE);

            Date formatExpiryDate = checkExpiryDate(ui, expiryDate);
            if (formatExpiryDate == null) { //if medication has expired
                return;
            }

            int maxQuantity = Integer.parseInt(maxQuantityToAdd);
            int quantity = Integer.parseInt(quantityToAdd);
            double price = Double.parseDouble(priceToAdd);

            if (isValidQuantity(ui, stockValidator, maxQuantity, quantity)) {
                return;
            }

            addMedicine(ui, medicines, nameToAdd, descriptionToAdd, price, quantity, formatExpiryDate, maxQuantity);
        }
        Storage storage = Storage.getInstance();
        storage.saveData(medicines);
    }

    /**
     * Check if medication had expired.
     *
     * @param ui         Reference to the UI object to print messages.
     * @param expiryDate Expiry Date of medication to add.
     * @return Expiry Date of medication if medication has not expired. Null if medication has expired.
     */
    private Date checkExpiryDate(Ui ui, String expiryDate) {
        Date formatExpiryDate = null;
        try {
            formatExpiryDate = DateParser.stringToDate(expiryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date todayDate = new Date();
        String todayDateString = DateParser.dateToString(todayDate);

        Date formatTodayDate = null;
        try {
            formatTodayDate = DateParser.stringToDate(todayDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (formatExpiryDate.before(formatTodayDate)) {
            ui.print("Unable to add medicine. Medicine has expired.");
            return null;
        }
        return formatExpiryDate;
    }

    /**
     * Check if same expiry for the same medication name exist.
     *
     * @param ui             Reference to the UI object to print messages.
     * @param stockValidator Reference to StockValidator object.
     * @param filteredStocks List of medication with the same medication name as user input.
     * @param quantityToAdd  Quantity of medication to add.
     * @param formatExpiry   Formatted Expiry Date of medication to add.
     * @param totalStock     Total Quantity of the same stock.
     * @return Boolean Value indicating if same medication exists.
     */
    private boolean isExpiryExist(Ui ui, StockValidator stockValidator, ArrayList<Stock> filteredStocks,
                                  String quantityToAdd, Date formatExpiry, int totalStock) {
        for (Stock stock : filteredStocks) {
            int quantity = Integer.parseInt(quantityToAdd);

            if (isValidQuantity(ui, stockValidator, stock.getMaxQuantity(), totalStock + quantity)) {
                return true;
            }

            if (formatExpiry.equals(stock.getExpiry())) {
                quantity += stock.getQuantity();
                stock.setQuantity(quantity);
                ui.print("Same Medication and Expiry Date exist. Using existing price,"
                        + " description and maximum quantity. Updating existing quantity.");
                ui.printStock(stock);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the medication to same stock if same name exist.
     *
     * @param ui             Reference to the UI object to print messages.
     * @param medicines      List of all medicines.
     * @param nameToAdd      Name of medication to add.
     * @param stockValidator Reference to StockValidator object.
     * @param filteredStocks List of medication with the same medication name as user input.
     * @param quantityToAdd  Quantity of medication to add.
     * @param formatExpiry   Formatted Expiry Date of medication to add.
     * @param totalStock     Total Quantity of the same stock.
     * @return Boolean Value indicating if the same medication name exist.
     */
    private boolean addSameMedicine(Ui ui, ArrayList<Medicine> medicines, String nameToAdd,
                                    StockValidator stockValidator,
                                    ArrayList<Stock> filteredStocks, String quantityToAdd, Date formatExpiry,
                                    int totalStock) {
        for (Stock stock : filteredStocks) {

            int quantity = Integer.parseInt(quantityToAdd);
            int existingMaxQuantity = stock.getMaxQuantity();

            if (isValidQuantity(ui, stockValidator, existingMaxQuantity, totalStock + quantity)) {
                return true;
            }

            String existingDescription = stock.getDescription();
            String priceToAdd = parameters.get(CommandParameters.PRICE);
            double price = Double.parseDouble(priceToAdd);
            ui.print("Medicine exists. Using existing description and maximum quantity.");
            addMedicine(ui, medicines, nameToAdd, existingDescription, price,
                    quantity, formatExpiry, existingMaxQuantity);
            return true;
        }
        return false;
    }

    /**
     * Check if quantity added is less than the maximum quantity of stock.
     *
     * @param ui             Reference to the UI object to print messages.
     * @param stockValidator Reference to StockValidator object.
     * @param maxQuantity    Maximum quantity of medication.
     * @param quantity       Quantity of medication to add.
     * @return Boolean Value indicating if quantity added is less than maximum quantity.
     */
    private boolean isValidQuantity(Ui ui, StockValidator stockValidator, int maxQuantity, int quantity) {
        boolean isValidQuantity = stockValidator.quantityValidityChecker(ui, quantity, maxQuantity);

        if (!isValidQuantity) {
            logger.log(Level.WARNING, "Invalid Quantity is specified by user");
            logger.log(Level.INFO, "Unsuccessful addition of stock");
            return true;
        }
        return false;
    }

    /**
     * Check if input contains Invalid Parameters and Invalid Parameter Values.
     *
     * @param ui                 Reference to the UI object to print messages.
     * @param parameters         The parameter that is not found.
     * @param medicines          List of all medicines.
     * @param requiredParameters The required parameters to check.
     * @param optionalParameters The optional parameters to check.
     * @return Boolean value indicating if parameter and parameter values are valid.
     */
    private boolean checkValidParametersAndValues(Ui ui, LinkedHashMap<String, String> parameters,
                                                  ArrayList<Medicine> medicines, String[] requiredParameters,
                                                  String[] optionalParameters) {
        MedicineValidator validator = new StockValidator();
        boolean isInvalidInput = validator.containsInvalidParametersAndValues(ui, medicines, parameters,
                requiredParameters, optionalParameters, CommandSyntax.ADD_STOCK_COMMAND, false, validator);
        if (isInvalidInput) {
            logger.log(Level.INFO, "Unsuccessful addition of stock");
            return false;
        }
        return true;
    }

    /**
     * Add medication based on user input.
     *
     * @param ui          Reference to the UI object to print messages.
     * @param medicines   List of all medicines.
     * @param name        Name of medication to add.
     * @param description Description of medication to add.
     * @param price       Price of medication to add.
     * @param quantity    Quantity of medication to add.
     * @param expiryDate  Expiry Date of medication to add.
     * @param maxQuantity Maximum Quantity of medication to add.
     */
    private void addMedicine(Ui ui, ArrayList<Medicine> medicines, String name, String description,
                             double price, int quantity, Date expiryDate, int maxQuantity) {

        Stock stock = new Stock(name, price, quantity, expiryDate, description, maxQuantity);
        medicines.add(stock);
        ui.print("Medication added: " + name);
        ui.printStock(stock);
        logger.log(Level.INFO, "Successful addition of stock");
    }

}
