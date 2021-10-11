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

        boolean paraExist = false;
        for (String parameter : parameters.keySet()) {
            if (parameter.contains(CommandParameters.NAME)) {
                paraExist = true;
                break;
            }
        }
        if (!paraExist) {
            ui.print("Please provide all parameters!");
            ui.print("COMMAND SYNTAX: ADD N/NAME P/PRICE Q/QUANTITY E/EXPIRY_DATE D/DESCRIPTION M/MAX_QUANTITY");
        }

        String nameToAdd = parameters.get(CommandParameters.NAME);
        String priceToAdd = parameters.get(CommandParameters.PRICE);
        String quantityToAdd = parameters.get(CommandParameters.QUANTITY);
        String expiryToAdd = parameters.get(CommandParameters.EXPIRY_DATE);
        String descriptionToAdd = parameters.get(CommandParameters.DESCRIPTION);
        String maxQuantityToAdd = parameters.get(CommandParameters.MAX_QUANTITY);
        int totalStock = MedicineManager.getTotalStockQuantity(medicines, nameToAdd);

        boolean nameExist = false;
        Stock existingStock = null;

        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock && medicine.getMedicineName().equals(nameToAdd.toUpperCase())) {
                existingStock = (Stock) medicine;
                nameExist = true;
                break;
            }
        }

        String existingDescription = existingStock.getDescription();
        int existingMaxQuantity = existingStock.getMaxQuantity();

        String[] requiredParameters = {CommandParameters.NAME, CommandParameters.PRICE,
                                       CommandParameters.QUANTITY, CommandParameters.EXPIRY_DATE};
        String[] optionalParameters = {};

        if (checkValidParametersAndValues(ui, parameters, medicines, requiredParameters, optionalParameters)) {

            return;
        }

        if (nameExist) {
            String[] reqParameters = {CommandParameters.NAME, CommandParameters.PRICE,
                                      CommandParameters.QUANTITY, CommandParameters.EXPIRY_DATE};
            String[] optParameters = {CommandParameters.DESCRIPTION, CommandParameters.MAX_QUANTITY};

            if (checkValidParametersAndValues(ui, parameters, medicines,
                    reqParameters, optParameters)) {

                return;
            }

            Date formatExpiry = null;
            try {
                formatExpiry = DateParser.stringToDate(expiryToAdd);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int quantity = Integer.parseInt(quantityToAdd);
            double price = Double.parseDouble(priceToAdd);

            boolean isValidDate =
                    StockValidator.dateValidityChecker(ui, medicines, formatExpiry, nameToAdd);

            assert quantity > 0 : "Quantity should be more than 0";
            assert price > 0 : "Price should be more than 0";

            if (!isValidDate) {
                logger.log(Level.WARNING, "Invalid Date is specified by user");
                logger.log(Level.INFO, "Unsuccessful addition of stock");
                return;
            }

            boolean isValidQuantity =
                    StockValidator.quantityValidityChecker(ui, (totalStock + quantity),
                            existingMaxQuantity);

            if (!isValidQuantity) {
                logger.log(Level.WARNING, "Invalid Quantity is specified by user");
                logger.log(Level.INFO, "Unsuccessful addition of stock");
                return;
            }

            addMedicine(ui, medicines, nameToAdd, existingDescription, price,
                    quantity, formatExpiry, existingMaxQuantity);

        } else {
            String[] requiredParam = {CommandParameters.NAME, CommandParameters.PRICE,
                                      CommandParameters.QUANTITY, CommandParameters.EXPIRY_DATE,
                                      CommandParameters.DESCRIPTION, CommandParameters.MAX_QUANTITY};
            String[] optionalParam = {};

            if (checkValidParametersAndValues(ui, parameters, medicines, requiredParam,
                    optionalParam)) {

                return;
            }

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

    private boolean checkValidParametersAndValues(Ui ui, HashMap<String, String> parameters,
                                                  ArrayList<Medicine> medicines, String[] requiredParameters,
                                                  String[] optionalParameters) {

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters, optionalParameters,
                CommandSyntax.ADD_COMMAND, false)) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful addition of stock");
            return true;
        }

        if (CommandSyntax.containsInvalidParameterValues(ui, parameters, medicines)) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful addition of stock");
            return true;
        }

        return false;
    }

    private void addMedicine(Ui ui, ArrayList<Medicine> medicines, String name, String description,
                             double price, int quantity, Date expiryDate, int maxQuantity) {
        Stock stock = new Stock(name, price, quantity, expiryDate, description, maxQuantity);
        medicines.add(stock);
        ui.print("Medication added: " + name);
        ui.printStock(stock);
        logger.log(Level.INFO, "Successful addition of stock");
    }

}


