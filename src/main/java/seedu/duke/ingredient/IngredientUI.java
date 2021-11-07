//@@author leecheokfeng

package seedu.duke.ingredient;

import seedu.duke.main.MainUI;

import java.time.LocalDate;

/**
 * Prints UI messages to interact with the users.
 */
public class IngredientUI {

    /**
     * Prints a message when an ingredient is added to the list of ingredients.
     * Informs the user that the new ingredient was added successfully.
     *
     * @param newIngredient the new ingredient to be added to the ingredient list.
     */
    public static void printAddIngredientMessage(Ingredient newIngredient) {
        MainUI.printSingleLine();
        System.out.println(" Got it. This ingredient was added:");
        System.out.println("   Ingredient Name: " + newIngredient.getName());
        System.out.println("   Ingredient Quantity: " + newIngredient.getQuantity());
        System.out.println("   Ingredient Unit Price: " + newIngredient.getPrice());
        System.out.println("   Expiry Date: " + newIngredient.getExpiry());
        System.out.println("\n   " + newIngredient);
        MainUI.printSingleLine();
    }

    /**
     * Prints a message when the user enters a command with invalid syntax/format.
     * Informs the user on the proper command syntax and gives a link to the user guide.
     *
     * @param command refers to the command type entered by the user, so that the corresponding
     *                correct command syntax can be shown to the user.
     */
    public static void printInvalidCommandSyntaxMessage(String command) {
        MainUI.printSingleLine();
        System.out.println(" Invalid command syntax!");
        printSampleCommandSyntaxMessage(command);
        System.out.println(" Please refer to our user guide for more details: " +
                "https://ay2122s1-cs2113t-t12-4.github.io/tp/UserGuide.html");
        MainUI.printSingleLine();
    }

    /**
     * Prints a message when an ingredient is removed from the list of ingredients.
     * Informs the user that the selected ingredient was removed successfully.
     *
     * @param deletedIngredient the selected ingredient to be removed from the list.
     */
    public static void printRemoveIngredientMessage(Ingredient deletedIngredient) {
        MainUI.printSingleLine();
        System.out.println(" Got it. This ingredient was deleted:");
        System.out.println("   " + deletedIngredient);
        MainUI.printSingleLine();
    }

    /**
     * Prints a message when an invalid index number of the ingredient list is entered.
     * Informs the user that an ingredient with the index number they have selected does not exist.
     */
    public static void printInvalidIndexMessage() {
        MainUI.printSingleLine();
        System.out.println(" That ingredient does not exist!");
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to inform users when the ingredient list is empty.
     */
    public static void printEmptyListMessage() {
        MainUI.printSingleLine();
        System.out.println(" No ingredients found.");
        MainUI.printSingleLine();
    }

    /**
     * Prints a message which lists all ingredients in the list, and their details.
     *
     * @param ingredients the list of ingredients to be displayed to the user.
     */
    public static void printIngredientListMessage(IngredientList ingredients) {
        MainUI.printSingleLine();
        System.out.println(" Here are the ingredients in your list:");
        for (int i = 0; i < ingredients.ingredientList.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + ingredients.ingredientList.get(i));
        }
        MainUI.printSingleLine();
    }

    /**
     * Prints a message which lists all expired ingredients based on an input date.
     * Ingredients with expiry dates earlier than the input date are considered
     * expired and will be displayed to the user.
     *
     * @param inputDate the date input by the user, in YYYY-MM-DD format.
     * @param ingredients the list of ingredients to be searched.
     */
    public static void printExpiredIngredientMessage(LocalDate inputDate, IngredientList ingredients) {
        boolean hasExpiredIngredients = false;
        MainUI.printSingleLine();
        System.out.println(" These ingredients are expired:");

        for (int i = 0; i < ingredients.ingredientList.size(); i++) {
            if (ingredients.ingredientList.get(i).getExpiry().isBefore(inputDate)) {
                System.out.println("   " + ingredients.ingredientList.get(i));
                hasExpiredIngredients = true;
            }
        }
        if (!hasExpiredIngredients) {
            System.out.println("   " + "None :)");
        }
        MainUI.printSingleLine();
    }

    /**
     * Prints a message when the user enters a command with invalid syntax.
     * Informs the user of the correct command syntax.
     *
     * @param command refers to the command type entered by the user, so that the corresponding
     *                correct command syntax can be shown to the user.
     */
    public static void printSampleCommandSyntaxMessage(String command) {
        switch (command) {
        case "add":
            System.out.println(" Please follow this syntax:");
            System.out.println("   add-ingredient/INGREDIENT_NAME/QUANTITY/PRICE/EXPIRY_DATE");
            System.out.println("   <INGREDIENT_NAME> must be a string.");
            System.out.println("   <QUANTITY> must be a positive, non-zero integer.");
            System.out.println("   <PRICE> must be a positive number greater than 0.01.");
            System.out.println("   <EXPIRY_DATE> must be a valid date in YYYY-MM-DD format.");
            break;
        case "remove":
            System.out.println(" Please follow this syntax:");
            System.out.println("   remove-ingredient/INGREDIENT_INDEX");
            System.out.println("   <INGREDIENT_INDEX> must be a positive integer corresponding to the list index.");
            break;
        case "find":
            System.out.println(" Please follow this syntax:");
            System.out.println("   find-expired-ingredient/INPUT_DATE");
            System.out.println("   <INPUT_DATE> must be a valid date in YYYY-MM-DD format.");
            break;
        default:
            break;
        }
    }
}