package seedu.duke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Cookbook {
    ArrayList<Recipe> recipes;

    public Cookbook() {
        recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void removeRecipe(int index) {
        recipes.remove(index);
    }

    public void sortByPrice () {
        Collections.sort(recipes);
        System.out.println("Recipes by price: ");
        for (int i = 0; i < recipes.size(); i++) {
            Recipe currRecipe = recipes.get(i);
            System.out.println((i + 1) + ". " + currRecipe.getName() + ": "
                    + String.format("%.2f", currRecipe.getTotalPrice()));
        }
    }
}
