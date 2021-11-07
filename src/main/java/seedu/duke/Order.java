package seedu.duke;

import seedu.duke.exceptions.LotsException;

import java.util.ArrayList;

public class Order {

    private String foodName;
    private int foodIndex;
    private int quantity;
    private double costOfOrder;
    private static ArrayList<String> menuList = Menu.FOODLIST;
    private static ArrayList<Double> priceList = Menu.PRICELIST;

    //@@author markuslyq
    /**
     * Constructor for Order.
     *
     * @param foodIndex Index of food to be added to the order.
     * @param quantity Quantity of food items to be added to the order.
     * @throws LotsException if the quantity for this particular food exceeds 999.
     */
    public Order(int foodIndex, int quantity) throws LotsException {
        this.foodIndex = foodIndex;
        this.foodName = menuList.get(foodIndex);
        this.quantity = quantity;
        this.costOfOrder = quantity * priceList.get(foodIndex);
    }

    /**
     * Function to update quantity of a particular order to a specified value.
     * @param quantityToSet Quantity of food items to be set.
     */
    public void setQuantity(int quantityToSet) {
        this.quantity = quantityToSet;
        this.costOfOrder = quantityToSet * priceList.get(foodIndex);
    }
    //@@author

    /**
     * Function to get quantity of a particular order.
     *
     * @return Returns the quantity of a particular order.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Function to get total cost of a particular order.
     *
     * @return Returns the total cost of a particular order.
     */
    public double getCost() {
        return costOfOrder;
    }

    /**
     * Function to represent the order data in a String format.
     *
     * @return Returns the data of a particular order as a String.
     */
    public String toString() {
        return foodName + " | Quantity = " + quantity + " | Cost = $" + String.format("%.2f", costOfOrder);
    }
}
