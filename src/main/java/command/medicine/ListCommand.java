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
import java.util.stream.Collectors;

/**
 * Helps to process the list command and validate the user's input.
 */

public class ListCommand extends Command {

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        ArrayList<Medicine> filteredMedicines = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock) { // Ensure that it is a medicine object
                filteredMedicines.add(medicine);
            }
        }
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.STOCK_ID:
                if (!StockValidator.isValidStockId(ui, parameterValue, medicines)) {
                    return;
                }
                filteredMedicines = (ArrayList<Medicine>) filteredMedicines.stream()
                        .filter((m) -> ((Stock) m).getStockID() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.NAME:
                if (!StockValidator.isValidName(ui, parameterValue)) {
                    return;
                }
                filteredMedicines = (ArrayList<Medicine>) filteredMedicines.stream()
                        .filter((m) -> m.getMedicineName().equals(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.PRICE:
                if (!StockValidator.isValidPrice(ui, parameterValue)) {
                    return;
                }
                filteredMedicines = (ArrayList<Medicine>) filteredMedicines.stream()
                        .filter((m) -> ((Stock) m).getPrice() == Double.parseDouble(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.QUANTITY:
                // Implement search by quantity
                break;
            case CommandParameters.EXPIRY_DATE:
                if (!StockValidator.isValidExpiry(ui, parameterValue)) {
                    return;
                }
                try {
                    Date expiryDate = DateParser.stringToDate(parameterValue);
                    filteredMedicines = (ArrayList<Medicine>) filteredMedicines.stream()
                            .filter((m) -> ((Stock) m).getExpiry().equals(expiryDate))
                            .collect(Collectors.toList());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case CommandParameters.DESCRIPTION:
                if (!StockValidator.isValidDescription(ui, parameterValue)) {
                    return;
                }
                filteredMedicines = (ArrayList<Medicine>) filteredMedicines.stream()
                        .filter((m) -> ((Stock) m).getDescription().equals(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.MAX_QUANTITY:
                // Implement search by max quantity
                break;
            default:
                ui.printInvalidParameter(parameter, CommandSyntax.LIST_COMMAND);
                return;
            }
        }
        ui.printMedicines(filteredMedicines);
    }
}
