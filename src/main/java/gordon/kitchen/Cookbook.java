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
        for (Recipe recipe : recipes) {
            output.append(recipe.getID()).append(". ");
            output.append(recipe.getName());
            output.append(System.lineSeparator());
        }
        return output.toString();
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

        r.setId(recipes.size() + 1);
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

    public void checkRecipe(int index) throws GordonException {
        try {
            System.out.println(recipes.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new GordonException(GordonException.INDEX_OOB);
        } catch (IllegalArgumentException e) {
            throw new GordonException(GordonException.INDEX_INVALID);
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

    public String saveString(int index) {
        return recipes.get(index).toString();
    }

    public void sortByID() {
        Comparator<Recipe> compareByID = Comparator
                .comparing(Recipe::getID);
        recipes = recipes.stream()
                .sorted(compareByID)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void sortByPrice() {
        Comparator<Recipe> compareByPrice = Comparator.comparing(Recipe::getTotalPrice)
                .thenComparing(Recipe::getID);
        recipes = recipes.stream()
                .sorted(compareByPrice)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void sortByCalories() {
        Comparator<Recipe> compareByCalories = Comparator.comparing(Recipe::getCalories)
                .thenComparing(Recipe::getID);
        recipes = recipes.stream()
                .sorted(compareByCalories)
                .collect(Collectors.toCollection(ArrayList::new));
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
