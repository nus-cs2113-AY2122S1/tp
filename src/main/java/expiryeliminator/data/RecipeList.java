package expiryeliminator.data;

import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.EmptyIngredientsException;

import java.util.HashMap;

/**
 * Represents the recipe list and contains methods to add and remove ingredients.
 */
public class RecipeList {
    private final HashMap <String,Recipe> recipes;

    /**
     * Constructs Recipe List with no recipes.
     */
    public RecipeList() {recipes = new HashMap<>();}

    /**
     * Adds a recipe to the recipe list.
     *
     * @param recipe Recipe to be added
     * @throws DuplicateDataException If the recipe already exists.
     * @throws EmptyIngredientsException If there are no ingredients in the recipe.
     */
    public void add(Recipe recipe) throws DuplicateDataException, EmptyIngredientsException {
        if(contains(recipe)) {
            throw new DuplicateDataException();
        } else if (recipe.getIngredients().size() == 0){
            throw new EmptyIngredientsException();
        } else {
            recipes.put(recipe.getName(), recipe);
        }
    }

    /**
     * Returns {@code true} if the recipe list contains the given recipe.
     *
     * @param recipe The recipe whose presence in the list is to be tested
     * @return {@code true} if the recipe list contains the given recipe.
     */
    public boolean contains(Recipe recipe) {return recipes.containsKey(recipe);}

    /**
     * Returns the number of recipes in the list.
     *
     * @return Number of recipes in the list.
     */
    public int size() {return recipes.size();}
}
