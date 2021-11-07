package seedu.situs.ingredients;

import seedu.situs.exceptions.SitusException;
import seedu.situs.storage.Storage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//@@author ngoivanessa
/*
 * Represents a group of the same ingredient.
 * (Same ingredient but different expiry dates)
 *
 * E.g.
 * Carrot | Total Amount: 13.75 kg
 *      Amount Left: 1.0 kg | Expiry Date: 01/12/2021
 *      Amount Left: 2.5 kg | Expiry Date: 05/01/2022
 *      Amount Left: 10.25 kg | Expiry Date: 04/03/2022
 */
public class IngredientGroup {

    private static final String INVALID_NUMBER = "Ingredient number does not exist!";

    protected ArrayList<Ingredient> ingredientGroup;
    private String groupName; //name of ingredient
    private Double totalAmount; //sum of all amounts of the ingredient


    public IngredientGroup() {
        ingredientGroup = new ArrayList<>();
        this.totalAmount = 0.000;
    }

    /**
     * Gets the size of 1 ingredient group.
     *
     * @return the size of 1 ingredient group
     */
    public int getIngredientGroupSize() {
        return ingredientGroup.size();
    }

    /**
     * Gets the total amount of an ingredient.
     *
     * @return the total amount of an ingredient
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    //@@author AayushMathur7
    /**
     * Updates total amount of ingredient.
     * @param currentAmount previous amount of ingredient with specific expiry date
     * @param newAmount new amount of ingredient with specific expiry date
     */
    public void updateTotalAmount(double currentAmount, double newAmount) {
        this.totalAmount += newAmount;
        this.totalAmount -= currentAmount;
    }

    //@@author AayushMathur7
    /**
     * Subtracts amount from total amount.
     * @param subtractAmount amount to subtract from total amount
     */
    public void subtractFromTotalAmount(double subtractAmount) {
        this.totalAmount -= subtractAmount;
    }

    /**
     * Gets the string representation of an ingredient in the list.
     *
     * @param ingredientNumber ingredient number to get information
     * @return String representation of the ingredient
     * @throws SitusException if the ingredient number have not existed
     */
    public String getIngredientInfo(int ingredientNumber) throws SitusException {
        try {
            return ingredientGroup.get(ingredientNumber - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new SitusException(INVALID_NUMBER);
        }
    }

    /**
     * Get name of ingredient group.
     *
     * @return name of ingredient
     */
    public String getIngredientGroupName() {
        return this.groupName;
    }

    /**
     * Set name of ingredient group.
     *
     * @param listName name of ingredient
     */
    public void setIngredientGroupName(String listName) {
        groupName = listName;
    }

    /**
     * Converts ingredient group to string.
     *
     * @return string representation of ingredient group
     */
    @Override
    public String toString() {
        String printedGroup = "";

        for (int i = 1; i <= getIngredientGroupSize(); i++) {
            try {
                printedGroup += "\t\t" + (IngredientList.getInstance().findIngredientIndexInList(this.groupName) + 1)
                        + "." + i + ". " + getIngredientInfo(i);
                if (i < getIngredientGroupSize()) {
                    printedGroup += '\n';
                }
            } catch (SitusException e) {
                e.printStackTrace();
            }
        }

        return groupName + " | Total Amount: " + String.format("%.3f", totalAmount) + " kg"
                + '\n' + printedGroup;
    }

    /**
     * Adds the new ingredient to the ingredient list.
     *
     * @param ingredient The ingredient object to be added
     */
    public void add(Ingredient ingredient) {
        ingredientGroup.add(ingredient);
        this.totalAmount += ingredient.getAmount();
        ingredientGroup.sort(Comparator.comparing(Ingredient::getExpiry));
    }

    public LocalDate getIngredientExpiry(int ingredientNumber) {
        return ingredientGroup.get(ingredientNumber - 1).getExpiry();
    }

    /**
     * Removes an ingredient from the list.
     *
     * @param ingredientNumber ingredient number to remove
     * @return The removed ingredient
     * @throws SitusException if the ingredient number has not existed
     */
    public Ingredient remove(int ingredientNumber) throws SitusException {
        try {
            Ingredient removedIngredient = ingredientGroup.remove(ingredientNumber - 1);
            this.totalAmount -= removedIngredient.getAmount();
            return removedIngredient;
        } catch (IndexOutOfBoundsException e) {
            throw new SitusException(INVALID_NUMBER);
        }
    }

    /**
     * Sets the update ingredient to an indexed ingredient in the ingredient list.
     *
     * @param ingredientNumber The index of the ingredient to be updated
     * @param ingredient       The update ingredient object
     */
    public void set(int ingredientNumber, Ingredient ingredient) {
        ingredientGroup.set(ingredientNumber, ingredient);
        ingredientGroup.sort(Comparator.comparing(Ingredient::getExpiry));
    }

    /**
     * Gets the ingredient from a specific index in the ingredient list.
     *
     * @param ingredientNumber The index of the ingredient to be located
     * @return The indexed ingredient object
     * @throws IndexOutOfBoundsException The ingredient is out of bounds
     */
    public Ingredient get(int ingredientNumber) throws IndexOutOfBoundsException {
        return ingredientGroup.get(ingredientNumber - 1);
    }

    /**
     * Finds the ingredient index in the group by expiry date.
     *
     * @param expiryDate the expiration date of the ingredient
     * @return the ingredient index by expiration date, -1 if not found
     */
    public int findIngredientIndexByExpiry(LocalDate expiryDate) {
        for (int i = 0; i < ingredientGroup.size(); i++) {
            if (ingredientGroup.get(i).getExpiry().compareTo(expiryDate) == 0) {
                return i;
            }
        }
        return -1;
    }
}
