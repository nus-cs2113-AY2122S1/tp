package seedu.duke.ingredient;

public class IngredientParser {

    public void addIngredient(String[] command, IngredientList ingredients) {
        try {
            Ingredient newIngredient = new Ingredient(command[1], command[2]);

            ingredients.ingredientList.add(newIngredient);
            ingredients.totalIngredients++;

            IngredientUI.printAddIngredientMessage(newIngredient);

        } catch (ArrayIndexOutOfBoundsException e) {
            IngredientUI.printInvalidCommandSyntaxMessage();
        }
    }

    public void loadIngredientFromStorage(IngredientList ingredients, Ingredient ingredient) {
        ingredients.ingredientList.add(ingredient);
        ingredients.totalIngredients++;
    }

    public void deleteIngredient(String[] command, IngredientList ingredients) {
        try {
            int deletedIngredientIndex = Integer.parseInt(command[1]) - 1;

            Ingredient deletedIngredient = ingredients.ingredientList.get(deletedIngredientIndex);
            ingredients.ingredientList.remove(deletedIngredientIndex);
            ingredients.totalIngredients--;

            IngredientUI.printRemoveIngredientMessage(deletedIngredient);

        } catch (ArrayIndexOutOfBoundsException e) {
            IngredientUI.printInvalidCommandSyntaxMessage();
        } catch (IndexOutOfBoundsException e) {
            IngredientUI.printInvalidIndexMessage();
        } catch (NumberFormatException e) {
            IngredientUI.printInvalidCommandSyntaxMessage();
        }
    }

    public void listIngredient(IngredientList ingredients) {
        if (ingredients.ingredientList.size() < 1) {
            IngredientUI.printEmptyListMessage();
            return;
        }
        assert ingredients.ingredientList.size() > 0 : "Ingredient list should not be empty";

        IngredientUI.printIngredientListMessage(ingredients);
    }
}
