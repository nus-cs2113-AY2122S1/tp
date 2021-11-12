package seedu.duke;

import seedu.duke.exceptions.foodbank.EmptyFoodDescription;
import seedu.duke.exceptions.foodbank.FoodBankException;
import seedu.duke.exceptions.meal.EmptyMealListException;
import seedu.duke.exceptions.meal.MealException;
import seedu.duke.exceptions.meal.NoDeleteMealIndexException;
import seedu.duke.exceptions.meal.NoMealDetailsException;
import seedu.duke.exceptions.meal.NoSuchMealIndexException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author VishalJeyaram
/**
 * Class used to perform actions related to meals such as adding,
 * deleting and listing meals.
 */
public class Meal extends Tracker {
    protected ArrayList<String> meals;
    protected int mealNumber;
    protected int calories;
    protected String description;
    protected String date;
    protected String time;
    protected int totalCalories;
    private static Logger logger = Logger.getLogger("MealTrackerLogger");

    /**
     * Constructor for Meal class used to initialize the instance variables
     * such as the arraylist to store the meals, and two integer variables
     * to store the number of meals, and the number of calories respectively.
     */
    public Meal() {
        this.meals = new ArrayList<>();
        this.mealNumber = 0;
        this.totalCalories = 0;
        logger.setLevel(Level.SEVERE);
    }

    /**
     * Extracts the calories, description, date, and time for a particular
     * user input.
     *
     * @param inputArguments User input.
     * @throws FoodBankException if user's meal is already within the meal library.
     * @throws MealException if no meal description is in input.
     */
    public void generateMealParameters(String inputArguments) throws FoodBankException {
        this.calories = Parser.getCalories(inputArguments);
        this.description = Parser.getDescription(inputArguments);
        this.date = Parser.getDate(inputArguments);
        this.time = Parser.getTime(inputArguments);
    }

    /**
     * Adds a meal to list of meals.
     *
     * @param inputArguments User input.
     * @throws DateTimeParseException If date and/or time is not keyed in properly.
     * @throws NumberFormatException If the calories is not an integer value.
     * @throws MealException If no meal description is in the input.
     * @throws FoodBankException If user's meal is already within the meal library.
     */
    public void addMeal(String inputArguments)
            throws DateTimeParseException, NumberFormatException, MealException, FoodBankException {
        logger.entering(getClass().getName(), "addMeal");
        if (inputArguments == null) {
            throw new NoMealDetailsException();
        }
        mealNumber = meals.size();
        logger.log(Level.INFO, "generating meal parameters");
        generateMealParameters(inputArguments);
        logger.log(Level.INFO, "meal parameters generated");
        if (Parser.containsSeparators(description)) {
            throw new EmptyFoodDescription();
        }
        String input = combineMealParameters();
        meals.add(input);
        mealNumber += 1;
        ClickfitMessages.printAddedMealMessage(description,date,time,calories);
        logger.log(Level.INFO, "meal has been added to meal list");
        logger.exiting(getClass().getName(), "addMeal");
    }

    /**
     * Combines multiple parameters into single input argument to be added to meal arraylist.
     *
     * @return inputArguments.
     */
    public String combineMealParameters() {
        String inputArguments;
        inputArguments = description + Parser.CALORIE_SEPARATOR + calories
                + Parser.DATE_SEPARATOR + date + Parser.TIME_SEPARATOR + time;
        return inputArguments;
    }

    /**
     * Deletes a meal from the list of meals.
     *
     * @param inputArguments User input.
     * @throws NumberFormatException If the meal index is not an integer value.
     * @throws MealException If meal list is empty or if meal index keyed in by user does not exist.
     * @throws FoodBankException If user's meal is already within the meal library
     */
    public void deleteMeal(String inputArguments)
            throws NumberFormatException, FoodBankException, MealException {
        logger.entering(getClass().getName(), "deleteMeal");
        if (inputArguments == null) {
            throw new NoDeleteMealIndexException();
        }
        mealNumber = meals.size();
        if (meals.size() == 0) {
            throw new EmptyMealListException();
        }
        int mealIndex = Parser.parseStringToInteger(inputArguments) - 1;
        if ((mealIndex < 0) || (mealIndex > (mealNumber - 1))) {
            throw new NoSuchMealIndexException();
        }
        logger.log(Level.INFO, "generating meal parameters");
        generateMealParameters(meals.get(mealIndex));
        logger.log(Level.INFO, "meal parameters generated");
        meals.remove(mealIndex);
        mealNumber -= 1;
        logger.log(Level.INFO, "meal has been deleted from meal list");
        ClickfitMessages.printDeletedMealMessage(description, date);
        logger.exiting(getClass().getName(), "deleteMeal");
    }

    /**
     * Lists meals from the list of meals depending on the date.
     *
     * @param userDate Date on which to list meals.
     * @throws MealException If meal list is empty or if meal index keyed in by user does not exist.
     * @throws FoodBankException If user's food is already within the meal library
     */
    public void listMeals(String userDate) throws FoodBankException, MealException {
        if (meals.size() == 0) {
            ClickfitMessages.printEmptyMealList();
        }
        mealNumber = 0;
        totalCalories = 0;
        if (userDate.equals(Keywords.ALL)) {
            listAllMeals();
        } else {
            listMealsByDate(userDate);
        }
        ClickfitMessages.printMealListTotals(mealNumber, totalCalories);
        logger.log(Level.INFO, "meal list printed");
        logger.exiting(getClass().getName(), "listMeal");
    }

    /**
     * Lists all meals from the list of meals on a specific date.
     *
     * @param userDate Date on which to list meals.
     * @throws MealException If meal list is empty or if meal index keyed in by user does not exist.
     * @throws FoodBankException If user's food is already within the meal library
     */
    private void listMealsByDate(String userDate) throws FoodBankException, MealException {
        logger.entering(getClass().getName(), "listMeals");
        int i = 1;
        logger.log(Level.INFO, "entering for loop");
        for (String meal : meals) {
            if (meal.contains(userDate)) {
                logger.log(Level.INFO, "generating meal parameters");
                generateMealParameters(meal);
                logger.log(Level.INFO, "meal parameters generated");
                ClickfitMessages.printSingleMealDetails(i,description,calories,date,time);
                i += 1;
                totalCalories += calories;
                mealNumber += 1;
            }
        }
    }

    /**
     * Lists all meals from the list of meals on all days.
     *
     * @throws MealException If meal list is empty or if meal index keyed in by user does not exist.
     * @throws FoodBankException If user's food is already within the meal library
     */
    private void listAllMeals() throws FoodBankException, MealException {
        int i = 1;
        for (String meal : meals) {
            logger.log(Level.INFO, "generating meal parameters");
            generateMealParameters(meal);
            logger.log(Level.INFO, "meal parameters generated");
            ClickfitMessages.printSingleMealDetails(i,description,calories,date,time);
            i += 1;
            totalCalories += calories;
            mealNumber += 1;
        }
    }

    /**
     * Returns total calories from meals consumed from the list of meals depending on the date.
     *
     * @param date Date on which to list meals.
     * @return Lateral location.
     * @throws MealException If meal list is empty or if meal index keyed in by user does not exist.
     * @throws FoodBankException If user's food is already within the meal library
     */
    public int getCalories(String date) throws FoodBankException, MealException {
        int calorieTotal = 0;
        for (String meal : meals) {
            if (meal.contains(date)) {
                generateMealParameters(meal);
                calorieTotal += calories;
            }
        }
        return calorieTotal;
    }
}

