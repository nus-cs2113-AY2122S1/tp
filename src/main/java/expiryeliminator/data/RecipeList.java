package expiryeliminator.data;

import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import expiryeliminator.common.LogsCenter;
import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Represents the recipe list and contains methods to add and remove recipes.
 */
public class RecipeList {
    private TreeMap<String, Recipe> recipes;
    private static final Logger logger = LogsCenter.getLogger(RecipeList.class);

    /**
     * Constructs Recipe List with no recipes.
     */
    public RecipeList() {
        recipes = new TreeMap<>();
    }

    /**
     * Adds a recipe to the recipe list.
     *
     * @param recipe Recipe to be added
     * @throws DuplicateDataException If the recipe already exists.
     */
    public void add(Recipe recipe) throws DuplicateDataException {
        if (recipes.containsKey(recipe.getName())) {
            throw new DuplicateDataException();
        } else {
            logger.log(Level.INFO,String.format("Adding recipe %1$s into RecipeList",recipe.getName()));
            recipes.put(recipe.getName(), recipe);
        }
    }

    //@@author vincentlauhl
    /**
     * Removes a recipe from the recipe list.
     *
     * @param name name of the recipe to be removed
     * @return The recipe removed.
     * @throws NotFoundException If there are no such recipes in the list
     */
    public Recipe remove(String name) throws NotFoundException {
        Recipe recipe = recipes.get(name);
        if (recipe == null) {
            throw new NotFoundException();
        }
        logger.log(Level.INFO,String.format("Removing recipe %1$s in RecipeList",recipe.getName()));
        recipes.remove(name);
        return recipe;
    }
    //@@author

    /**
     * Returns the list of recipes.
     *
     * @return The list of recipes.
     */
    public TreeMap<String, Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(TreeMap<String, Recipe> inputRecipes) {
        this.recipes = inputRecipes;
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
        StringBuilder wholeRecipeList = new StringBuilder();
        for (Recipe recipe : recipes.values()) {
            wholeRecipeList.append("\n").append(recipe.toString());
        }
        return wholeRecipeList.toString();
    }

    /**
     * Looks for the recipe that the user is looking for.
     *
     * @param recipeDescription The recipe name the user is searching for.
     * @return the recipe object that the user is searching for.
     */
    public Recipe findRecipe(String recipeDescription) throws NotFoundException {
        final Recipe recipe = recipes.get(recipeDescription);
        if (recipe == null) {
            throw new NotFoundException();
        }
        return recipe;
    }

    /**
     * Updates units of specified ingredient in all recipes.
     *
     * @param ingredientName The name of the ingredient.
     * @param newUnits       The new units to change to.
     */
    public void updateUnits(String ingredientName, String newUnits) {
        //iterate through all recipes
        for (Recipe recipe : recipes.values()) {
            //iterate through all the ingredients in the recipe
            for (String ingredientInRecipeName : recipe.getIngredientQuantities().keySet()) {
                if (ingredientInRecipeName.equals(ingredientName)) {
                    recipe.getIngredientQuantities().get(ingredientInRecipeName).getIngredient().setUnit(newUnits);
                }
            }
        }
    }
}
