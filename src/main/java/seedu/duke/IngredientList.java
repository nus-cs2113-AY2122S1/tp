package seedu.duke;

import java.util.ArrayList;
import java.util.Collections;

public class IngredientList {
    public static ArrayList<Ingredient> ingredientList = new ArrayList<>();
    private static Ui ui = new Ui();

    public static void add(String ingredientName, double ingredientWeight) {
        if (IngredientList.find(ingredientName) == -1) {
            Ingredient ingredientToAdd = new Ingredient(ingredientName, ingredientWeight);
            ingredientList.add(ingredientToAdd);
            System.out.println(ui.getLineDivider() + System.lineSeparator()
                    + "Ingredient added to list: " + ingredientToAdd.getIngredientName()
                    + " (Weight: " + ingredientWeight + " kg)");
        } else {
            System.out.println(ui.getIngrExistsMsg());
        }
    }

    //Returns -1 if not present, index if present
    public static int find(String ingredientName) {
        for (Ingredient ingredient : ingredientList) {
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
        System.out.println("You can use command 'add' to add new ingredients!");
    }

    public static void delete(String ingredientName) {
        int listSize = ingredientList.size(); //listSize = N
        int ingredientIndex = IngredientList.find(ingredientName);
        if (ingredientIndex == -1) {
            System.out.println(ui.getIngrNotExistMsg());
            assert ingredientList.size() == listSize : "ingredientList should be of size N";
        } else {
            //Get all dishes
            for (Dish dish : DishList.dishList) {
                //Find if they contain ingr in constituents
                ArrayList<Ingredient> constituents = dish.getConstituents();
                for (int i = 0; i < constituents.size(); i++) {
                    if (constituents.get(i).getIngredientName().equals(ingredientName)) {
                        constituents.remove(i);
                    }
                }
            }
            ingredientList.remove(ingredientIndex);
            System.out.println("Ingredient, " + ingredientName + " has been removed!");
            assert ingredientList.size() == (listSize - 1) : "ingredientList should be of size N-1";
        }
    }

    public static void clearList() {
        ingredientList.clear();
        assert ingredientList.size() == 0 : "ingredientList should be of size 0";
    }
}
