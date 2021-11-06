package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearAllCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("ClearAllCommand.execute()");
    private static final Ui UI = new Ui();

    ClearAllCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        Scanner input = new Scanner(System.in);
        UI.printConfirmClearAll();
        String confirmClear = input.nextLine().toLowerCase();
        while (!confirmClear.matches("^(y|yes|n|no)$")) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmClear.startsWith("y")) {
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
