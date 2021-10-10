package expiryeliminator.data;

import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.EmptyIngredientsException;
import expiryeliminator.data.exception.RecipeNotFoundException;

import java.util.HashMap;

/**
 * Represents the recipe list and contains methods to add and remove recipes.
 */
public class RecipeList {
    private final HashMap<String,Recipe> recipes;

    /**
     * Constructs Recipe List with no recipes.
     */
    public RecipeList() {
        recipes = new HashMap<>();
    }

    /**
     * Adds a recipe to the recipe list.
     *
     * @param recipe Recipe to be added
     * @throws DuplicateDataException If the recipe already exists.
     * @throws EmptyIngredientsException If there are no ingredients in the recipe.
     */
    public void add(Recipe recipe) throws DuplicateDataException, EmptyIngredientsException {
        if (recipes.containsKey(recipe.getName())) {
            throw new DuplicateDataException();
        } else if (recipe.getIngredients().size() == 0) {
            throw new EmptyIngredientsException();
        } else {
            recipes.put(recipe.getName(), recipe);
        }
    }

    public Recipe remove(String name) throws RecipeNotFoundException {
        if(recipes.get(name) == null) {
            throw new RecipeNotFoundException();
        }
        Recipe recipe = recipes.get(name);
        recipes.remove(name);
        return recipe;
    }

    /**
     * Returns { @code true } if the recipe list contains the given recipe.
     *
     * @param recipe The recipe whose presence in the list is to be tested
     * @return { @code true } if the recipe list contains the given recipe.
     */
    public boolean contains(Recipe recipe) {
        return recipes.containsKey(recipe);
    }

    /**
     * Returns the number of recipes in the list.
     *
     * @return Number of recipes in the list.
     */
    public int size() {
        return recipes.size();
    }

    /**
     * Returns a string representation of the whole recipe list.
     *
     * @return returns a string representing all the recipes.
     */
    public String getWholeRecipeList() {
        String wholeRecipeList = "";
        for (Recipe recipe : recipes.values()) {
            wholeRecipeList = wholeRecipeList + recipe.toString() + "\n";
        }
        return wholeRecipeList;
    }

    /**
     * Looks for the recipe that the user is looking for.
     *
     * @param recipeDescription The recipe name the user is searching for.
     * @return the recipe object that the user is searching for.
     */
    public Recipe findRecipe(String recipeDescription) {
        return recipes.get(recipeDescription);
    }

}
