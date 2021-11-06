package expiryeliminator.data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.TreeMap;

import expiryeliminator.data.exception.DuplicateDataException;
import expiryeliminator.data.exception.IllegalValueException;
import expiryeliminator.data.exception.NotFoundException;

/**
 * Represents the ingredient repository and contains methods to add and remove ingredients.
 */
public class IngredientRepository {

    private final TreeMap<String, IngredientStorage> ingredients = new TreeMap<>();

    public TreeMap<String, IngredientStorage> getIngredients() {
        return ingredients;
    }

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
     * @param unit Unit for ingredient. Can be null.
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
     * Returns the ingredient storage object corresponding to the given ingredient name. Allows null to be returned
     * to allow ingredients to be added into storage if it's not found.
     *
     * @param ingredientName Name of ingredient.
     * @return Ingredient storage object corresponding to the given ingredient name.
     */
    public IngredientStorage findWithNullReturn(String ingredientName) {
        return ingredients.get(ingredientName);
    }

    //@@author JoshHDs

    /**
     * Looks for the ingredients that are expiring within the week.
     *
     * @return the string representing the list of expiring ingredients.
     */
    public String findExpiringIngredients() {
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDatePlusAWeek = currentDate.plus(1, ChronoUnit.WEEKS);
        StringBuilder expiringIngredientsList = new StringBuilder();

        for (IngredientStorage ingredientStorage : ingredients.values()) {
            //create a storage to store expiring batches
            IngredientStorage expiredIngredientStorage = new IngredientStorage(ingredientStorage.getIngredient());
            boolean hasExpiringIngredients = false;

            for (LocalDate expiryDate : ingredientStorage.getIngredientBatches().keySet()) {
                if (expiryDate.isAfter(currentDate) && expiryDate.isBefore(currentDatePlusAWeek)) {
                    expiredIngredientStorage.add(ingredientStorage.getIngredientBatches().get(expiryDate), expiryDate);
                    hasExpiringIngredients = true;
                }
            }

            if (hasExpiringIngredients) {
                expiringIngredientsList.append(expiredIngredientStorage);
                expiringIngredientsList.append("\n");
            }

        }
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

        for (IngredientStorage ingredientStorage : ingredients.values()) {
            //create a storage to store expired batches
            IngredientStorage expiredIngredientStorage = new IngredientStorage(ingredientStorage.getIngredient());
            boolean hasExpiredIngredients = false;

            for (LocalDate expiryDate : ingredientStorage.getIngredientBatches().keySet()) {
                if (expiryDate.isBefore(currentDate)) {
                    expiredIngredientStorage.add(ingredientStorage.getIngredientBatches().get(expiryDate), expiryDate);
                    hasExpiredIngredients = true;
                }
            }

            if (hasExpiredIngredients) {
                expiredIngredientsList.append(expiredIngredientStorage);
                expiredIngredientsList.append("\n");
            }
        }
        return expiredIngredientsList.toString();
    }

    /**
     * Deletes all ingredients that have expired.
     *
     * @return a boolean that represents if there are expired ingredients within the repository
     */
    public Boolean deleteExpiredIngredients() {
        boolean haveExpired = false;
        LocalDate currentDate = LocalDate.now();

        for (IngredientStorage ingredientStorage : ingredients.values()) {
            ArrayList<LocalDate> expiryDatesToRemove = new ArrayList<>();
            for (LocalDate expiryDate : ingredientStorage.getIngredientBatches().keySet()) {
                if (expiryDate.isBefore(currentDate)) {
                    expiryDatesToRemove.add(expiryDate);
                    haveExpired = true;
                }
            }
            for (LocalDate expiryDate : expiryDatesToRemove) {
                ingredientStorage.remove(expiryDate);
            }
        }
        return haveExpired;
    }

    /**
     * Adds an ingredient and its required quantity determined by the recipe it is part of to a Treemap storing
     * the total types and quantity of ingredients in all the recipes that the user wants to generate a shopping
     * list for.
     *
     * @param ingredientName The name of the ingredient to be added to totalIngredient Treemap.
     * @param recipe The recipe that the ingredient belongs to.
     * @param totalIngredients The treemap that keeps track of the total types and quantity of each ingredient
     *         needed to make all the recipes that the user input.
     */
    public void updateShoppingListItemQuantity(String ingredientName, Recipe recipe, TreeMap<String,
            IngredientQuantity> totalIngredients) throws IllegalValueException {
        int quantity = recipe.getIngredientQuantities().get(ingredientName).getQuantity();
        String units = recipe.getIngredientQuantities().get(ingredientName).getUnit();
        if (totalIngredients.containsKey(ingredientName)) {
            try {
                int previousQuantity = totalIngredients.get(ingredientName).getQuantity();
                totalIngredients.get(ingredientName).setQuantity(quantity + previousQuantity);
            } catch (IllegalValueException e) {
                e.printStackTrace();
            }
        } else {
            Ingredient ingredientItem = new Ingredient(ingredientName, units);
            IngredientQuantity ingredientAndQuantityItem = new IngredientQuantity(ingredientItem, quantity);
            totalIngredients.put(ingredientName, ingredientAndQuantityItem);
        }
    }

    /**
     * Generates a list of ingredients and quantity to buy depending on what recipe/recipes the user wants to make.
     *
     * @param recipes The list of recipes the user wants to make.
     * @return the string representing the list ingredients and its quantities to be bought.
     */
    public String generateShoppingList(ArrayList<Recipe> recipes) throws IllegalValueException {

        StringBuilder shoppingList = new StringBuilder();
        TreeMap<String, IngredientQuantity> totalIngredients = new TreeMap<>();

        //tally total types and quantities of ingredients needed to cook the recipes
        for (Recipe recipe : recipes) {
            for (String ingredientName : recipe.getIngredientQuantities().keySet()) {
                updateShoppingListItemQuantity(ingredientName, recipe, totalIngredients);
            }
        }

        //determine what and how much of an ingredient is needed to be bought
        for (String ingredientName : totalIngredients.keySet()) {
            IngredientQuantity ingredientAndQuantityItem = totalIngredients.get(ingredientName);
            int quantityRequired = ingredientAndQuantityItem.getQuantity();
            Ingredient ingredient = ingredientAndQuantityItem.getIngredient();

            if (ingredients.containsKey(ingredientName)) {
                IngredientStorage ingredientStorage = ingredients.get(ingredientName);
                int quantityAvailable = ingredientStorage.getQuantity();
                if (quantityAvailable < quantityRequired) {
                    int quantityRequiredToBuy = quantityRequired - quantityAvailable;
                    try {
                        IngredientQuantity shoppingItem = new IngredientQuantity(ingredient, quantityRequiredToBuy);
                        shoppingList.append("\n").append(shoppingItem);
                    } catch (IllegalValueException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                shoppingList.append("\n").append(ingredientAndQuantityItem);
            }
        }
        return shoppingList.toString();
    }

}
