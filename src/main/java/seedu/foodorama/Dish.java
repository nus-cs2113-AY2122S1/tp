package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a Dish which contains
 * the dishName,
 * weight of DIsh wasted,
 * limit of the weight of Dish wasted and
 * the Ingredients that make up the Dish.
 *
 * @author renzocanare, jhsee5, Rakesh12000, Dniv-ra
 */
public class Dish implements Comparable<Dish> {
    private static final Logger LOGGER = Logger.getLogger("Dish class");
    private ArrayList<Ingredient> parts = new ArrayList<>();
    private static final Ui UI = new Ui();
    private static final String YES_NO_REGEX = "^(y|yes|n|no)$";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final int LOOP = 0;
    private static final int EXIT = 1;
    private static final int WEIGHT_SOFT_LIMIT = 10000;
    private String dishName;
    private double wastage;
    private double limit;
    //Each dish contributes a portion of its wastage to part ingredients
    private double ingredientContribution;

    /**
     * Default constructor for instances of the Dish object being created.
     *
     * @param dishName takes in the String that will become the name of the Dish.
     * @author Dniv-ra
     */
    public Dish(String dishName) {
        // Initialize ingredient fields to zero, set no limit
        LoggerManager.setupLogger(LOGGER);
        LOGGER.log(Level.INFO, "Calling default constructor");
        this.dishName = dishName;
        this.wastage = 0.0;
        this.ingredientContribution = 0.0;
        this.limit = -1;
    }

    /**
     * Loading constructor for instances of the Dish object being created.
     *
     * @param dishName               takes in the String that will become the name of the Dish.
     * @param wastage                takes in the double that will become the weight wasted of the Dish.
     * @param ingredientContribution takes in the Double of an Ingredient
     *                               that will represent its contribution fraction to the Dish.
     * @author Dniv-ra
     */
    public Dish(String dishName, double wastage, double ingredientContribution) {
        LoggerManager.setupLogger(LOGGER);
        LOGGER.log(Level.INFO, "Calling loading constructor");
        this.dishName = dishName;
        this.wastage = wastage;
        this.ingredientContribution = ingredientContribution;
        this.limit = -1;
    }

    /**
     * Returns the name of the Dish.
     *
     * @return dishName which is the name of the Dish as a String.
     * @author Dniv-ra
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * Returns the wastage weight limit of the Dish.
     *
     * @return limit which is the limit of the wastage weight of the Dish.
     * @author Dniv-ra
     */
    public double getLimit() {
        return limit;
    }

    /**
     * Returns the wastage weight of the Dish.
     *
     * @return wastage weight of the Dish.
     * @author Dniv-ra
     */
    public double getWastage() {
        return wastage;
    }

    /**
     * Sets the wastage weight limit of the Dish.
     *
     * @param limit limit which is the limit of the wastage weight of the Dish.
     * @author Dniv-ra
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }

    /**
     * Sets the name of the Dish.
     *
     * @param dishName which is the name of the Dish as a String.
     * @author Dniv-ra
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * Sets the wastage weight of the Dish.
     *
     * @param wastage wastage weight of the Dish.
     * @author renzocanare
     */
    public void setDishWastage(Double wastage) {
        this.wastage = wastage;
    }

    /**
     * Allow setting of Limit based on the user input.
     *
     * @throws FoodoramaException when user types in a String instead of number for the limit
     *                            or when the user types in Infinity for the limit.
     * @author Dniv-ra
     */
    public void setLimitValue() throws FoodoramaException {
        UI.printEnterLimitFor(dishName);
        Scanner in = new Scanner(System.in);
        String inputLimit = in.nextLine();
        double userLimit;
        try {
            userLimit = Double.parseDouble(inputLimit);
            // While limit is negative
            while (userLimit < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidDishLimitValue(dishName);
                inputLimit = in.nextLine();
                userLimit = Double.parseDouble(inputLimit);
            }
        } catch (NumberFormatException e) {
            throw new FoodoramaException(UI.getInvalidNumberMsg());
        }
        if (Double.isInfinite(userLimit) || Double.isNaN(userLimit)) {
            throw new FoodoramaException(UI.printNumericalInputInvalid("dish limit"));
        }
        limit = userLimit;
        UI.printLimitSet(dishName, limit);
    }


    /**
     * Gets the portion of the Ingredient
     * which is part of the Dish as a double.
     *
     * @return ingredientContribution which is the portion of the Ingredient which is part of the Dish as a double.
     * @author Dniv-ra
     */
    public double getIngredientContribution() {
        return ingredientContribution;
    }


    /**
     * Gets an ArrayList of Ingredients which are parts that make up the Dish.
     *
     * @return parts which is the ArrayList of Ingredients that make up the DIsh.
     * @author jhsee5
     */
    public ArrayList<Ingredient> getParts() {
        return parts;
    }

    /**
     * Adds the Ingredient [ingredientName] to the Dish as a part of the Dish.
     *
     * @param ingredientName which is the name of the Ingredient to be associated with the Dish.
     * @author Dniv-ra
     */
    public void addPart(String ingredientName) {
        int ingredientIndex = IngredientList.find(ingredientName);
        // If ingredientIndex cannot be found
        if (ingredientIndex == -1) {
            UI.printIngrNotExistMsg();
        } else {
            //Subtract the old contribution if it exists
            for (Ingredient ingredient : parts) {
                ingredient.addDishWaste(-ingredientContribution);
            }
            parts.add(IngredientList.ingredientList.get(ingredientIndex));

            UI.printAddedPartOf(ingredientName, dishName);

            //Modify the ingredient contribution to reflect the change
            ingredientContribution = wastage / parts.size();

            //Add new contribution
            for (Ingredient ingredient : parts) {
                ingredient.addDishWaste(ingredientContribution);
            }
        }
    }

    /**
     * Takes in the weight of Dish wasted from the command line interface
     * and adds to existing Dish Waste.
     *
     * @throws FoodoramaException when the input is not a number or when the number entered is out of bounds,
     * @author Dniv-ra
     */
    public void addWaste() throws FoodoramaException {
        UI.printEnterWasteWeightOf(dishName);
        Scanner in = new Scanner(System.in);
        String dishWaste = in.nextLine();

        int loop = LOOP;
        double inputWastage;
        while (loop == LOOP) {
            String confirmAdd = "e";
            if (!isNumber(dishWaste)) {
                throw new FoodoramaException(UI.getInvalidNumberMsg());
            }

            inputWastage = Double.parseDouble(dishWaste);
            // While inputWastage is negative
            while (inputWastage < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidDishWasteValue(dishName);
                dishWaste = in.nextLine();
                if (!isNumber(dishWaste)) {
                    throw new FoodoramaException(UI.getInvalidNumberMsg());
                }
                inputWastage = Double.parseDouble(dishWaste);
            }
            if (Double.isInfinite(inputWastage) || Double.isNaN(inputWastage)) {
                throw new FoodoramaException(UI.printNumericalInputInvalid("dish waste"));
            } else if (inputWastage > WEIGHT_SOFT_LIMIT) {
                UI.clearTerminalAndPrintNewPage();
                UI.printDishWasteValueHigh(dishName);
                confirmAdd = in.nextLine();

                confirmAdd = getConfirmation(confirmAdd);
                if (confirmAdd.startsWith(NO)) {
                    UI.clearTerminalAndPrintNewPage();
                    UI.printEnterWeightOf(dishName);
                    dishWaste = in.nextLine();
                    if (!isNumber(dishWaste)) {
                        throw new FoodoramaException(UI.getInvalidNumberMsg());
                    }
                    inputWastage = Double.parseDouble(dishWaste);
                }
            }
            // If dishWaste is a number and inputWastage is zero or positive and inputWastage is less than or equal to
            // the soft limit
            if ((isNumber(dishWaste) && (inputWastage >= 0) && (inputWastage <= WEIGHT_SOFT_LIMIT))
                    || confirmAdd.startsWith(YES)) {
                loop = EXIT;
            }
        }

        inputWastage = Double.parseDouble(dishWaste);

        assert inputWastage > 0 : "Adding negative waste is impossible";
        wastage += inputWastage;
        UI.printWastage(dishName, wastage);
        if (wastage >= limit && limit != -1) {
            UI.printLimitExceeded(dishName);
        }
        if (!parts.isEmpty()) {
            ingredientContribution = wastage / parts.size();
            for (Ingredient ingredient : parts) {
                // Change in contribution is change in wastage / num of parts
                ingredient.addDishWaste(inputWastage / parts.size());
            }
        }

    }

    /**
     * Checks if the parameter numberString is a number.
     *
     * @param numberString the String to check if it is a number
     * @return true is the String is a number, false if it is not a number
     * @author Dniv-ra
     */
    private boolean isNumber(String numberString) {
        try {
            double number = Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * Modifies the printing of a Dish when required to print to show the user.
     *
     * @return Dish profile: [DISH_NAME], wastage weight, Ingredients associated with the Dish & wastage weight limit.
     * @author Dniv-ra
     */
    @Override
    public String toString() {
        String limitString;
        if (limit == -1) {
            limitString = "No limit has been set";
        } else {
            limitString = String.valueOf(limit);
            if (limit < wastage) {
                limitString = limitString + " (exceeded)";
            }
        }
        String partList = "";
        if (!parts.isEmpty()) {
            for (Ingredient ingredient : parts) {
                partList = partList + "," + ingredient.getIngredientName();
            }
            partList = partList.replaceFirst(",", "");
        } else {
            LOGGER.log(Level.INFO, "No parts present for dish " + dishName);
            partList = "None";
        }
        return dishName + System.lineSeparator()
                + "   Wastage: " + wastage + " kg" + System.lineSeparator()
                + "   Ingredients Linked: " + partList + System.lineSeparator()
                + "   Limit: " + limitString;
    }

    /**
     * Calculates the height of the bar belonging to the Dish
     * when 'graph dish' is called.
     *
     * @param max        is a double which is the maximum weight wasted among all Dishes in the Dish List.
     * @param resolution is the number of units along height of the graph.
     * @return num which is the height of the bar that is associated to the Dish that compares to other Dishes.
     * @author Dniv-ra
     */
    public double getGraphHeight(double max, int resolution) {
        double num = (resolution * wastage / max);
        return num;
    }

    /**
     * Allow storage into the text file dishes.txt.
     *
     * @return output which is the text to be written to dishes.txt to store the user data of Dishes
     * @author Dniv-ra
     */
    public String formatData() {
        String output = "";
        output = output + dishName + "|" + wastage + "|" + ingredientContribution + "|" + limit;
        for (Ingredient ingredient : parts) {
            output = output + "|" + ingredient.getIngredientName();
        }
        return output;
    }

    /**
     * Comparator function to compare the wastage of all Dishes in the Dish List
     * which the SortDishCommand utilises.
     *
     * @param o is the Dish that will be passed into the method for comparison with this Dish.
     * @return diff which is either 0, 1 or -1. 0 means no difference. 1 means greater than, -1 means smaller than.
     * @author Dniv-ra, jhsee5
     */
    @Override
    public int compareTo(Dish o) {
        double diff = (o.wastage - wastage);
        // if (difference is zero or positive) and (difference is zero) return 0;
        // else if (difference is zero or positive) but (difference is not zero) return 1;
        // else return -1;
        return (diff >= 0) ? (diff == 0) ? 0 : 1 : -1;
    }

    /**
     * Allow for user confirmation between yes 'y' and no 'n' on methods that involve Dishes.
     *
     * @param confirmAdd is a String either 'y' / 'yes' meaning yes or 'n'/'no' meaning no
     *                   and cannot take other strings.
     * @return confirmAdd returns 'y'/'yes'/'n'/'no' which will be used in other methods for user confirmation.
     * @author Rakesh12000
     */
    public String getConfirmation(String confirmAdd) {
        Scanner input = new Scanner(System.in);
        while (!confirmAdd.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmationSoftLimit();
            confirmAdd = input.nextLine().toLowerCase();
        }
        return confirmAdd;
    }
}
