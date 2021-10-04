package seedu.duke;

import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;
import seedu.duke.ui.UI;

public class Duke {

    private static UI ui;
    private static IngredientList ingredientList;

    public Duke() {
    }

    /**
     * Starts up the system by creating the UI and the IngredientList.
     */
    public static void initialize() {
        //TODO: CREATE A METHOD IN UI CLASS TO PRINT THE GREETING (CAN BE CALLED IN CONSTRUCTOR)
        ui = new UI();
        ingredientList = new IngredientList(); //For now, no storage

    }

    /**
     * Loops until exit command received.
     */
    public static void runCommandLoopUntilExitCommand() {
        //we temporarily do without the command classes and parser class
        //assume all commands are valid

        /*
            Here are the expected syntax as per the feature list style (yet to update)
            add n/name a/amount u/units e/expiry date
            delete i/index (for now is index, in future to change to name)
            update i/index a/amount (amount should be negative if to subtract, positive if to add)
         */
        while (true) {
            String[] userInput = ui.getUserCommand().split(" ", 2); //Splits into command and parameters
            String command = userInput[0];
            String commandOutput = "";
            switch (command) {
            case "exit":
                exit();
                break;
            case "list":
                commandOutput = ingredientList.listAllIngredients();
                break;
            case "add":
                //fill in code to obtain the necessary parameters
                Ingredient newIngredient = new Ingredient("name", 200.0, "units", "expiry");
                commandOutput = ingredientList.addNewIngredient(newIngredient);
                break;
            case "delete":
                int indexToDelete = 999999; //write code to get the correct index
                commandOutput = ingredientList.deleteIngredient(indexToDelete);
                break;
            case "update":
                int indexToUpdate = 999999;
                double amount = 0;
                commandOutput = ingredientList.updateIngredient(indexToUpdate, amount);
                break;
            default:
                System.out.println("Unknown Command");
                break;
            }
            ui.printCommandOutput(commandOutput);

        }
    }

    /**
     * Prints the exit message, then closes the program.
     */
    public static void exit() {
        // TODO: CREATE GOODBYE FUNCTION IN UI CLASS
        ui.printGoodbye();
        System.exit(0);
    }

    public static void main(String[] args) {
        initialize();
        runCommandLoopUntilExitCommand();
        exit();
    }
}
