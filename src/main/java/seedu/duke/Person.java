package seedu.duke;

import seedu.duke.exceptions.LotsException;

public class Person {

    public static final int MAX_FOOD_QUANTITY = 999;
    protected int totalMenuItems = Menu.TOTAL_MENU_ITEMS;
    protected String personName;
    protected Order[] individualFoodOrders = new Order[totalMenuItems];
    private String foodIndexOutOfBoundsErrorMessage = "Please enter the right menu number!";

    /**
     * Function to get the total number of orders for a person.
     * @return quantity of orders.
     */
    public int getTotalNumberOfOrders() {
        int counter = 0;
        for (int i = 0; i < individualFoodOrders.length; i++) {
            if (individualFoodOrders[i].getQuantity() != 0) {
                counter = counter + individualFoodOrders[i].getQuantity();
            }
        }
        return counter;
    }

    /**
     * Constructor for Person.
     * Individual Food order list will be populated with 0 on initialisation.
     *
     * @param personName Name of the person.
     * @throws LotsException if the quantity for this particular food exceeds 999.
     */
    public Person(String personName) throws LotsException {
        this.personName = personName;
        setupIndividualFoodOrders();
    }

    /**
     * Used to populate the array with 0 from index 1 to total_menu_items(10).
     *
     * @throws LotsException if the quantity for this particular food exceeds 999.
     */
    private void setupIndividualFoodOrders() throws LotsException {
        for (int i = 0; i < totalMenuItems; i++) {
            individualFoodOrders[i] = new Order(i, 0);
        }
    }

    /**
     * Add the quantity of the corresponding food index.
     *
     * @param foodIndex Index of food in the menu.
     */
    public void addFoodToIndividualFoodOrders(int foodIndex, int foodQuantity) throws LotsException {
        if (foodIndex <= totalMenuItems && foodIndex > 0) {
            int updatedQuantity = individualFoodOrders[foodIndex - 1].getQuantity() + foodQuantity;
            if (updatedQuantity > MAX_FOOD_QUANTITY) {
                throw new LotsException("Please keep the food quantity to less than 1000!");
            }
            individualFoodOrders[foodIndex - 1].setQuantity(updatedQuantity);
        } else {
            throw new LotsException(foodIndexOutOfBoundsErrorMessage);
        }
    }

    /**
     * Sets the quantity of a particular food order in the order array to 0.
     *
     * @param foodIndex Index of the order whose quantity is getting set to 0.
     * @throws LotsException When order index is more than the number of orders.
     */
    public void deleteParticularOrder(int foodIndex) throws LotsException {
        editParticularOrder(foodIndex, 0);
    }

    /**
     * Sets the quantity of a particular food to quantity requested by user.
     *
     * @param foodIndex Index of the order whose quantity is to be changed.
     * @param quantity  Quantity of food to be set.
     * @throws LotsException When foodIndex is more than number of food in menu or
     *                       order quantity is less than 0.
     */
    public void editParticularOrder(int foodIndex, int quantity) throws LotsException {
        try {
            int indexToEdit = getIndexToEdit(foodIndex);
            individualFoodOrders[indexToEdit].setQuantity(quantity);
        } catch (IndexOutOfBoundsException e) {
            throw new LotsException("Please enter a valid order index!" + System.lineSeparator()
                + "Enter \"list\" to check your order index.");
        }
    }

    /**
     * Get the correct index of the Order array to be edited.
     *
     * @param foodIndex Index of the order whose quantity is to be changed.
     * @return The index of Order array to be edited.
     */
    private int getIndexToEdit(int foodIndex) {
        int numberOfOrders = 0;
        for (int i = 0; i < totalMenuItems; i++) {
            if (individualFoodOrders[i].getQuantity() > 0) {
                numberOfOrders++;
            }
            if (foodIndex + 1 == numberOfOrders) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if the individual order of this person is empty by checking
     * if the quantity of food orders are 0.
     *
     * @return Returns true if all the quantity of the food orders are 0, else returns false.
     */
    public boolean isEmpty() {
        int numberOfOrders = 0;
        for (int i = 0; i < totalMenuItems; i++) {
            if (individualFoodOrders[i].getQuantity() > 0) {
                numberOfOrders++;
            }
        }
        if (numberOfOrders == 0) {
            return true;
        }
        return false;
    }

    public String getPersonName() {
        return personName;
    }

    public Order[] getEntireFoodOrdersOfPerson() {
        return individualFoodOrders;
    }

}