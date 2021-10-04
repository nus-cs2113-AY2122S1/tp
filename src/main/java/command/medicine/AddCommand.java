package command.medicine;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Stock;
import inventory.Medicine;
import parser.DateParser;
import parser.StockValidator;
import ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Add medication based on user input.
 * User input include name, price, quantity, expiry date, description and maximum quantity of medication.
 */

public class AddCommand extends Command {

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        String[] requiredParameters = {CommandParameters.NAME, CommandParameters.PRICE, CommandParameters.QUANTITY,
            CommandParameters.EXPIRY_DATE, CommandParameters.DESCRIPTION, CommandParameters.MAX_QUANTITY};

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters, CommandSyntax.ADD_COMMAND)) {
            return;
        }

        String nameToAdd = parameters.get(CommandParameters.NAME);
        String priceToAdd = parameters.get(CommandParameters.PRICE);
        String quantityToAdd = parameters.get(CommandParameters.QUANTITY);
        String expiryToAdd = parameters.get(CommandParameters.EXPIRY_DATE);
        String descriptionToAdd = parameters.get(CommandParameters.DESCRIPTION);
        String maxQuantityToAdd = parameters.get(CommandParameters.MAX_QUANTITY);

        for (String parameter : parameters.keySet()) {
            switch (parameter) {
            case CommandParameters.NAME:
                if (!StockValidator.isValidName(ui, nameToAdd)) {
                    return;
                }
                break;
            case CommandParameters.PRICE:
                if (!StockValidator.isValidPrice(ui, priceToAdd)) {
                    return;
                }
                break;
            case CommandParameters.QUANTITY:
                if (!StockValidator.isValidQuantity(ui, quantityToAdd)) {
                    return;
                }
                break;
            case CommandParameters.EXPIRY_DATE:
                if (!StockValidator.isValidExpiry(ui, expiryToAdd)) {
                    return;
                }
                break;
            case CommandParameters.DESCRIPTION:
                if (!StockValidator.isValidDescription(ui, descriptionToAdd)) {
                    return;
                }
                break;
            case CommandParameters.MAX_QUANTITY:
                if (!StockValidator.isValidMaxQuantity(ui, maxQuantityToAdd)) {
                    return;
                }
                break;
            default:
                ui.printInvalidParameter(parameter, CommandSyntax.ADD_COMMAND);
                break;
            }
        }

        try {
            double price = Double.parseDouble(priceToAdd);
            int quantity = Integer.parseInt(quantityToAdd);
            int maxQuantity = Integer.parseInt(maxQuantityToAdd);
            Date dateObj = DateParser.stringToDate(expiryToAdd);
            Stock stock = new Stock(nameToAdd, price, quantity, dateObj, descriptionToAdd, maxQuantity);
            medicines.add(stock);
            ui.print("Medication added: " + nameToAdd);
            ui.printMedicine(stock);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
