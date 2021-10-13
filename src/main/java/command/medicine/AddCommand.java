package command.medicine;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Stock;
import inventory.Medicine;
import parser.DateParser;
import parser.MedicineManager;
import parser.StockValidator;

import ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Add medication based on user input.
 * User input include name, price, quantity, expiry date, description and maximum quantity of medication.
 */
public class AddCommand extends Command {
    private static Logger logger = Logger.getLogger("AddCommand");

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        logger.log(Level.INFO, "Start addition of stock");

        boolean nameExist = false;
        String nameToAdd = parameters.get(CommandParameters.NAME);
        Stock existingStock = null;
        String[] optionalParameters = {};

        if (parameters.containsKey(CommandParameters.NAME)) {
            nameToAdd = parameters.get(CommandParameters.NAME);
            for (Medicine medicine : medicines) {
                if (medicine instanceof Stock && medicine.getMedicineName().equals(nameToAdd.toUpperCase())) {
                    existingStock = (Stock) medicine;
                    nameExist = true;
                    break;
                }
            }
        }

        if (nameExist) {
            String[] requiredParameters = {CommandParameters.NAME, CommandParameters.PRICE,
                                           CommandParameters.QUANTITY, CommandParameters.EXPIRY_DATE};

            if (checkValidParametersAndValues(ui, parameters, medicines, requiredParameters, optionalParameters)) {
                return;
            }

            String quantityToAdd = parameters.get(CommandParameters.QUANTITY);
            String expiryToAdd = parameters.get(CommandParameters.EXPIRY_DATE);

            Date formatExpiry = null;
            try {
                formatExpiry = DateParser.stringToDate(expiryToAdd);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int quantity = Integer.parseInt(quantityToAdd);

            boolean isValidDate =
                    StockValidator.dateValidityChecker(ui, medicines, formatExpiry, nameToAdd);

            if (!isValidDate) {
                logger.log(Level.WARNING, "Invalid Date is specified by user");
                logger.log(Level.INFO, "Unsuccessful addition of stock");
                return;
            }

            int existingMaxQuantity = existingStock.getMaxQuantity();
            int totalStock = MedicineManager.getTotalStockQuantity(medicines, nameToAdd);
            assert totalStock > 0 : "Total Stock should be more than 0";

            boolean isValidQuantity =
                    StockValidator.quantityValidityChecker(ui, (totalStock + quantity),
                            existingMaxQuantity);

            if (!isValidQuantity) {
                logger.log(Level.WARNING, "Invalid Quantity is specified by user");
                logger.log(Level.INFO, "Unsuccessful addition of stock");
                return;
            }

            String existingDescription = existingStock.getDescription();
            String priceToAdd = parameters.get(CommandParameters.PRICE);
            double price = Double.parseDouble(priceToAdd);
            addMedicine(ui, medicines, nameToAdd, existingDescription, price,
                    quantity, formatExpiry, existingMaxQuantity);

        } else {
            String[] requiredParameters = {CommandParameters.NAME, CommandParameters.PRICE,
                                           CommandParameters.QUANTITY, CommandParameters.EXPIRY_DATE,
                                           CommandParameters.DESCRIPTION, CommandParameters.MAX_QUANTITY};

            if (checkValidParametersAndValues(ui, parameters, medicines, requiredParameters, optionalParameters)) {
                return;
            }

            String priceToAdd = parameters.get(CommandParameters.PRICE);
            String quantityToAdd = parameters.get(CommandParameters.QUANTITY);
            String expiryToAdd = parameters.get(CommandParameters.EXPIRY_DATE);
            String descriptionToAdd = parameters.get(CommandParameters.DESCRIPTION);
            String maxQuantityToAdd = parameters.get(CommandParameters.MAX_QUANTITY);

            Date formatExpiry = null;
            try {
                formatExpiry = DateParser.stringToDate(expiryToAdd);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int maxQuantity = Integer.parseInt(maxQuantityToAdd);
            int quantity = Integer.parseInt(quantityToAdd);
            double price = Double.parseDouble(priceToAdd);
            addMedicine(ui, medicines, nameToAdd, descriptionToAdd, price, quantity, formatExpiry, maxQuantity);
        }

    }

    /**
     * Check if input contains Invalid Parameters and Invalid Parameter Values.
     *
     * @param ui                    Reference to the UI object passed by Main to print messages.
     * @param parameters            The parameter that is not found.
     * @param medicines             List of all medicines.
     * @param requiredParameters    The required parameters to check.
     * @param optionalParameters    The optional parameters to check.
     * @return                      Boolean value indicating if parameter and parameter values are valid.
     */
    private boolean checkValidParametersAndValues(Ui ui, HashMap<String, String> parameters,
                                                  ArrayList<Medicine> medicines, String[] requiredParameters,
                                                  String[] optionalParameters) {

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters, optionalParameters,
                CommandSyntax.ADD_COMMAND, false)) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful addition of stock");
            return true;
        }

        if (CommandSyntax.containsInvalidParameterValues(ui, parameters, medicines, CommandSyntax.ADD_COMMAND)) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful addition of stock");
            return true;
        }
        return false;
    }

    /**
     * Add medication based on user input.
     *
     * @param ui            Reference to the UI object passed by Main to print messages.
     * @param medicines     List of all medicines.
     * @param name          Name of medication to add.
     * @param description   Description of medication to add.
     * @param price         Price of medication to add.
     * @param quantity      Quantity of medication to add.
     * @param expiryDate    Expiry Date of medication to add.
     * @param maxQuantity   Maximum Quantity of medication to add.
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