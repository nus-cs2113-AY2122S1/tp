package seedu.duke;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public class Cookbook {
    protected ArrayList<Recipe> recipes;

    public Cookbook() {
        recipes = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < recipes.size(); i++) {
            output.append(i + 1).append(". ");
            output.append(recipes.get(i).getName());
            output.append(System.lineSeparator());
        }
        return output.toString();
    }

    public void addRecipe(Recipe r) {
        r.setId(recipes.size() + 1);
        recipes.add(r);
    }

    public void removeRecipe(int index) {
        recipes.remove(index);
    }

    public void checkRecipe(int index) {
        System.out.println(recipes.get(index).toString());
    }

    public void checkRecipe(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                System.out.println(recipe);
            }
        }
    }

    public void sortByID () {
        Comparator<Recipe> compareByID = Comparator
                .comparing(Recipe::getID);
        recipes = recipes.stream()
                .sorted(compareByID)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void sortByPrice () {
        Comparator<Recipe> compareByPrice = Comparator.comparing(Recipe::getTotalPrice)
                .thenComparing(Recipe::getID);
        recipes = recipes.stream()
                .sorted(compareByPrice)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void sortByCalories () {
        Comparator<Recipe> compareByCalories = Comparator.comparing(Recipe::getCalories)
                .thenComparing(Recipe::getID);
        recipes = recipes.stream()
                .sorted(compareByCalories)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void sortByTime () {
        //Comparator<Recipe> compareByTime = Comparator.comparing(Recipe::getTotalPrice).thenComparing(Recipe::getID);
        //recipes = recipes.stream().sorted(compareByCalories).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Recipe> filterByTag(String tag) {
        Comparator<Recipe> compareByID = Comparator
                .comparing(Recipe::getID);
        return recipes.stream()
                .filter(r -> r.getTags().contains(tag.toLowerCase()))
                .sorted(compareByID)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
