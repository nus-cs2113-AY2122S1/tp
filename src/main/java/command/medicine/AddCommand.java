package command.medicine;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Stock;
import parser.DateParser;
import parser.MedicineValidator;
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
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Stock> stocks) {
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
                if (!MedicineValidator.isValidName(ui, nameToAdd)) {
                    return;
                }
                break;
            case CommandParameters.PRICE:
                if (!MedicineValidator.isValidPrice(ui, priceToAdd)) {
                    return;
                }
                break;
            case CommandParameters.QUANTITY:
                if (!MedicineValidator.isValidQuantity(ui, quantityToAdd)) {
                    return;
                }
                break;
            case CommandParameters.EXPIRY_DATE:
                if (!MedicineValidator.isValidExpiry(ui, expiryToAdd)) {
                    return;
                }
                break;
            case CommandParameters.DESCRIPTION:
                if (!MedicineValidator.isValidDescription(ui, descriptionToAdd)) {
                    return;
                }
                break;
            case CommandParameters.MAX_QUANTITY:
                if (!MedicineValidator.isValidMaxQuantity(ui, maxQuantityToAdd)) {
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
            Medicine medicine = new Medicine(nameToAdd, price, quantity, dateObj, descriptionToAdd, maxQuantity);
            stocks.add(medicine);
            ui.print("Medication added: " + nameToAdd);
            ui.printMedicine(medicine);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
