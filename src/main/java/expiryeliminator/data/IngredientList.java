package expiryeliminator.data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;

import expiryeliminator.data.exception.DuplicateDataException;

/**
 * Represents the ingredient list and contains methods to add and remove ingredients.
 */
public class IngredientList {
    private final HashMap<String, Ingredient> ingredients;

    /**
     * Constructs IngredientList object with no ingredients.
     */
    public IngredientList() {
        ingredients = new HashMap<>();
    }

    /**
     * Adds ingredient to the ingredient list.
     *
     * @param ingredient Ingredient to be added.
     * @throws DuplicateDataException If the ingredient already exists.
     */
    public void add(Ingredient ingredient) throws DuplicateDataException {
        if (contains(ingredient)) {
            throw new DuplicateDataException();
        }
        ingredients.put(ingredient.getName(), ingredient);
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients.values();
    }

    /**
     * Returns {@code true} if the ingredient list contains the specified ingredient.
     *
     * @param ingredient Ingredient whose presence in this list is to be tested.
     * @return {@code true} if the ingredient list contains the specified ingredient.
     */
    public boolean contains(Ingredient ingredient) {
        return ingredients.containsKey(ingredient.getName());
    }

    /**
     * Returns the number of ingredients in the ingredient list.
     *
     * @return The number of ingredients in the ingredient list.
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
        String wholeList = "";
        for (Ingredient ingredient : ingredients.values()) {
            wholeList = wholeList + ingredient.toString() + "\n";
        }
        return wholeList;
    }

    /**
     * Looks for the ingredient that the user is looking for.
     *
     * @param ingredientDescription The ingredient name the user is searching for.
     * @return the ingredient object that the user is searching for.
     */
    public Ingredient findIngredient(String ingredientDescription) {
        return ingredients.get(ingredientDescription);
    }

    /**
     * Looks for the ingredients that are expiring within the week.
     *
     * @return the string representing the list of expiring ingredients.
     */
    public String findExpiringIngredients() {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDatePlusAWeek = currentDate.plus(1, ChronoUnit.WEEKS);

        String expiringIngredientsList = "";

        for (Ingredient ingredient : ingredients.values()) {
            if (ingredient.getExpiryDate().isAfter(currentDate)
                    && ingredient.getExpiryDate().isBefore(currentDatePlusAWeek)) {
                expiringIngredientsList = expiringIngredientsList + ingredient.toString() + "\n";
            }
        }
        return expiringIngredientsList;
    }

    /**
     * Looks for the ingredients that have expired.
     *
     * @return the string representing the list of expired ingredients.
     */
    public String findExpiredIngredients() {
        LocalDate currentDate = LocalDate.now();

        String expiredIngredientsList = "";

        for (Ingredient ingredient : ingredients.values()) {
            if (ingredient.getExpiryDate().isBefore(currentDate)) {
                expiredIngredientsList = expiredIngredientsList + ingredient.toString() + "\n";
            }
        }
        return expiredIngredientsList;
    }


}
