package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to clear the list of dishes and ingredients.
 * Format: clear all
 *
 * @author Rakesh12000
 */
public class ClearAllCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("ClearAllCommand.execute()");
    private static final Ui UI = new Ui();
    private static final String YES_NO_REGEX = "^(y|yes|n|no)$";
    private static final String Y_CHAR = "y";

    ClearAllCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to clear the list of dishes and ingredients.
     * This method calls .clear() methods in both DishList and IngredientList, clearing the list of dishes
     * and ingredients respectively.
     *
     * @author Rakesh12000
     */
    @Override
    public void execute(ArrayList<String> parameters)  {
        LOGGER.log(Level.INFO, "Start of process");
        Scanner input = new Scanner(System.in);
        UI.printConfirmClearAll();
        String confirmClear = input.nextLine().toLowerCase();
        while (!confirmClear.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmClear.startsWith(Y_CHAR)) {
            DishList.dishList.clear();
            IngredientList.ingredientList.clear();
            UI.printAllCleared();
            LOGGER.log(Level.INFO, "Successfully cleared both lists");
        } else {
            UI.printDisregardMsg();
        }
        LOGGER.log(Level.INFO, "End of process");
    }
}
