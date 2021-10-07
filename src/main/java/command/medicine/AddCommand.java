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

/**
 * Add medication based on user input.
 * User input include name, price, quantity, expiry date, description and maximum quantity of medication.
 */
public class AddCommand extends Command {

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        String[] requiredParameters = {CommandParameters.NAME, CommandParameters.PRICE, CommandParameters.QUANTITY,
            CommandParameters.EXPIRY_DATE, CommandParameters.DESCRIPTION, CommandParameters.MAX_QUANTITY};
        String[] optionalParameters = {};

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters, optionalParameters,
            CommandSyntax.ADD_COMMAND)) {
            return;
        }
        if (CommandSyntax.containsInvalidParameterValues(ui, parameters, medicines)) {
            return;
        }

        String nameToAdd = parameters.get(CommandParameters.NAME);
        String priceToAdd = parameters.get(CommandParameters.PRICE);
        String quantityToAdd = parameters.get(CommandParameters.QUANTITY);
        String expiryToAdd = parameters.get(CommandParameters.EXPIRY_DATE);
        String descriptionToAdd = parameters.get(CommandParameters.DESCRIPTION);
        String maxQuantityToAdd = parameters.get(CommandParameters.MAX_QUANTITY);

        try {
            double price = Double.parseDouble(priceToAdd);
            int quantity = Integer.parseInt(quantityToAdd);
            int maxQuantity = Integer.parseInt(maxQuantityToAdd);
            Date formatExpiry = DateParser.stringToDate(expiryToAdd);

            int totalStock = MedicineManager.getTotalStockQuantity(medicines, nameToAdd);

            for (Medicine medicine : medicines) {
                Stock existingStock = (Stock) medicine;
                String existingName = existingStock.getMedicineName().toUpperCase();
                Date existingExpiry = existingStock.getExpiry();
                String existingDescription = existingStock.getDescription();
                int existingMaxQuantity = existingStock.getMaxQuantity();
                boolean isValidQuantity =
                        StockValidator.quantityValidityChecker(ui,(totalStock + quantity),existingMaxQuantity);
                boolean nameExist = existingName.equals(nameToAdd.toUpperCase());
                boolean expiryExist = existingExpiry.equals(formatExpiry);

                if (nameExist & expiryExist) {
                    ui.print("You already have existing stocks! Use update instead.");
                    return;
                }
                if (nameExist & isValidQuantity) {
                    Stock stockToAdd = new Stock(nameToAdd, price, quantity, formatExpiry, existingDescription,
                            existingMaxQuantity);
                    medicines.add(stockToAdd);
                    ui.print("Medication added: " + nameToAdd);
                    ui.printMedicine(stockToAdd);
                    return;
                }
                if (isValidQuantity) {
                    ui.print("Stock added will exceed max quantity! Please try again with different quantity.");
                    return;
                }
            }
            Stock stock = new Stock(nameToAdd, price, quantity, formatExpiry, descriptionToAdd, maxQuantity);
            medicines.add(stock);
            ui.print("Medication added: " + nameToAdd);
            ui.printMedicine(stock);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}


