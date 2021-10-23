package seedu.duke.command;

import seedu.duke.DishList;
import seedu.duke.IngredientList;
import seedu.duke.Ui;
import seedu.duke.exceptions.FoodoramaException;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearAllCommand extends Command {
    private static final Logger logger = Logger.getLogger("ClearAllCommand.execute()");
    private static final Ui ui = new Ui();

    ClearAllCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        logger.log(Level.INFO, "Start of process");
        Scanner input = new Scanner(System.in);
        ui.printConfirmClearAll();
        String confirmClear = input.nextLine().toLowerCase();
        while (!(confirmClear.equals("y") | confirmClear.equals("n"))) {
            ui.clearTerminalAndPrintNewPage();
            ui.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        ui.clearTerminalAndPrintNewPage();
        if (confirmClear.equals("y")) {
            DishList.dishList.clear();
            IngredientList.ingredientList.clear();
            ui.printAllCleared();
            logger.log(Level.INFO, "Successfully cleared both lists");
        } else {
            ui.printDisregardMsg();
        }
        logger.log(Level.INFO, "End of process");
    }
}
