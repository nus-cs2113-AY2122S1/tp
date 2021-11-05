package seedu.situs.ingredients;

import seedu.situs.Situs;
import seedu.situs.command.DeleteCommand;
import seedu.situs.exceptions.SitusException;
import seedu.situs.storage.Storage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * Represents a list of multiple ingredients (multiple ingredient groups).
 *
 * E.g. (2 ingredient groups)
 * 1. Carrot | Total Amount: 3.5 kg
 *      Amount Left: 1.0 kg | Expiry Date: 04/11/2021
 *      Amount Left: 2.5 kg | Expiry Date: 10/12/2021
 *
 * 2. Radish | Total Amount: 1.0 kg
 *      Amount Left: 1.0 kg | Expiry Date: 18/02/2022
 */

public class IngredientList {

    private static final String INVALID_NUMBER = "Ingredient number does not exist!";
    private static final String NEGATIVE_NUMBER = "Amount updated must be positive!";
    private static final String INVALID_SUBTRACT = "Subtract amount can't be more than total amount!";
    private static final String INGREDIENT_NOT_FOUND = "Ingredient not found!";

    protected static ArrayList<IngredientGroup> ingredientList;
    private static IngredientList instance = null;
    private static Storage storage;

    //@@author datn02
    public IngredientList() throws SitusException {
        try {
            storage = new Storage();
            ingredientList = storage.loadIngredientsFromMemory();
        } catch (IOException e) {
            ingredientList = new ArrayList<>();
            throw new SitusException("Cannot open the memory file!");
        }
    }

    /**
     * Gets instance of ingredient list.
     *
     * @return ingredient list
     * @throws SitusException if data file could not be accessed
     */
    public static IngredientList getInstance() throws SitusException {
        if (instance == null) {
            instance = new IngredientList();
        }
        return instance;
    }

    /**
     * Gets size of ingredient list.
     *
     * @return size
     */
    public int getSize() {
        return ingredientList.size();
    }

    /**
     * Gets ingredient list.
     *
     * @return ingredient list
     */
    public static ArrayList<IngredientGroup> getIngredientList() {
        return ingredientList;
    }

    /**
     * Uses ingredient name as key to search if ingredient currently exists in ingredient list.
     *
     * @param ingredientName ingredient to be searched
     * @return true if ingredient already exists, false if otherwise
     */
    public boolean isIngredientInList(String ingredientName) {
        for (int i = 0; i < getSize(); i++) {
            if (ingredientList.get(i).getIngredientGroupName().equals(ingredientName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Uses ingredient name as key to find the index of the corresponding ingredient.
     * in the ingredient list.
     *
     * @param ingredientName name of ingredient
     * @return index of ingredient, -1 if not found
     */
    public int findIngredientIndexInList(String ingredientName) {
        for (int i = 0; i < getSize(); i++) {
            IngredientGroup currentIngredient = ingredientList.get(i);
            if (ingredientName.equals(currentIngredient.getIngredientGroupName())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds ingredient to ingredient list.
     *
     * @param ingredient ingredient to be added
     * @throws IOException if memory could not be written to
     * @throws IndexOutOfBoundsException if an error occurred when adding ingredient
     */
    public void add(Ingredient ingredient) throws IOException, IndexOutOfBoundsException {
        String ingredientName = ingredient.getName();
        boolean isRepeatedName = isIngredientInList(ingredientName);

        if (isRepeatedName) { //ingredient already exists, add to current ingredient group
            int ingredientIndex = findIngredientIndexInList(ingredientName);
            ingredientList.get(ingredientIndex).add(ingredient);

        } else { //create new ingredient group
            IngredientGroup newGroup = new IngredientGroup();
            ingredientList.add(newGroup);
            newGroup.setIngredientGroupName(ingredientName);
            newGroup.add(ingredient);
        }
        storage.writeIngredientsToMemory(ingredientList);
    }

    //@@author AayushMathur7
    /**
     * Subtracts amount from total ingredient amount.
     * @param groupNumber group number of ingredient
     * @param subtractAmount amount to be subtracted from total amount
     * @return name of the subtracted ingredient
     * @throws SitusException if the ingredient and/or expiry date are not matched
     * @throws IOException if the removed ingredient cannot be removed from memory
     */
    public String subtractIngredientFromGroup(int groupNumber, Double subtractAmount) throws
            SitusException, IOException, IndexOutOfBoundsException {

        int i = 0;
        IngredientGroup currentGroup = getIngredientGroup(groupNumber);
        String subtractedIngredientName = currentGroup.getIngredientGroupName();


        if (BigDecimal.valueOf(currentGroup.getTotalAmount()).compareTo(BigDecimal.valueOf(subtractAmount)) < 0) {
            throw new SitusException(INVALID_SUBTRACT);
        }


        if (Math.abs(currentGroup.getTotalAmount() - subtractAmount) < 0.001) {
            ingredientList.remove(groupNumber - 1);
            storage.writeIngredientsToMemory(ingredientList);
            return subtractedIngredientName;
        }

        currentGroup.subtractFromTotalAmount(subtractAmount);

        while (subtractAmount != 0.0) {
            if (subtractAmount <= currentGroup.get(i + 1).getAmount()) {
                currentGroup.get(i + 1).setAmount(currentGroup.get(i + 1).getAmount() - subtractAmount);
                subtractAmount = 0.0;
            } else {
                subtractAmount -= currentGroup.get(i + 1).getAmount();
                currentGroup.get(i + 1).setAmount(0.0);
            }
            i++;
        }

        i = 0;
        // remove ingredients in group where amount is approx. 0
        while (i < currentGroup.getIngredientGroupSize()) {
            if (BigDecimal.valueOf(currentGroup.get(i + 1).getAmount()).compareTo(new BigDecimal("0.001")) < 0) {
                currentGroup.remove(i + 1);
            } else {
                i++;
            }
        }

        storage.writeIngredientsToMemory(ingredientList);
        return subtractedIngredientName;
    }

    //@@author datn02
    /**
     * Removes an ingredient from ingredient group.
     *
     * @param groupNumber the group number of the ingredient to remove
     * @param ingredientNumber the number of the ingredient within group to remove
     * @return an ingredient object of the removed ingredient
     * @throws SitusException if the ingredient and/or expiry date are not matched
     * @throws IOException if the removed ingredient cannot be removed from memory
     */
    public Ingredient removeIngredientFromGroup(int groupNumber, int ingredientNumber)
            throws IndexOutOfBoundsException, SitusException, IOException {
        Ingredient removedIngredient;

        removedIngredient = getIngredientGroup(groupNumber)
                .remove(ingredientNumber);

        if (getIngredientGroup(groupNumber).getIngredientGroupSize() < 0.001) {
            ingredientList.remove(groupNumber - 1);
        }

        storage.writeIngredientsToMemory(ingredientList);

        return removedIngredient;
    }

    /**
     * Get ingredient group based on ingredient number (i.e. all duplicates of the same ingredient).
     *
     * @param ingredientNumber ingredient number
     * @return ingredient group
     * @throws SitusException index out of bounds, cannot access
     */
    public IngredientGroup getIngredientGroup(int ingredientNumber) throws IndexOutOfBoundsException {
        return ingredientList.get(ingredientNumber - 1);
    }

    //@@author AayushMathur7
    /**
     * Get ingredient group based on ingredient name (i.e. all duplicates of the same ingredient).
     *
     * @param groupNumber group number of ingredient to be updated
     * @param ingredientNumber number of ingredient within group to be updated
     * @param newAmount the new amount of the ingredient to be updated
     * @return the updated ingredient
     * @throws SitusException index out of bounds, cannot access
     * @throws IOException if the ingredient list could not be saved to memory
     */
    public Ingredient update(int groupNumber, int ingredientNumber, double newAmount)
            throws IndexOutOfBoundsException, IOException {
        IngredientGroup updatedGroup = getIngredientGroup(groupNumber);
        Ingredient updatedIngredient = updatedGroup.get(ingredientNumber);
        updatedGroup.updateTotalAmount(updatedIngredient.getAmount(), newAmount);
        updatedIngredient.setAmount(newAmount);
        storage.writeIngredientsToMemory(ingredientList);
        return updatedIngredient;
    }

}
