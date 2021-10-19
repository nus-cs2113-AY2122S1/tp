package expiryeliminator.data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.TreeMap;

import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Represents the ingredient repository and contains methods to add and remove ingredients.
 */
public class IngredientRepository {
    private final TreeMap<String, IngredientStorage> ingredients = new TreeMap<>();

    /**
     * Adds an ingredient to the repository without any associated unit or quantity.
     *
     * @param ingredientName Name of ingredient to be added.
     * @return Ingredient storage object corresponding to the added ingredient.
     * @throws DuplicateDataException If ingredient already exists in the repository.
     */
    public IngredientStorage add(String ingredientName) throws DuplicateDataException {
        return add(ingredientName, null);
    }

    /**
     * Adds an ingredient to the repository with an associated unit, but without any associated quantity.
     *
     * @param ingredientName Name of ingredient to be added.
     * @param unit Unit for ingredient.
     * @return Ingredient storage object corresponding to the added ingredient.
     * @throws DuplicateDataException If ingredient already exists in the repository.
     */
    public IngredientStorage add(String ingredientName, String unit) throws DuplicateDataException {
        assert ingredientName != null : "Ingredient name cannot be null";
        checkDuplicateIngredient(ingredientName);
        final Ingredient ingredient = new Ingredient(ingredientName, unit);
        final IngredientStorage ingredientStorage = new IngredientStorage(ingredient);
        ingredients.put(ingredient.getName(), ingredientStorage);
        return ingredientStorage;
    }

    /**
     * Adds an ingredient to the repository with an associated unit, quantity and expiry date.
     *
     * @param ingredientName Name of ingredient to be added.
     * @param unit Unit for ingredient.
     * @param quantity Quantity of ingredient.
     * @param expiryDate Expiry date of ingredient.
     * @return Ingredient storage object corresponding to the added ingredient.
     * @throws DuplicateDataException If ingredient already exists in the repository.
     */
    public IngredientStorage add(String ingredientName, String unit, int quantity, LocalDate expiryDate)
            throws DuplicateDataException {
        assert ingredientName != null : "Ingredient name cannot be null";
        assert expiryDate != null : "Expiry date cannot be null";
        checkDuplicateIngredient(ingredientName);
        final Ingredient ingredient = new Ingredient(ingredientName, unit);
        final IngredientStorage ingredientStorage = new IngredientStorage(ingredient);
        ingredientStorage.add(quantity, expiryDate);
        ingredients.put(ingredient.getName(), ingredientStorage);
        return ingredientStorage;
    }

    /**
     * Checks if the ingredient already exists in the repository.
     *
     * @param ingredientName Name of ingredient.
     * @throws DuplicateDataException If ingredient already exists in the repository.
     */
    private void checkDuplicateIngredient(String ingredientName) throws DuplicateDataException {
        if (ingredients.containsKey(ingredientName)) {
            throw new DuplicateDataException();
        }
    }

    /**
     * Removes a batch of ingredients based on the given expiry date.
     *
     * @param ingredientName Name of ingredient.
     * @param expiryDate Expiry date of batch of ingredients to be removed.
     * @throws NotFoundException If ingredient does not exist in the repository.
     * @throws IllegalValueException If the ingredient does not have any batch with the given expiry date.
     */
    public void remove(String ingredientName, LocalDate expiryDate) throws NotFoundException, IllegalValueException {
        final IngredientStorage ingredientStorage = find(ingredientName);
        ingredientStorage.remove(expiryDate);
    }

    /**
     * Removes ingredient from the ingredient repository.
     *
     * @param ingredientName Name of ingredient to be removed.
     * @return Ingredient that was removed.
     */
    public IngredientStorage remove(String ingredientName) throws NotFoundException {
        final IngredientStorage ingredientStorage = ingredients.remove(ingredientName);
        if (ingredientStorage == null) {
            throw new NotFoundException();
        }
        return ingredientStorage;
    }

    /**
     * Returns the number of ingredients in the ingredient repository.
     *
     * @return The number of ingredients in the ingredient repository.
     */
    public int size() {
        return ingredients.size();
    }

    /**
     * Returns a string representation of the whole list.
     *
     * @return returns a string representing all the ingredients and their specific quantities and expiration dates.
     */
    public String printWholeList() {
        StringBuilder wholeList = new StringBuilder();
        for (IngredientStorage ingredientStorage : ingredients.values()) {
            wholeList.append("\n").append(ingredientStorage);
        }
        return wholeList.toString();
    }

    /**
     * Returns the ingredient storage object corresponding to the given ingredient name.
     *
     * @param ingredientName Name of ingredient.
     * @return Ingredient storage object corresponding to the given ingredient name.
     * @throws NotFoundException If ingredient does not exist in the repository.
     */
    public IngredientStorage find(String ingredientName) throws NotFoundException {
        final IngredientStorage ingredientStorage = ingredients.get(ingredientName);
        if (ingredientStorage == null) {
            throw new NotFoundException();
        }
        return ingredientStorage;
    }

    /**
     * Looks for the ingredients that are expiring within the week.
     *
     * @return the string representing the list of expiring ingredients.
     */
    public String findExpiringIngredients() {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDatePlusAWeek = currentDate.plus(1, ChronoUnit.WEEKS);

        StringBuilder expiringIngredientsList = new StringBuilder();

        // TODO: Need to re-implement.

        // for (IngredientStorage ingredientStorage : ingredients.values()) {
        //     if (ingredient.getExpiryDate().isAfter(currentDate)
        //             && ingredient.getExpiryDate().isBefore(currentDatePlusAWeek)) {
        //         expiringIngredientsList.append(ingredient).append("\n");
        //     }
        // }
        return expiringIngredientsList.toString();
    }

    /**
     * Looks for the ingredients that have expired.
     *
     * @return the string representing the list of expired ingredients.
     */
    public String findExpiredIngredients() {
        LocalDate currentDate = LocalDate.now();

        StringBuilder expiredIngredientsList = new StringBuilder();

        // TODO: Need to re-implement.

        // for (IngredientStorage ingredientStorage : ingredients.values()) {
        //     if (ingredient.getExpiryDate().isBefore(currentDate)) {
        //         expiredIngredientsList.append(ingredient).append("\n");
        //     }
        // }
        return expiredIngredientsList.toString();
    }
}
