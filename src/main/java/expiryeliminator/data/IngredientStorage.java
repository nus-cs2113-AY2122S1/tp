package expiryeliminator.data;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import expiryeliminator.data.exception.IllegalValueException;

/**
 * Represents the storage of a particular ingredient.
 */
public class IngredientStorage {
    private Ingredient ingredient;
    private int quantity = 0;
    private final TreeMap<LocalDate, Integer> ingredientBatches = new TreeMap<>();

    /**
     * Initialises ingredient storage.
     *
     * @param ingredient Ingredient object.
     */
    public IngredientStorage(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    /**
     * Returns the associated ingredient object.
     *
     * @return Ingredient object.
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Set ingredient with associated input.
     *
     * @param ingredientInput ingredient to be copied to the ingredient object in the class.
     */
    public void setIngredient(Ingredient ingredientInput) {
        ingredient = ingredientInput;
    }

    /**
     * Returns the total quantity of the ingredient.
     *
     * @return Quantity of ingredient.
     */
    public int getQuantity() {
        return quantity;
    }

    public LocalDate getEarliestExpiryDate() {
        return ingredientBatches.firstKey();
    }

    /**
     * Returns the different batches of ingredients and their respective quantity and expiry date.
     *
     * @return The different batches of the ingredient
     */
    public TreeMap<LocalDate, Integer> getIngredientBatches() {
        return ingredientBatches;
    }

    /**
     * Adds a batch of ingredients to the storage.
     *
     * @param quantity Quantity of batch of ingredients to be added.
     * @param expiryDate Expiry date of batch of ingredients to be added.
     */
    public void add(int quantity, LocalDate expiryDate) {
        assert expiryDate != null : "Expiry date cannot be null";
        if (ingredientBatches.containsKey(expiryDate)) {
            ingredientBatches.replace(expiryDate, ingredientBatches.get(expiryDate) + quantity);
        } else {
            ingredientBatches.put(expiryDate, quantity);
        }
        this.quantity += quantity;
    }

    /**
     * Removes a quantity of ingredients from the storage.
     * Removes from the earliest batch of expiry date first.
     *
     * @param quantity Quantity to be removed.
     * @throws IllegalValueException If quantity to be removed exceeds the current available quantity.
     */
    public void remove(int quantity) throws IllegalValueException {
        if (this.quantity < quantity) {
            throw new IllegalValueException();
        }

        int toRemove = quantity;
        while (toRemove > 0) {
            final LocalDate expiryDate = ingredientBatches.firstKey();
            final int batchQuantity = ingredientBatches.get(expiryDate);
            if (toRemove >= batchQuantity) {
                ingredientBatches.remove(expiryDate);
                toRemove -= batchQuantity;
            } else {
                ingredientBatches.put(expiryDate, batchQuantity - toRemove);
                toRemove = 0;
            }
        }

        this.quantity -= quantity;
    }

    /**
     * Removes a batch of ingredients from the storage based on the given expiry date.
     *
     * @param expiryDate Expiry date of batch of ingredients to be removed.
     */
    public void remove(LocalDate expiryDate) {
        //assert expiryDate != null : "Expiry date cannot be null";
        final Integer quantity = ingredientBatches.remove(expiryDate);
        this.quantity -= quantity;
    }

    /**
     * Updates units if ingredient in storage.
     *
     * @param newUnit The new unit to change to.
     */
    public void updateUnits(String newUnit) {
        ingredient.setUnit(newUnit);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ingredient.getName()).append(" (qty: ").append(quantity);
        final String unitString = ingredient.getUnit() == null ? "" : " " + ingredient.getUnit();
        stringBuilder.append(unitString);
        stringBuilder.append(")\n");
        for (Map.Entry<LocalDate, Integer> entry : ingredientBatches.entrySet()) {
            stringBuilder.append("- ").append(entry.getValue()).append(unitString);
            stringBuilder.append(" (").append(entry.getKey()).append(")").append("\n");
        }
        return stringBuilder.toString();
    }
}
