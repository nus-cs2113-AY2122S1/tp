package seedu.duke;

import java.util.ArrayList;

public class IngredientList {
    public static ArrayList<Ingredient> ingredientList = new ArrayList<>();

    public static void add(String ingredientName, double ingredientWeight) {
        if (IngredientList.find(ingredientName) == -1) {
            Ingredient ingredientToAdd = new Ingredient(ingredientName, ingredientWeight);
            ingredientList.add(ingredientToAdd);
            System.out.println("Added:" + ingredientToAdd.getIngredientName() + " " + ingredientWeight);
        } else {
            System.out.println("Ingredient already exists");
        }
    }

    //Returns -1 if not present, index if present
    public static int find(String ingredientName) {
        for (Ingredient ingredient: ingredientList) {
            if (ingredient.getIngredientName().equals(ingredientName)) {
                return ingredientList.indexOf(ingredient);
            }
        }
        return -1;
    }

    public static void list() {
        System.out.println("Here are the ingredients you have: ");
        for (int i = 0; i < ingredientList.size(); i++) {
            System.out.println((i + 1) + ". " + ingredientList.get(i));
        }
        System.out.println("You can use commands 'add' or 'find' to add new ingredients or find existing ones!");
    }

    public static void delete(String ingredientName) {
        int ingredientIndex = IngredientList.find(ingredientName);
        if (ingredientIndex == -1) {
            System.out.println("Ingredient does not exist");
        } else {
            ingredientList.remove(ingredientIndex);
            System.out.println("Ingredient, " + ingredientName + " has been removed!");
        }
    }
}
