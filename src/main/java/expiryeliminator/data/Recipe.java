package expiryeliminator.data;

import java.util.TreeMap;

import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Represents a recipe.
 */
public class Recipe {
    private final String name;
    private final TreeMap<String, IngredientQuantity> ingredientQuantities = new TreeMap<>();

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

    /**
     * Adds an ingredient and its associated quantity to the recipe.
     *
     * @param ingredientName Name of ingredient to be added.
     * @param quantity Quantity of ingredient to be added.
     * @param ingredientRepository Ingredient repository.
     * @throws NotFoundException If ingredient does not exist in the ingredient repository.
     * @throws DuplicateDataException If ingredient already exists in the recipe.
     * @throws IllegalValueException If quantity is less than or equal to 0.
     */
    public void add(String ingredientName, int quantity, IngredientRepository ingredientRepository)
            throws NotFoundException, DuplicateDataException, IllegalValueException {
        final Ingredient ingredient = ingredientRepository.find(ingredientName).getIngredient();
        final IngredientQuantity ingredientQuantity = new IngredientQuantity(ingredient, quantity);
        if (ingredientQuantities.containsKey(ingredientQuantity.getName())) {
            throw new DuplicateDataException();
        }
        ingredientQuantities.put(ingredientQuantity.getName(), ingredientQuantity);
    }

    @Override
    public String toString() {
        StringBuilder ingredientsWithQuantities = new StringBuilder(name + "\n");
        for (IngredientQuantity ingredientQuantity : ingredientQuantities.values()) {
            ingredientsWithQuantities.append("- ").append(ingredientQuantity).append("\n");
        }
        return ingredientsWithQuantities.toString();
    }

    //TODO(vincentlauhl): Allow users to modify recipe.
}
