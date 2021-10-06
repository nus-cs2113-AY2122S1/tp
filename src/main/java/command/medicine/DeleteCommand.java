package command.medicine;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Stock;
import inventory.Medicine;
import parser.StockValidator;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Delete medication based on user input given stock id.
 */

public class DeleteCommand extends Command {

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        String[] requiredParameters = {CommandParameters.STOCK_ID};
        String[] optionalParameters = {};

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters, optionalParameters,
            CommandSyntax.DELETE_COMMAND)) {
            return;
        }

        String stockIdToDelete = parameters.get(CommandParameters.STOCK_ID);
        if (!StockValidator.isValidStockId(ui, stockIdToDelete, medicines)) {
            return;
        }

        int stockId = Integer.parseInt(stockIdToDelete);
        for (Medicine medicine : medicines) {
            Stock stock = (Stock) medicine;
            if (stock.getStockID() == stockId) {
                medicines.remove(medicine);
                break;
            }
        }
        ui.print("Medication deleted: Stock_Id=" + stockId);
    }
}
