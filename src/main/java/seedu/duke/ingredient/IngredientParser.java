package seedu.duke.ingredient;

public class IngredientParser {

    public void addIngredient(IngredientList ingredients, Ingredient ingredient) {
        ingredients.ingredientList.add(ingredient);
        ingredients.totalIngredients++;

        System.out.println("--------------------");
        System.out.println("Got it. This ingredient was added:");
        System.out.println("Ingredient Name: " + ingredient.getName());
        System.out.println("Ingredient Quantity: " + ingredient.getQuantity());
        System.out.println("--------------------");
    }

    public void deleteIngredient(IngredientList ingredients, int ingredientIndex) {
        if (ingredients.ingredientList.size() < 1) {
            return;
        }
        Ingredient deletedIngredient = ingredients.ingredientList.get(ingredientIndex);
        ingredients.ingredientList.remove(ingredientIndex);
        ingredients.totalIngredients--;

        System.out.println("--------------------");
        System.out.println("Got it. This ingredient was deleted:");
        System.out.println(deletedIngredient.getName());
        System.out.println("--------------------");
    }

    public void listIngredient(IngredientList ingredients) {
        if (ingredients.ingredientList.size() < 1) {
            System.out.println("--------------------");
            System.out.println("No ingredients found.");
            System.out.println("--------------------");
            return;
        }

        System.out.println("--------------------");
        System.out.println("Here are the ingredients in your list:");
        for (int i = 0; i < ingredients.ingredientList.size(); i++) {
            System.out.println((i + 1) + ". " + ingredients.ingredientList.get(i));
        }
        System.out.println("--------------------");
    }
}
