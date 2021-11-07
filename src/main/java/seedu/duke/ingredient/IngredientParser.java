//@@author leecheokfeng

package seedu.duke.ingredient;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses the input parameters entered by users to interact with the list of ingredients.
 * Allows users to add, remove, list and find ingredients.
 */
public class IngredientParser {

    /**
     * Adds a single ingredient to the list of ingredients.
     * Ingredient details are provided by the user via the command parameter.
     *
     * @param command contains the ingredient information input by the users.
     * @param ingredients refers to an existing list of ingredients where the
     *                    new ingredient will be added.
     * @throws ArrayIndexOutOfBoundsException If invalid parameters are entered by the user.
     * @throws DateTimeParseException If invalid date format is entered by the user.
     * @throws NumberFormatException If invalid number format is entered by the user.
     */
    public void addIngredient(String[] command, IngredientList ingredients) {
        try {
            String name = command[1].stripLeading().stripTrailing();
            if (name.length() == 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            int quantity = Integer.parseInt(command[2].stripLeading().stripTrailing());
            if (quantity < 1) {
                throw new NumberFormatException();
            }

            double price = Double.parseDouble(command[3]);
            String priceTwoDp = String.format("%.2f", price);
            if (Double.parseDouble(priceTwoDp) <= 0) {
                throw new NumberFormatException();
            }

            Ingredient newIngredient = new Ingredient(command[1].stripLeading().stripTrailing(),
                    command[2].stripLeading().stripTrailing(),
                    priceTwoDp.stripLeading().stripTrailing(),
                    LocalDate.parse(command[4]));

            ingredients.ingredientList.add(newIngredient);
            ingredients.totalIngredients++;

            IngredientUI.printAddIngredientMessage(newIngredient);

        } catch (ArrayIndexOutOfBoundsException e) {
            IngredientUI.printInvalidCommandSyntaxMessage("add");
        } catch (DateTimeParseException e) {
            IngredientUI.printInvalidCommandSyntaxMessage("add");
        } catch (NumberFormatException e) {
            IngredientUI.printInvalidCommandSyntaxMessage("add");
        }
    }

    /**
     * Adds a single ingredient (from storage location - Duke.txt) into the list of ingredients
     * Ingredient details are based on the contents of the storage file.
     *
     * @param ingredients refers to an existing list of ingredients where the
     *                    new ingredient will be added.
     * @param ingredient the new ingredient with details based on storage file contents.
     */
    public void loadIngredientFromStorage(IngredientList ingredients, Ingredient ingredient) {
        ingredients.ingredientList.add(ingredient);
        ingredients.totalIngredients++;
    }

    /**
     * Deletes a single ingredient from the list of ingredients.
     * The list number of the ingredient to be deleted must be specified by the user.
     *
     * @param command contains the list number of the ingredient to be deleted.
     * @param ingredients refers to an existing list of ingredients where a
     *                    selected ingredient will be deleted from the list.
     * @throws ArrayIndexOutOfBoundsException If an invalid parameter is entered by the user.
     * @throws IndexOutOfBoundsException If a non-existent list number is entered by the user.
     * @throws NumberFormatException If the list number entered by the user is of the wrong format.
     */
    public void deleteIngredient(String[] command, IngredientList ingredients) {
        try {
            int deletedIngredientIndex = Integer.parseInt(command[1]) - 1;

            Ingredient deletedIngredient = ingredients.ingredientList.get(deletedIngredientIndex);
            ingredients.ingredientList.remove(deletedIngredientIndex);
            ingredients.totalIngredients--;

            IngredientUI.printRemoveIngredientMessage(deletedIngredient);

        } catch (ArrayIndexOutOfBoundsException e) {
            IngredientUI.printInvalidCommandSyntaxMessage("remove");
        } catch (IndexOutOfBoundsException e) {
            IngredientUI.printInvalidIndexMessage();
        } catch (NumberFormatException e) {
            IngredientUI.printInvalidCommandSyntaxMessage("remove");
        }
    }

    /**
     * Lists all ingredients in the list and their respective information.
     * Listed information includes: name, quantity, price, expiry date.
     *
     * @param ingredients refers to the existing list of ingredients which
     *                    will be displayed to the user.
     */
    public void listIngredient(IngredientList ingredients) {
        if (ingredients.ingredientList.size() < 1) {
            IngredientUI.printEmptyListMessage();
            return;
        }
        assert ingredients.ingredientList.size() > 0 : "Ingredient list should not be empty";

        IngredientUI.printIngredientListMessage(ingredients);
    }

    /**
     * Finds all ingredients in the list that are expired by a particular date.
     * The date is chosen and input by the user. Ingredients with expiry dates earlier
     * than the input date are considered expired and will be displayed to the user.
     *
     * @param command contains the input date of the user.
     * @param ingredients refers to the existing list of ingredients to be searched.
     * @throws ArrayIndexOutOfBoundsException If an invalid parameter is entered by the user.
     * @throws DateTimeParseException If an invalid date format is entered by the user.
     */
    public void findExpiredIngredient(String[] command, IngredientList ingredients) {
        try {
            LocalDate inputDate = LocalDate.parse(command[1]);

            IngredientUI.printExpiredIngredientMessage(inputDate, ingredients);
        } catch (ArrayIndexOutOfBoundsException e) {
            IngredientUI.printInvalidCommandSyntaxMessage("find");
        } catch (DateTimeParseException e) {
            IngredientUI.printInvalidCommandSyntaxMessage("find");
        }
    }
}
