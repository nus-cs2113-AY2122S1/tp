package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Represents an Ingredient which contains
 * the ingredientName,
 * weight of Ingredient stored
 * weight of Ingredient wasted,
 * limit of the weight of Ingredient wasted and
 * expiry date of the Ingredient.
 *
 * @author renzocanare, jhsee5, Rakesh12000, Dniv-ra
 */
public class Ingredient implements Comparable<Ingredient> {

    private static final String YES_NO_REGEX = "^(y|yes|n|no)$";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final int LOOP = 0;
    private static final int EXIT = 1;
    private static final Ui UI = new Ui();
    private String ingredientName;
    private double ingredientWeight;
    private double ingredientWaste;
    private double ingredientWasteDish;
    private double limit;
    private static final int WEIGHT_SOFT_LIMIT = 10000;
    private LocalDate expiryDate;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Default constructor for instances of the Ingredient object being created.
     *
     * @param ingredientName takes in the String that will become the name of the Ingredient.
     * @param ingredientWeight takes in the double that will become the weight of the Ingredient stored.
     * @author jhsee5
     */
    public Ingredient(String ingredientName, double ingredientWeight) {
        this.ingredientName = ingredientName;
        this.ingredientWeight = ingredientWeight;
        this.ingredientWaste = 0;
        this.limit = -1;
        this.expiryDate = null;
    }

    /**
     * Loading constructor for instances of the Ingredient object being created.
     *
     * @param ingredientName         takes in the String that will become the name of the Ingredient.
     * @param ingredientWeight       takes in the double that will become the weight of the Ingredient in storage.
     * @param ingredientWaste        takes in the double that will become the weight of wastage of the Ingredient.
     * @param savedDate              takes in the String that will become the expiry date of the Ingredient
     *                               that will represent its contribution fraction to the Dish.
     * @author jhsee5
     */
    public Ingredient(String ingredientName, double ingredientWeight, double ingredientWaste, String savedDate) {
        this.ingredientName = ingredientName;
        this.ingredientWeight = ingredientWeight;
        this.ingredientWaste = ingredientWaste;
        this.limit = -1;
        if (savedDate.equals("null")) {
            this.expiryDate = null;
        } else {
            this.expiryDate = LocalDate.parse(savedDate, dtf);
        }
    }

    /**
     * Returns the name of the Ingredient.
     *
     * @return ingredientName which is the name of the Ingredient as a String.
     * @author jhsee5
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Returns the storage weight of the Ingredient.
     *
     * @return storage weight of the Ingredient.
     * @author Dniv-ra
     */
    public double getIngredientWeight() {
        return ingredientWeight;
    }

    /**
     * Sets the storage weight of the Ingredient.
     *
     * @param ingredientWeight storage weight of the Ingredient.
     * @author jhsee5
     */
    public void setIngredientWeight(double ingredientWeight) {
        this.ingredientWeight = ingredientWeight;
    }

    /**
     * Sets the wastage weight of the Dish.
     *
     * @param newWeight wastage weight of the Ingredient.
     * @author renzocanare
     */
    public void setIngredientWaste(double newWeight) {
        this.ingredientWaste = newWeight;
    }

    /**
     * Sets the wastage weight limit of the Ingredient.
     *
     * @param limit limit of the wastage weight of the Ingredient.
     * @author Dniv-ra
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }

    /**
     * Returns the wastage weight limit of the Ingredient.
     *
     * @return limit which is the limit of the wastage weight of the Ingredient.
     * @author Dniv-ra
     */
    public double getLimit() {
        return limit;
    }

    /**
     * Returns the expiry date of the Ingredient.
     *
     * @return expiry date of the Ingredient.
     * @author renzocanare
     */
    public String getExpiryDate() {
        return this.expiryDate.format(dtf);
    }

    /**
     * Sets the expiry date of the Ingredient.
     *
     * @param date expiry date of the Ingredient.
     * @author renzocanare
     */
    public void setExpiryDate(LocalDate date) {
        this.expiryDate = date;
    }

    /**
     * Sets the updated name of the Ingredient.
     *
     * @param ingredientName updated name of the Ingredient.
     * @author Rakesh12000
     */
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    /**
     * Takes in the weight of Ingredient stored from the command line interface
     * and adds to existing Ingredient Storage.
     *
     * @throws FoodoramaException when the input is not a number or when the number entered is out of bounds,
     * @author Dniv-ra
     */
    public void updateIngredientWeight() throws FoodoramaException {
        UI.printEnterStoredWeightOf(ingredientName);
        Scanner in = new Scanner(System.in);
        String inputIngredientWeight = in.nextLine();

        int loop = LOOP;
        double ingredientWeightValue;
        while (loop == LOOP) {
            String confirmAdd = "e";
            if (!isNumber(inputIngredientWeight)) {
                throw new FoodoramaException(UI.getInvalidNumberMsg());
            }
            ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
            // while ingredientWeightValue is negative
            while (ingredientWeightValue < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidIngrWeight(inputIngredientWeight);
                inputIngredientWeight = in.nextLine();
                if (!isNumber(inputIngredientWeight)) {
                    throw new FoodoramaException(UI.getInvalidNumberMsg());
                }
                ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
            }
            if (Double.isInfinite(ingredientWeightValue) || Double.isNaN(ingredientWeightValue)) {
                throw new FoodoramaException(UI.printNumericalInputInvalid("dish waste"));
            } else if (ingredientWeightValue > WEIGHT_SOFT_LIMIT) {
                UI.clearTerminalAndPrintNewPage();
                UI.printIngrValueHigh(ingredientName);
                confirmAdd = in.nextLine();

                confirmAdd = getConfirmation(confirmAdd);
                if (confirmAdd.startsWith(NO)) {
                    UI.clearTerminalAndPrintNewPage();
                    UI.printEnterWeightOf(ingredientName);
                    inputIngredientWeight = in.nextLine();
                    if (!isNumber(inputIngredientWeight)) {
                        throw new FoodoramaException(UI.getInvalidNumberMsg());
                    }
                    ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
                }
            }
            // If inputIngredientWeight is a number and ingredientWeight is zero or positive and inputWastage is less
            // than or equal to the soft limit
            if ((isNumber(inputIngredientWeight) && (ingredientWeightValue >= 0)
                    && (ingredientWeightValue <= WEIGHT_SOFT_LIMIT)) || confirmAdd.startsWith(YES)) {
                loop = EXIT;
            }
        }
        ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
        ingredientWeight += ingredientWeightValue;
        UI.printStorage(ingredientName, ingredientWeight);
    }

    /**
     * Allow setting of Limit for Ingredient Wastage based on the user input.
     *
     * @throws FoodoramaException when user types in a String instead of number for the limit
     *                            or when the user types in Infinity for the limit.
     * @author Dniv-ra
     */
    public void setLimitValue() throws FoodoramaException {
        UI.printEnterLimitFor(ingredientName);
        Scanner in = new Scanner(System.in);
        String inputLimit = in.nextLine();
        double userLimit;
        try {
            userLimit = Double.parseDouble(inputLimit);
            // while userLimit is negative
            while (userLimit < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidIngrLimitValue(ingredientName);
                inputLimit = in.nextLine();
                userLimit = Double.parseDouble(inputLimit);
            }
        } catch (NumberFormatException e) {
            throw new FoodoramaException(UI.getInvalidNumberMsg());
        }
        if (Double.isInfinite(userLimit) || Double.isNaN(userLimit)) {
            throw new FoodoramaException(UI.printNumericalInputInvalid("ingredient limit"));
        }
        limit = userLimit;
        UI.printLimitSet(ingredientName, limit);
    }

    /**
     * Takes in the weight of Ingredient wasted from the command line interface
     * and adds to existing Ingredient Waste.
     *
     * @throws FoodoramaException when the input is not a number or when the number entered is out of bounds,
     * @author Dniv-ra
     */
    public void addWaste() throws FoodoramaException {
        UI.printEnterWasteWeightOf(ingredientName);
        Scanner in = new Scanner(System.in);
        String ingredientWeight = in.nextLine();

        int loop = LOOP;
        double ingredientWeightValue;
        while (loop == LOOP) {
            String confirmAdd = "e";
            if (!isNumber(ingredientWeight)) {
                throw new FoodoramaException(UI.getInvalidNumberMsg());
            }

            ingredientWeightValue = Double.parseDouble(ingredientWeight);
            while (ingredientWeightValue < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidIngrWasteValue(ingredientName);
                ingredientWeight = in.nextLine();
                if (!isNumber(ingredientWeight)) {
                    throw new FoodoramaException(UI.getInvalidNumberMsg());
                }
                ingredientWeightValue = Double.parseDouble(ingredientWeight);
            }
            if (Double.isInfinite(ingredientWeightValue) || Double.isNaN(ingredientWeightValue)) {
                throw new FoodoramaException(UI.printNumericalInputInvalid("dish waste"));
            } else if (ingredientWeightValue > WEIGHT_SOFT_LIMIT) {
                UI.clearTerminalAndPrintNewPage();
                UI.printIngrWasteValueHigh(ingredientName);
                confirmAdd = in.nextLine();

                confirmAdd = getConfirmation(confirmAdd);
                if (confirmAdd.startsWith(NO)) {
                    UI.clearTerminalAndPrintNewPage();
                    UI.printEnterWeightOf(ingredientName);
                    ingredientWeight = in.nextLine();
                    if (!isNumber(ingredientWeight)) {
                        throw new FoodoramaException(UI.getInvalidNumberMsg());
                    }
                    ingredientWeightValue = Double.parseDouble(ingredientWeight);
                }
            }
            if ((isNumber(ingredientWeight) && (ingredientWeightValue >= 0)
                    && (ingredientWeightValue <= WEIGHT_SOFT_LIMIT)) || confirmAdd.startsWith(YES)) {
                loop = EXIT;
            }
        }

        ingredientWeightValue = Double.parseDouble(ingredientWeight);

        ingredientWaste += ingredientWeightValue;
        double totalWaste = ingredientWaste + ingredientWasteDish;
        UI.printWastage(ingredientName, totalWaste);
        if (totalWaste >= limit && limit != -1) {
            UI.printLimitExceeded(ingredientName);
        }
    }

    /**
     * Returns total Ingredient waste from Ingredients itself and from Dish Waste.
     *
     * @return total Ingredient Waste: ingredientWaste + ingredientWasteDish
     * @author Dniv-ra
     */
    public double getWastage() {
        return ingredientWaste + ingredientWasteDish;
    }

    /**
     * Modifies the printing of an Ingredient when required to print to show the user.
     *
     * @return Ingredient profile: [INGR_NAME], storage weight, wastage weight, wastage weight limit & expiry date.
     * @author Dniv-ra
     */
    @Override
    public String toString() {
        double totalWaste = ingredientWaste + ingredientWasteDish;
        String limitString;
        String expiryDateString;
        if (limit == -1) {
            limitString = "No limit has been set";
        } else {
            limitString = String.valueOf(limit);
            if (limit < totalWaste) {
                limitString = limitString + " (exceeded)";
            }
        }
        if (this.expiryDate == null) {
            expiryDateString = "No expiry date has been set";
        } else {
            long daysBetweenExpiryToday = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
            // if days between expiry and today's date is greater than zero
            if (daysBetweenExpiryToday > 0) {
                expiryDateString = expiryDate.format(dtf) + " (" + daysBetweenExpiryToday + " day(s) from today)";
            } else {
                expiryDateString = expiryDate.format(dtf)
                        + " (EXPIRED " + Math.abs(daysBetweenExpiryToday) + " day(s) ago)";
            }
        }

        return ingredientName + '\n'
                + "   Storage: " + ingredientWeight + " kg" + System.lineSeparator()
                + "   Wastage: " + totalWaste + " kg" + System.lineSeparator()
                + "   Limit: " + limitString + System.lineSeparator()
                + "   Expiry Date: " + expiryDateString;
    }

    /**
     * Allow storage into the text file ingredients.txt.
     *
     * @return output which is the text to be written to ingredients.txt to store the user data of Dishes
     * @author Dniv-ra
     */
    public String formatData() {
        String expiryDateString;
        if (expiryDate == null) {
            expiryDateString = null;
        } else {
            expiryDateString = expiryDate.format(dtf);
        }
        return ingredientName + "|" + ingredientWeight + "|" + ingredientWaste + "|" + limit + "|"
                + expiryDateString;
    }

    /**
     * Adds weight of Ingredient wasted from Dish Waste to existing Ingredient Waste.
     *
     * @param value weight of Ingredient wasted from Dish Waste
     * @author Dniv-ra
     */
    public void addDishWaste(Double value) {
        ingredientWasteDish += value;
    }

    /**
     * Calculates the height of the bar belonging to the Ingredient
     * when 'graph ingr' is called.
     *
     * @param max        is a double which is the maximum weight wasted among all Ingredients in the Ingredient List.
     * @param resolution is the number of units along height of the graph.
     * @return num which is the height of the bar that is associated to the Ingredient that
     *     compares to other Ingredients.
     * @author Dniv-ra
     */
    public double getGraphHeight(double max, int resolution) {
        double wastage = ingredientWasteDish + ingredientWaste;
        double num = resolution * wastage / max;
        return num;
    }

    /**
     * Comparator function to compare the wastage of all Ingredients in the Ingredient List
     * which the SortIngredientCommand utilises.
     *
     * @param o is the Ingredient that will be passed into the method for comparison with this Ingredient.
     * @return diff which is either 0, 1 or -1. 0 means no difference. 1 means greater than, -1 means smaller than.
     * @author Dniv-ra, jhsee5
     */
    @Override
    public int compareTo(Ingredient o) {
        double wastage = ingredientWasteDish + ingredientWaste;
        double diff = (o.getWastage() - wastage);
        // if (difference is zero or positive) and (difference is zero) return 0;
        // else if (difference is zero or positive) but (difference is not zero) return 1;
        // else return -1;
        return (diff >= 0) ? (diff == 0) ? 0 : 1 : -1;
    }

    /**
     * Checks if the parameter numberString is a number.
     *
     * @param numberString the String to check if it is a number
     * @return true if the String is a number, false if it is not a number
     * @author Rakesh12000
     */
    public static boolean isNumber(String numberString) {
        try {
            double numberInteger = Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Allow for user confirmation between 'yes', 'y' and 'no', 'n' on methods that involve Ingredients.
     *
     * @param confirmAdd is a String either 'y' / 'yes' meaning yes or 'n'/'no' meaning no
     *                   and cannot take other strings.
     * @return confirmAdd returns 'y'/'yes'/'n'/'no' which will be used in other methods for user confirmation.
     * @author Rakesh12000
     */
    public static String getConfirmation(String confirmAdd) {
        Scanner input = new Scanner(System.in);
        while (!confirmAdd.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmationSoftLimit();
            confirmAdd = input.nextLine().toLowerCase();
        }
        return confirmAdd;
    }
}
