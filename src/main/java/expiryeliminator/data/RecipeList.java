package expiryeliminator.data;

import expiryeliminator.data.exception.IllegalValueException;

import java.util.Iterator;
import java.util.TreeMap;

import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Represents the recipe list and contains methods to add and remove recipes.
 */
public class RecipeList {
    private TreeMap<String, Recipe> recipes;

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
            recipes.put(recipe.getName(), recipe);
        }
    }

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
        recipes.remove(name);
        return recipe;
    }

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

    /**
     * Updates the quantities of ingredients in a recipe.
     *
     * @param recipes     The whole recipe list.
     * @param recipe      The recipe that is going to be updated.
     * @param ingredients The whole ingredient Repository.
     */
    public RecipeList updateRecipe(RecipeList recipes, Recipe recipe, IngredientRepository ingredients)
            throws NotFoundException, IllegalValueException, DuplicateDataException {
        Recipe matchedRecipe = recipes.findRecipe(recipe.getName());
        boolean hasMatchingIngredient = false;
        if (matchedRecipe == null) {
            return null;
        }
        for (IngredientQuantity newIngredient : recipe.getIngredientQuantities().values()) {
            for (IngredientQuantity originalIngredient : matchedRecipe.getIngredientQuantities().values()) {
                if (newIngredient.getName().equals(originalIngredient.getName())) {
                    hasMatchingIngredient = true;
                    originalIngredient.setQuantity(newIngredient.getQuantity());
                }
            }
            if (!hasMatchingIngredient) {
                matchedRecipe.add(newIngredient.getName(), newIngredient.getQuantity(), ingredients);
            }
            hasMatchingIngredient = false;
        }
        recipes.recipes.put(recipe.getName(), matchedRecipe);
        return recipes;
    }

    /**
     * Deletes ingredients in a recipe.
     *
     * @param recipes     The whole recipe list.
     * @param recipe      The recipe that is going to be updated.
     * @param ingredients The whole ingredient Repository.
     */
    public RecipeList deleteIngredientInRecipe(RecipeList recipes, Recipe recipe, IngredientRepository ingredients)
            throws NotFoundException, IllegalValueException, DuplicateDataException {
        Recipe matchedRecipe = recipes.findRecipe(recipe.getName());
        boolean hasMatchingIngredient = false;
        if (matchedRecipe == null) {
            return null;
        }
        for (IngredientQuantity newIngredient : recipe.getIngredientQuantities().values()) {

            // Use iterator to avoid ConcurrentModificationException
            Iterator<IngredientQuantity> iterator = matchedRecipe.getIngredientQuantities().values().iterator();
            while (iterator.hasNext()) {
                IngredientQuantity originalIngredient = iterator.next();
                if (newIngredient.getName().equals(originalIngredient.getName())) {
                    hasMatchingIngredient = true;
                    iterator.remove();
                }
            }
            if (!hasMatchingIngredient) {
                return null;
            }
            hasMatchingIngredient = false;
        }

        recipes.recipes.put(recipe.getName(), matchedRecipe);
        return recipes;
    }
}
