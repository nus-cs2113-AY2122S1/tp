package seedu.duke.data.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * ItemList is an abstract class that contains all the common methods for food list and exercise list.
 */
public abstract class ItemList extends ItemBank {
    public static final String MESSAGE_ITEM = "%d. %s";
    public static final String ITEM_LIST_DIVIDER = "..............................................."
            + "...........................................................";
    protected static final String DATE_FORMAT = "dd MMM yyyy";
    protected static final String LS = System.lineSeparator();
    protected static final String TAB = "\t";


    //====================Override methods=========================

    /**
     * Adds an item into the item list.
     *
     * @param item The item class object to add
     */
    @Override
    public void addItem(Item item) {
        this.internalItems.add(item);
        sortList();
    }


    //====================Public methods=========================

    /**
     * Computes the sum of calorie of all items in the item list.
     *
     * @return Integer value of the sum of calorie of all the items
     */
    public int getTotalCalories() {
        int totalCalories = internalItems.stream().mapToInt(Item::getCalories).sum();
        assert totalCalories >= 0 : "Total calories cannot less than 0";
        return totalCalories;
    }

    /**
     * Computes the sum of calorie of all food items consumed in a specific date.
     *
     * @param date The date to query all the consumed food items
     * @return Integer value of the sum of calorie of all food items consumed in the given date
     */
    public int getTotalCaloriesWithDate(LocalDate date) {
        int totalCalories = internalItems.stream()
                .filter(i -> i.getDate().isEqual(date))
                .mapToInt(Item::getCalories)
                .sum();
        assert totalCalories >= 0 : "Total calories cannot less than 0";
        return totalCalories;
    }

    /**
     * Sorts the item list according to date (and) time, will be implemented in FoodList and ExerciseList.
     */
    public abstract void sortList();

    /**
     * Converts the item list of a specific date to a string for printing purpose,
     * will be implemented in FoodList and ExerciseList.
     *
     * @param date The given date to query the item list
     * @return The string of the item list of a specific date
     */
    public abstract String convertToStringBySpecificDate(LocalDate date);

    /**
     * Adds all items in the filtered list to the list, also prevents adding duplicate items.
     *
     * @param filteredItemList The other item list
     */
    public void addAll(ItemList filteredItemList) {
        LocalDate today = LocalDate.now();
        ArrayList<Item> listToRemove = (ArrayList<Item>) internalItems
                .stream()
                .filter(f -> f.getDate().isAfter(today.minusDays(8)))
                .collect(Collectors.toList());
        internalItems.removeAll(listToRemove);
        for (int i = 0; i < filteredItemList.getSize(); i++) {
            internalItems.add(filteredItemList.getItem(i));
        }
        this.sortList();
    }


    //====================Common Helper methods for Food List and Exercise List=========================

    /**
     * Gets the day of the week of the given date.
     *
     * @param currentDate The date to query the day of the week
     * @return The day of the week in string
     */
    protected String getDayOfWeek(LocalDate currentDate) {
        String day = currentDate.getDayOfWeek().toString();
        day = day.charAt(0) + day.substring(1).toLowerCase();
        return day;
    }

    /**
     * Common method used in food list and exercise list to generate the item count string.
     *
     * @param itemListInString The StringBuilder that will contain the correct output string format
     * @param size             The size of the item list
     * @param date             The date to query the item count
     * @param message          The string format to display
     */
    protected void convertItemCountToString(StringBuilder itemListInString, int size, LocalDate date, String message) {
        itemListInString
                .append(String.format(message, size, getDayOfWeek(date),
                        date.format(DateTimeFormatter.ofPattern(DATE_FORMAT))))
                .append(ItemList.LS);
    }

    /**
     * Common method used in food list and exercise list to generate the item string.
     *
     * @param itemListInString The StringBuilder that will contain the correct output string format
     * @param index            The index of the item in the list
     * @param item             The item to convert to string
     */
    protected void convertItemToString(StringBuilder itemListInString, int index, Item item) {
        itemListInString.append(ItemList.TAB).append(String.format(MESSAGE_ITEM, index, item)).append(ItemList.LS);
    }

    /**
     * Common method used in food list and exercise list to generate the total calorie string.
     *
     * @param itemListInString The StringBuilder that will contain the correct output string format
     * @param totalCalories    The sum of calorie for each item stored inside the list
     * @param message          The string format to display
     */
    protected void convertTotalCaloriesToString(StringBuilder itemListInString, int totalCalories, String message) {
        itemListInString.append(String.format(message, totalCalories)).append(ItemList.LS);
    }

    /**
     * Helper method used in deleteItem method in FoodList and ExerciseList to get the
     * actual index from the entire list of the items to delete.
     *
     * @param index       The index of the item as shown in the view f/ or view e/ command
     * @param deletedItem The item to delete
     * @return The actual index of the item in the entire item list
     */
    protected int getActualIndex(int index, Item deletedItem) {
        for (int i = 0; i < internalItems.size(); i++) {
            if (isListToQuery(deletedItem, i) && isItemToDelete(deletedItem, i, index)) {
                return i + index;
            } else if (isListToQuery(deletedItem, i)) {
                break;
            }
        }
        return -1;
    }

    /**
     * Helper method used in getActualIndex to check if the current index has the same date,
     * (and time period for food) as the user input, will be implemented in FoodList and ExerciseList.
     *
     * @param deletedItem  The item to delete
     * @param currentIndex The current index of the entire item list
     * @return True if the current index points to the item that has the same date (and time for food)
     *         as the item to delete, false otherwise
     */
    protected abstract boolean isListToQuery(Item deletedItem, int currentIndex);

    /**
     * Helper method used in getActualIndex to check if the current index points to the item to delete,
     * will be implemented in FoodList and ExerciseList accordingly.
     *
     * @param deletedItem   The item to delete
     * @param currentIndex  The current index of the entire item list
     * @param indexToDelete The index to delete as shown in view f/ or view e/ command
     * @return true if the current index points to the item to delete, false otherwise
     */
    protected abstract boolean isItemToDelete(Item deletedItem, int currentIndex, int indexToDelete);
}
