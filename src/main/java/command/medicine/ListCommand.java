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
import java.util.stream.Collectors;

/**
 * Helps to process the list command and validate the user's input.
 */

public class ListCommand extends Command {

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Stock> stocks) {
        ArrayList<Stock> filteredMedicines = new ArrayList<>();
        for (Stock stock : stocks) {
            if (stock instanceof Medicine) { // Ensure that it is a medicine object
                filteredMedicines.add(stock);
            }
        }
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.STOCK_ID:
                if (!MedicineValidator.isValidStockId(ui, parameterValue, stocks)) {
                    return;
                }
                filteredMedicines = (ArrayList<Stock>) filteredMedicines.stream()
                        .filter((m) -> ((Medicine) m).getStockID() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.NAME:
                if (!MedicineValidator.isValidName(ui, parameterValue)) {
                    return;
                }
                filteredMedicines = (ArrayList<Stock>) filteredMedicines.stream()
                        .filter((m) -> m.getMedicineName().equals(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.PRICE:
                if (!MedicineValidator.isValidPrice(ui, parameterValue)) {
                    return;
                }
                filteredMedicines = (ArrayList<Stock>) filteredMedicines.stream()
                        .filter((m) -> ((Medicine) m).getPrice() == Double.parseDouble(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.QUANTITY:
                // Implement search by quantity
                break;
            case CommandParameters.EXPIRY_DATE:
                if (!MedicineValidator.isValidExpiry(ui, parameterValue)) {
                    return;
                }
                try {
                    Date expiryDate = DateParser.stringToDate(parameterValue);
                    filteredMedicines = (ArrayList<Stock>) filteredMedicines.stream()
                            .filter((m) -> ((Medicine) m).getExpiry().equals(expiryDate))
                            .collect(Collectors.toList());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case CommandParameters.DESCRIPTION:
                if (!MedicineValidator.isValidDescription(ui, parameterValue)) {
                    return;
                }
                filteredMedicines = (ArrayList<Stock>) filteredMedicines.stream()
                        .filter((m) -> ((Medicine) m).getDescription().equals(parameterValue))
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
