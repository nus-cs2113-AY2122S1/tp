package seedu.duke;

import seedu.duke.exceptions.foodbank.DuplicateFood;
import seedu.duke.exceptions.foodbank.EmptyFluidBankException;
import seedu.duke.exceptions.foodbank.EmptyFoodDescription;
import seedu.duke.exceptions.foodbank.EmptyLibraryDescription;
import seedu.duke.exceptions.foodbank.EmptyMealBankException;
import seedu.duke.exceptions.foodbank.FoodBankException;
import seedu.duke.exceptions.foodbank.InvalidFluidIndexException;
import seedu.duke.exceptions.foodbank.InvalidMealIndexException;
import seedu.duke.exceptions.foodbank.NoFoodFoundException;
import seedu.duke.exceptions.foodbank.NoFoodIndexException;

import java.util.ArrayList;

public class FoodBank {

    protected static ArrayList<String> meals;
    protected static ArrayList<String> fluids;
    protected static int calories;
    protected static String description;
    protected static int totalMeals;
    protected static int totalFluids;

    //@@author VishalJeyaram
    /**
     * Constructor for FoodBank class used to initialize the instance variables
     * such as the arraylist to store the library meals, the arraylist to store
     * the library fluids, two integer variables to store the number of library
     * meals and the number of library fluids respectively.
     */
    public FoodBank() {
        meals = new ArrayList<>();
        fluids = new ArrayList<>();
        totalMeals = 0;
        totalFluids = 0;
    }

    //@@author pragyan01
    /**
     * This method splits user input to extract parameters such as description and calories.
     *
     * @param inputArguments user input provided
     * @throws FoodBankException if calories are not provided
     *
     * @author pragyan01
     */
    public static void generateParameters(String inputArguments) throws FoodBankException {
        calories = Parser.getCalories(inputArguments);
        description = Parser.getDescription(inputArguments);
    }

    //@@author pragyan01
    /**
     * This method adds a fluid entry to the fluid library.
     *
     * @param inputArguments user input provided
     * @throws FoodBankException if user input is null
     *
     * @author pragyan01
     */
    public static void addCustomFluid(String inputArguments) throws FoodBankException {
        totalFluids = fluids.size();
        if (inputArguments == null) {
            throw new EmptyLibraryDescription();
        }
        try {
            generateParameters(inputArguments);
        } catch (EmptyFoodDescription | NoFoodFoundException e) {
            throw new EmptyLibraryDescription();
        }
        inputArguments = description + " /c " + calories;
        for (String f : fluids) {
            if (f.contains(description)) {
                throw new DuplicateFood();
            }
        }
        fluids.add(inputArguments);
        totalFluids += 1;
        System.out.println(description + ", which has " + calories
                + " calories, will be added to your library of fluids."
                + " You now have " + totalFluids + " fluids!\n");
    }

    //@@author pragyan01
    /**
     * This method deletes a fluid entry from the fluid library.
     *
     * @param inputArguments user input provided
     * @throws FoodBankException if the fluid entry index is invalid or not provided
     *
     * @author pragyan01
     */
    public static void deleteCustomFluids(String inputArguments) throws FoodBankException {
        totalFluids = fluids.size();
        if (inputArguments == null) {
            throw new NoFoodIndexException();
        }
        if (fluids.size() == 0) {
            throw new EmptyFluidBankException();
        }
        int fluidIndex = Parser.parseStringToInteger(inputArguments) - 1;
        if ((fluidIndex < 0) || (fluidIndex > (fluids.size() - 1))) {
            throw new InvalidFluidIndexException();
        }
        generateParameters(fluids.get(fluidIndex));
        fluids.remove(fluidIndex);
        totalFluids -= 1;
        System.out.println(description + " will be removed from your list of fluids consumed."
                + " You now have " + totalFluids + " fluids left!\n");
    }

    //@@author pragyan01
    /**
     * This method lists out all fluid entries stored in the food library.
     *
     *@throws FoodBankException if calories for a fluid entry is not found
     *
     * @author pragyan01
     */
    public static void listCustomFluids() throws FoodBankException {
        if (fluids.size() == 0) {
            System.out.println(ClickfitMessages.EMPTY_FLUID_LIBRARY);
        }
        int i = 1;
        for (String fluid : fluids) {
            generateParameters(fluid);
            System.out.println(i + ". " + description);
            System.out.println("Calories: " + calories + "\n");
            i += 1;
        }
        System.out.println("Total number of fluids in library: " + totalFluids);
    }

    //@@author VishalJeyaram
    /**
     * Lists out all the library meals.
     *
     * @throws FoodBankException if user's food is already within the meal library.
     */
    public static void listCustomMeal() throws FoodBankException {
        if (meals.size() == 0) {
            System.out.println("Your meals library is empty!");
        }
        int i = 1;
        for (String meal : meals) {
            generateParameters(meal);
            ClickfitMessages.printSingleLibraryMeal(i,description,calories);
            i += 1;
        }
        ClickfitMessages.printMealLibraryTotals(totalMeals);
    }

    //@@author VishalJeyaram

    /**
     * Adds a custom meal with its associated calories to the meal library.
     *
     * @throws FoodBankException If the meal description is empty, or if the meal already exists
     *                           within the library.
     */
    public static void addCustomMeal(String inputArguments) throws FoodBankException {
        totalMeals = meals.size();
        if (inputArguments == null) {
            throw new EmptyLibraryDescription();
        }
        try {
            generateParameters(inputArguments);
        } catch (EmptyFoodDescription | NoFoodFoundException e) {
            throw new EmptyLibraryDescription();
        }
        for (String m : meals) {
            if (m.contains(description)) {
                throw new DuplicateFood();
            }
        }
        inputArguments = description + Parser.CALORIE_SEPARATOR + calories;
        meals.add(inputArguments);
        totalMeals += 1;
        ClickfitMessages.printAddedLibraryMeal(description,calories,totalMeals);
    }

    //@@author VishalJeyaram

    /**
     * Deletes a custom meal with its associated calories from the meal library.
     *
     * @throws NumberFormatException If the meal index is not an integer value.
     * @throws FoodBankException     If meal index is not keyed in, or if the meal library is empty,
     *                               or if the meal index is out of range
     */
    public static void deleteCustomMeal(String inputArguments) throws NumberFormatException, FoodBankException {
        totalMeals = meals.size();
        if (inputArguments == null) {
            throw new NoFoodIndexException();
        }
        if (meals.size() == 0) {
            throw new EmptyMealBankException();
        }
        int mealIndex = Parser.parseStringToInteger(inputArguments) - 1;
        if ((mealIndex < 0) || (mealIndex > (meals.size() - 1))) {
            throw new InvalidMealIndexException();
        }
        generateParameters(meals.get(mealIndex));
        meals.remove(mealIndex);
        totalMeals -= 1;
        ClickfitMessages.printDeletedLibraryMeal(description,totalMeals);
    }

    //@@author pragyan01
    /**
     * This method finds calories associated with a specific food entry.
     * @return calories associated with the specific food entry
     * @throws FoodBankException if specified food entry
     */
    public static int findCalories(String name) throws FoodBankException {
        for (String meal : meals) {
            generateParameters(meal);
            if (description.equals(name)) {
                return calories;
            }
        }
        for (String fluid : fluids) {
            generateParameters(fluid);
            if (description.equals(name)) {
                return calories;
            }
        }
        throw new NoFoodFoundException();
    }

    //@@author pragyan01
    /**
     * This method checks if specified food exists in food bank.
     *
     * @param name food name specified by user
     * @return true if food specified is found in food bank, false otherwise.
     * @throws FoodBankException if parameters cannot be generated for food entries stored in food bank.
     */
    public static boolean isFoodFound(String name) throws FoodBankException {
        for (String meal : meals) {
            generateParameters(meal);
            if (description.equals(name)) {
                return true;
            }
        }
        for (String fluid : fluids) {
            generateParameters(fluid);
            if (description.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
