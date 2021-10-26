package seedu.commands;

import seedu.data.exception.FridgetException;
import seedu.parser.Parser;
import seedu.storage.IngredientList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

public class Command {
    protected boolean isExit = false;

    /**
     * Constructor for Command.
     */
    public Command() {
    }

    /**
     * Check status of program, if it should end.
     *
     * @return true if program should keep running and false when exit is called.
     */
    public boolean exitNotRequired() {
        return !isExit;
    }

    /**
     * Executes the command.
     *
     * @param ui The ui object to interact with user.
     * @param parser The parser object to parse user inputs.
     * @param ingredientList The ingredientList object.
     * @param shoppingList The shoppingList object.
     * @throws FridgetException The error object thrown.
     */
    public void execute(Ui ui, Parser parser, IngredientList ingredientList, ShoppingList shoppingList)
            throws FridgetException {
    }
}
