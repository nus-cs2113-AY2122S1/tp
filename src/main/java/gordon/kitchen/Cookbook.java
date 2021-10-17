package gordon.kitchen;

import gordon.exception.GordonException;

import java.util.ArrayList;
import java.util.Comparator;
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

    public String saveString(int index) {
        return recipes.get(index).toString();
    }

    public int numRecipes() {
        return recipes.size();
    }

    public void addRecipe(Recipe r) throws GordonException {
        boolean contains = recipes.stream()
                .map(Recipe::getName)
                .collect(Collectors.toCollection(ArrayList::new))
                .contains(r.name);

        if (contains) {
            throw new GordonException(GordonException.DUPLICATE_RECIPE_NAME);
        }

        recipes.add(r);
    }

    public void removeRecipe(int index) throws GordonException {
        try {
            assert (index > 0);
            recipes.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new GordonException(GordonException.INDEX_OOB);
        }
    }

    public void checkRecipe(String name) throws GordonException {
        System.out.println("Finding recipes called " + name + ".....");
        for (Recipe recipe : recipes) {
            // (?i) enables case insensitivity
            // .* uses all characters except line break
            if (recipe.getName().matches("(?i).*" + name + ".*")) {
                System.out.println("--------------------");
                System.out.print(recipe);
                System.out.println("--------------------");
                return;
            }
        }

        throw new GordonException(GordonException.NO_RESULT_FOUND);
    }

    public void setCalories(String name, int newCalories) throws GordonException {
        for (Recipe recipe : recipes) {
            // (?i) enables case insensitivity
            // .* uses all characters except line break
            if (recipe.getName().matches("(?i).*" + name + ".*")) {
                recipe.setCalories(newCalories);
                return;
            }
        }

        throw new GordonException(GordonException.NO_RESULT_FOUND);
    }

    public ArrayList<Recipe> filterByIngredients(ArrayList<String> ingredients) {
        return recipes.stream()
                .filter(r -> r.containsIngredients(ingredients))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Recipe> filterByTags(ArrayList<String> tags) {
        return recipes.stream()
                .filter(r -> r.containsTags(tags))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Recipe> filterByPrice(float price) {
        Comparator<Recipe> compareByPrice = Comparator.comparing(Recipe::getTotalPrice);
        return recipes.stream()
                .filter(r -> r.totalPrice <= price)
                .sorted(compareByPrice)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Recipe> filterByCalories(int cal) {
        Comparator<Recipe> compareByCalories = Comparator.comparing(Recipe::getCalories);
        return recipes.stream()
                .filter(r -> r.calories <= cal)
                .sorted(compareByCalories)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Recipe> filterByTime(int time) {
        Comparator<Recipe> compareByTime = Comparator.comparing(Recipe::getTotalTime);
        return recipes.stream()
                .filter(r -> r.getTotalTime() <= time)
                .sorted(compareByTime)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
