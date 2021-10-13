package expiryeliminator;

import expiryeliminator.commands.Command;
import expiryeliminator.data.IngredientList;
import expiryeliminator.data.RecipeList;
import expiryeliminator.parser.Parser;
import expiryeliminator.ui.Ui;

/**
 * Entry point of the ExpiryEliminator application.
 * Initialises the application and starts the interaction with the user.
 */
public class ExpiryEliminator {
    private static Ui ui;
    private static IngredientList ingredients;
    private static RecipeList recipes;

    /**
     * Initialises the ExpiryEliminator application.
     */
    public ExpiryEliminator() {
        ui = new Ui();
        ingredients = new IngredientList();
        recipes = new RecipeList();
    }

    //@@author bernardboey-reused
    // Reused from https://github.com/bernardboey/ip/blob/master/src/main/java/duke/ui/Ui.java
    // with minor modifications

    /**
     * Runs the program.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            final String userInput = ui.getUserInput();
            final Command command = Parser.parseCommand(userInput);
            isExit = command.isExit();
            final String feedback = command.execute(ingredients, recipes);
            ui.showToUser(feedback);
        }
    }
    //@author

    /**
     * Main entry-point for the ExpiryEliminator application.
     */
    public static void main(String[] args) {
        new ExpiryEliminator().run();
    }
}
