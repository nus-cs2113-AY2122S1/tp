package seedu.duke.data;

import seedu.duke.data.item.Item;
import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.data.item.food.Food;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;

import java.time.LocalDate;

/**
 * Manages the data that is processed by the application.
 */
public class DataManager {

    private ExerciseList filteredExerciseItems;
    private ExerciseList exerciseItems;
    private FutureExerciseList futureExerciseItems;
    private FoodList filteredFoodItems;
    private FoodList foodItems;
    private ItemBank exerciseBank;
    private ItemBank foodBank;
    private Profile profile;

    /**
     * Handles the different types of data going into and out of the bot.
     * Used for storage purposes.
     *
     * @param exerciseItems       Exercises items to be initialized
     * @param futureExerciseItems Upcoming exercise items to be initialized
     * @param foodItems           Food items to be initialized
     * @param exerciseBank        Exercise bank items to be initialized
     * @param foodBank            Food bank items to be initialized
     * @param profile             Profile to be initialized
     */
    public DataManager(ExerciseList exerciseItems, FutureExerciseList futureExerciseItems, FoodList foodItems,
                       ItemBank exerciseBank, ItemBank foodBank, Profile profile) {
        this.exerciseItems = exerciseItems;
        this.futureExerciseItems = futureExerciseItems;
        this.foodItems = foodItems;
        this.exerciseBank = exerciseBank;
        this.foodBank = foodBank;
        this.profile = profile;
        loadsFutureExercisesToList();
        filterExerciseListAndFoodList();
    }


    //@@author xingjie99

    /**
     * Check whether the dates of the exercises in the future exercise list have passed.
     * If the dates have passed, move the exercises in the exercise list.
     */
    private void loadsFutureExercisesToList() {
        int index = 0;
        LocalDate today = LocalDate.now();
        while (futureExerciseItems.getSize() != 0
                && (futureExerciseItems.getItem(index).getDate().isBefore(today)
                || futureExerciseItems.getItem(index).getDate().isEqual(today))) {
            String name = futureExerciseItems.getItem(index).getName();
            int calories = futureExerciseItems.getItem(index).getCalories();
            LocalDate date = futureExerciseItems.getItem(index).getDate();
            exerciseItems.addItem(new Exercise(name, calories, date));
            futureExerciseItems.deleteItem(index);
        }
    }
    //@@author

    //====================Filtered Lists methods=========================

    /**
     * Returns the filtered Exercise List.
     *
     * @return filtered exercise list in DataManager object
     */
    public ExerciseList getFilteredExerciseItems() {
        return filteredExerciseItems;
    }

    /**
     * Returns the filtered Food List.
     *
     * @return filtered food list in DataManager object
     */
    public FoodList getFilteredFoodItems() {
        return filteredFoodItems;
    }

    /**
     * Filters exercise list and food list that are within 7 days before today.
     */
    private void filterExerciseListAndFoodList() {
        this.filteredExerciseItems = new ExerciseList();
        filterExerciseListWithPastSevenDaysRecordOnly();
        this.filteredFoodItems = new FoodList();
        filterFoodListWithPastSevenDaysRecordOnly();
    }

    /**
     * Filters food list and add food items that are within 7 days before today.
     */
    private void filterFoodListWithPastSevenDaysRecordOnly() {
        LocalDate today = LocalDate.now();
        for (int i = foodItems.getSize() - 1; i >= 0; i--) {
            Food food = (Food) foodItems.getItem(i);
            if (food.getDate().isBefore(today.minusDays(7))) {
                break;
            }
            if (isWithinPastSevenDays(food, today)) {
                filteredFoodItems.addItem(food);
            }
        }
    }

    /**
     * Checks if the item is within 7 days of today.
     *
     * @param item The item from the item list
     * @return True if the item date is not before 7 days from today, and is not after today
     */
    private boolean isWithinPastSevenDays(Item item, LocalDate today) {
        boolean isBeforeOrEqualToday = item.getDate().isEqual(today) || item.getDate().isBefore(today);
        boolean isWithinOneWeek = item.getDate().isAfter(today.minusDays(7));
        return isBeforeOrEqualToday && isWithinOneWeek;
    }

    /**
     * Filters exercise list and add exercises that are within 7 days before today.
     */
    private void filterExerciseListWithPastSevenDaysRecordOnly() {
        LocalDate today = LocalDate.now();
        for (int i = exerciseItems.getSize() - 1; i >= 0; i--) {
            Exercise exercise = (Exercise) exerciseItems.getItem(i);
            if (exercise.getDate().isBefore(today.minusDays(7))) {
                break;
            }
            if (isWithinPastSevenDays(exercise, today)) {
                filteredExerciseItems.addItem(exercise);
            }
        }
    }

    //====================ExerciseList methods=========================


    /**
     * Returns the exercise list.
     *
     * @return exercise list in DataManager object
     */
    public ExerciseList getExerciseItems() {
        return this.exerciseItems;
    }

    //====================FutureExerciseList methods===================


    /**
     * Returns the future exercise items.
     *
     * @return future exercise list in DataManager object
     */
    public FutureExerciseList getFutureExerciseItems() {
        return this.futureExerciseItems;
    }

    //========================FoodList methods=============================

    /**
     * Returns the food items.
     *
     * @return food items in DataManager object
     */
    public FoodList getFoodItems() {
        return this.foodItems;
    }

    //=====================FoodBank methods============================

    /**
     * Returns food bank items.
     *
     * @return food bank items in DataManager object
     */
    public ItemBank getFoodBank() {
        return this.foodBank;
    }

    //=====================ExerciseBank methods==========================

    /**
     * Returns exercise bank items.
     *
     * @return exercise bank items in DataManager object
     */
    public ItemBank getExerciseBank() {
        return this.exerciseBank;
    }

    //=====================Profile method================================

    /**
     * Replaces profile with data in {@code profile}.
     *
     * @param profile profile to be set
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Returns profile.
     *
     * @return profile in DataManager object
     */
    public Profile getProfile() {
        return this.profile;
    }

}
