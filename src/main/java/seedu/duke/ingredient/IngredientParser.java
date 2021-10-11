package seedu.duke.ingredient;

import seedu.duke.main.MainUI;

public class IngredientParser {

    public void addIngredient(String[] command, IngredientList ingredients) {
        Ingredient newIngredient = new Ingredient(command[1], command[2]);

        ingredients.ingredientList.add(newIngredient);
        ingredients.totalIngredients++;

        MainUI.printSingleLine();
        System.out.println("Got it. This ingredient was added:");
        System.out.println("Ingredient Name: " + newIngredient.getName());
        System.out.println("Ingredient Quantity: " + newIngredient.getQuantity());
        MainUI.printSingleLine();
    }

    public void loadIngredientFromStorage(IngredientList ingredients, Ingredient ingredient) {
        ingredients.ingredientList.add(ingredient);
        ingredients.totalIngredients++;
    }

    public void deleteIngredient(String[] command, IngredientList ingredients) {
        int deletedIngredientIndex = Integer.parseInt(command[1]) - 1;

        if (ingredients.ingredientList.size() < 1) {
            return;
        }
        Ingredient deletedIngredient = ingredients.ingredientList.get(deletedIngredientIndex);
        ingredients.ingredientList.remove(deletedIngredientIndex);
        ingredients.totalIngredients--;

        MainUI.printSingleLine();
        System.out.println("Got it. This ingredient was deleted:");
        System.out.println(deletedIngredient.getName());
        MainUI.printSingleLine();
    }

    public void listIngredient(IngredientList ingredients) {
        if (ingredients.ingredientList.size() < 1) {
            MainUI.printSingleLine();
            System.out.println("No ingredients found.");
            MainUI.printSingleLine();
            return;
        }

        MainUI.printSingleLine();
        System.out.println("Here are the ingredients in your list:");
        for (int i = 0; i < ingredients.ingredientList.size(); i++) {
            System.out.println((i + 1) + ". " + ingredients.ingredientList.get(i));
        }
        MainUI.printSingleLine();
    }
}
