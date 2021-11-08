package expiryeliminator.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import expiryeliminator.common.LogsCenter;
import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Represents a recipe.
 */
public class Recipe {
    private String name;
    private final TreeMap<String, IngredientQuantity> ingredientQuantities = new TreeMap<>();
    private static final Logger logger = LogsCenter.getLogger(Recipe.class);

    /**
     * Initialises a recipe.
     *
     * @param name The name of the recipe
     */
    public Recipe(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the recipe.
     *
     * @return Name of the recipe.
     */
    public String getName() {
        return name;
    }

    public void setName(String inputName) {
        name = inputName;
    }

    public void setIngredientQuantities(String ingredientName, IngredientQuantity ingredientQuantity) {
        ingredientQuantities.put(ingredientName, ingredientQuantity);
    }


    /**
     * Returns the ingredients and the respective quantities of the recipe.
     *
     * @return Ingredients and the respective quantities of the recipe.
     */
    public TreeMap<String, IngredientQuantity> getIngredientQuantities() {
        return ingredientQuantities;
    }

    //@@author vincentlauhl

    /**
     * Adds an ingredient and its associated quantity to the recipe.
     *
     * @param ingredientName Name of ingredient to be added.
     * @param quantity Quantity of ingredient to be added.
     * @param ingredientRepository Ingredient repository.
     * @throws DuplicateDataException If ingredient already exists in the recipe.
     * @throws IllegalValueException If quantity is less than or equal to 0.
     */
    public String addIngredient(String ingredientName, int quantity, IngredientRepository ingredientRepository)
            throws DuplicateDataException, IllegalValueException {
        String ingredientNameIfNotInList = "";
        final IngredientStorage ingredientStorage = ingredientRepository.findWithNullReturn(ingredientName);
        if (ingredientQuantities.containsKey(ingredientName)) {
            throw new DuplicateDataException();
        }
        Ingredient ingredient;
        if (ingredientStorage == null) {
            logger.log(Level.INFO, ingredientName + " not in Ingredient Repository.");
            ingredientRepository.add(ingredientName);
            logger.log(Level.INFO, "Adding " + ingredientName + " to the Ingredient Repository.");
            ingredientNameIfNotInList = ingredientName + "\n";
            ingredient = new Ingredient(ingredientName);
        } else {
            ingredient = ingredientStorage.getIngredient();
        }
        final IngredientQuantity ingredientQuantity = new IngredientQuantity(ingredient, quantity);
        assert quantity > 0 : "Quantity for an ingredient in the recipe cannot be zero";
        logger.log(Level.INFO, String.format("Adding ingredient %1$s with quantity %2$s "
                + "into IngredientQuantities",ingredientName,quantity));
        ingredientQuantities.put(ingredientQuantity.getName(), ingredientQuantity);
        return ingredientNameIfNotInList;
    }

    /**
     * Checks if the ingredient name in the recipe is the same to prevent ingredients from being added
     * into the Ingredient Repository without the recipe being added.
     *
     * @param ingredientNames Array List of ingredientNames
     * @return empty string if no same ingredients and the ingredient name otherwise.
     */
    public String sameIngredientNames(ArrayList<String> ingredientNames) {
        List<String> testIngredientNames = new ArrayList<>(ingredientNames);
        for (int i = 0; i < ingredientNames.size(); i++) {
            String testIngredient = testIngredientNames.get(0);
            testIngredientNames.remove(0);
            if (testIngredientNames.contains(testIngredient)) {
                return testIngredient;
            }
        }
        return "";
    }

    public boolean allIngredientsAreSufficient(IngredientRepository ingredients) {
        for (IngredientQuantity i : ingredientQuantities.values()) {
            IngredientStorage ingredient = ingredients.findWithNullReturn(i.getName());
            assert ingredient != null : "Ingredient should be in the repository after the recipe is added";
            logger.log(Level.INFO,"Checking if " + ingredient.getIngredient() + " has sufficient quantity");
            if (ingredient.getQuantity() < i.getQuantity()) {
                return false;
            }
        }
        return true;
    }
    //@@author

    /**
     * Delete ingredients in a recipe.
     *
     * @param ingredientName Name of ingredient to be added.
     */
    public void delete(String ingredientName) throws IllegalValueException, NotFoundException {
        if (!contains(ingredientName)) {
            throw new NotFoundException();
        }
        if (ingredientQuantities.size() <= 1) {
            throw new IllegalValueException();
        }

        ingredientQuantities.remove(ingredientName);
    }

    /**
     * Updates the quantity of ingredients in a recipe.
     *
     * @param ingredientName Name of ingredient to be added.
     * @param quantity quantity of ingredient to be updated to.
     */
    public void update(String ingredientName, int quantity) throws NotFoundException, IllegalValueException {
        if (!contains(ingredientName)) {
            throw new NotFoundException();
        }
        IngredientQuantity ingredientQuantity = ingredientQuantities.get(ingredientName);
        ingredientQuantity.setQuantity(quantity);
    }


    /**
     * Checks if the recipe contains the given ingredient name.
     *
     * @return True if the recipe contains the given ingredient name; false otherwise.
     */
    public Boolean contains(String ingredientName) {
        return ingredientQuantities.containsKey(ingredientName);
    }

    //@@author vincentlauhl
    @Override
    public String toString() {
        assert ingredientQuantities.size() > 0 : "Recipe must contain at least one ingredient.";
        StringBuilder ingredientsWithQuantities = new StringBuilder(name + "\n");
        for (IngredientQuantity ingredientQuantity : ingredientQuantities.values()) {
            ingredientsWithQuantities.append("- ").append(ingredientQuantity).append("\n");
        }
        return ingredientsWithQuantities.toString();
    }
}
