package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles the collection of ingredient objects at runtime.
 * @author renzocanare, jhsee5, Rakesh12000, Dniv-ra
 */
public class IngredientList {
    private static final String YES_NO_REGEX = "^(y|yes|n|no)$";
    private static final Ui UI = new Ui();
    private static final String YES = "y";
    private static final String NO = "n";
    private static final int LOOP = 0;
    private static final int EXIT = 1;
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final int TEN_YEARS_IN_DAYS = 3650;
    private static final int ZERO_DAYS = 0;
    private static final int SOFT_WEIGHT_LIMIT = 10000;
    private static final int SIZE_OFFSET = 1;
    public static final String NUMBER_REGEX = "^[-\\d\\s.]+$";
    public static ArrayList<Ingredient> ingredientList = new ArrayList<>();

    /**
     * Adds a new ingredient to the ingredient list.
     * @param ingredientName name of the ingredient to be added
     * @throws FoodoramaException if the weight of the ingredient is invalid
     *
     * @author jhsee5
     */
    public static void add(String ingredientName) throws FoodoramaException {
        UI.printEnterStoredWeightOf(ingredientName);
        Scanner in = new Scanner(System.in);
        String ingredientWeight = in.nextLine();

        int loop = LOOP;
        double ingredientWeightValue;
        while (loop == LOOP) {
            String confirmAdd = "e";
            if (!isNumber(ingredientWeight) | !isDouble(ingredientWeight)) {
                throw new FoodoramaException(UI.getInvalidNumberMsg());
            }
            ingredientWeightValue = Double.parseDouble(ingredientWeight);
            while (ingredientWeightValue < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidIngrWeight(ingredientName);
                ingredientWeight = in.nextLine();
                if (!isDouble(ingredientWeight)) {
                    throw new FoodoramaException(UI.getInvalidNumberMsg());
                }
                ingredientWeightValue = Double.parseDouble(ingredientWeight);
            }
            if (Double.isInfinite(ingredientWeightValue) || Double.isNaN(ingredientWeightValue)) {
                throw new FoodoramaException(UI.printNumericalInputInvalid("dish waste"));
            } else if (ingredientWeightValue > SOFT_WEIGHT_LIMIT) {
                UI.clearTerminalAndPrintNewPage();
                UI.printIngrValueHigh(ingredientName);
                confirmAdd = in.nextLine();

                confirmAdd = getConfirmation(confirmAdd);
                if (confirmAdd.startsWith(NO)) {
                    UI.clearTerminalAndPrintNewPage();
                    UI.printEnterStoredWeightOf(ingredientName);
                    ingredientWeight = in.nextLine();
                    if (!isDouble(ingredientWeight)) {
                        throw new FoodoramaException(UI.getInvalidNumberMsg());
                    }
                    ingredientWeightValue = Double.parseDouble(ingredientWeight);
                }
            }
            if ((isNumber(ingredientWeight) && (ingredientWeightValue >= 0)
                    && (ingredientWeightValue <= SOFT_WEIGHT_LIMIT)) | confirmAdd.startsWith(YES)) {
                loop = EXIT;
            }
        }

        ingredientWeightValue = Double.parseDouble(ingredientWeight);
        Ingredient ingredientToAdd = new Ingredient(ingredientName, ingredientWeightValue);
        ingredientList.add(ingredientToAdd);
        UI.printAddedIngredient(ingredientToAdd, ingredientWeightValue);

    }

    /**
     * Checks if an ingredient exists in the ingredient list and returns it index.
     * @param ingredientName name of ingredient being searched for
     * @return -1 if not present, index if present
     *
     * @author renzocanare
     */
    public static int find(String ingredientName) {
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getIngredientName().equals(ingredientName)) {
                return ingredientList.indexOf(ingredient);
            }
        }
        return -1;
    }

    /**
     * Gets the value of the ingredient with the largest waste.
     * @return largest wastage present in the list
     *
     * @author Dniv-ra
     */
    public static double getGreatestWaste() {
        double greatest = 0;
        for (Ingredient ingr : ingredientList) {
            double currWaste = ingr.getWastage();
            if (currWaste > greatest) {
                greatest = currWaste;
            }
        }
        assert greatest != 0 : "Exception should have been thrown earlier if list is empty";
        return greatest;
    }

    /**
     * Calls the graph function for ingredients.
     *
     * @author Dniv-ra
     */
    public static void graph() {
        UI.printIngrListGraph(ingredientList);
    }

    /**
     * Calls the list function for ingredients.
     *
     * @author renzocanare
     */
    public static void list() {
        UI.printIngrList(ingredientList);
    }

    /**
     * Deletes an ingredient from the ingredient list.
     * @param ingredientIndex index of the item to be deleted
     *
     * @author Rakesh12000
     */
    public static void delete(int ingredientIndex) {
        Scanner input = new Scanner(System.in);
        int listSize = ingredientList.size(); //listSize = N
        String ingredientName = ingredientList.get(ingredientIndex).getIngredientName();
        if (ingredientIndex == -1) {
            UI.printIngrNotExistMsg();
            assert ingredientList.size() == listSize : "ingredientList should be of size N";
        } else {
            UI.printConfirmDelIngr(ingredientName);
            String confirmDel = input.nextLine().toLowerCase();
            while (!confirmDel.matches(YES_NO_REGEX)) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmDel = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmDel.startsWith(YES)) {
                //Get all dishes
                for (Dish dish : DishList.dishList) {
                    //Find if they contain ingr in parts
                    ArrayList<Ingredient> parts = dish.getParts();
                    for (int i = 0; i < parts.size(); i++) {
                        if (parts.get(i).getIngredientName().equals(ingredientName)) {
                            parts.remove(i);
                        }
                    }
                }
                ingredientList.remove(ingredientIndex);
                UI.printIngrNameRemoved(ingredientName);
                assert ingredientList.size() == (listSize - SIZE_OFFSET) : "ingredientList should be of size N-1";
            } else {
                UI.printDisregardMsg();
            }
        }
    }

    /**
     * Clears the ingredient list.
     *
     * @author Rakesh12000
     */
    public static void clearList() {
        Scanner input = new Scanner(System.in);
        UI.printConfirmClearIngr();
        String confirmClear = input.nextLine().toLowerCase();

        while (!confirmClear.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmClear.startsWith(YES)) {
            ingredientList.clear();
            assert ingredientList.size() == 0 : "ingredientList should be of size 0";
            UI.printIngrListCleared();
        } else {
            UI.printDisregardMsg();
        }
    }

    /**
     * Edits the name of one of the ingredients present in the list.
     * @param ingredientIndex index of item to be edited
     * @throws FoodoramaException if new name is blank
     *
     * @author Rakesh12000
     */
    public static void editName(int ingredientIndex) throws FoodoramaException {
        String ingrName = ingredientList.get(ingredientIndex).getIngredientName();
        UI.printAskNewNameIngr(ingrName);

        Scanner input = new Scanner(System.in);
        String newName = input.nextLine().toLowerCase();
        while (isNumber(newName) | isDouble(newName)) {
            throw new FoodoramaException(UI.getInvalidIngredientName());
        }

        if (newName.isBlank()) {
            throw new FoodoramaException(UI.getBlankName("ingredient"));
        }

        UI.clearTerminalAndPrintNewPage();
        UI.printConfirmIngrEditMsg(ingrName, newName);
        String confirmChange = input.nextLine().toLowerCase();
        while (!confirmChange.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmChange = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmChange.startsWith(YES)) {
            ingredientList.get(ingredientIndex).setIngredientName(newName);
            UI.printIngrNameChanged(ingrName, newName);
        } else {
            UI.printDisregardMsg();
        }
    }

    /**
     * Check if the given date matches the proper date formatting.
     * @param expiryDateString date string to be checked
     * @return true if follows the correct format, false otherwise
     *
     * @author renzocanare
     */
    private static boolean isValidDateFormat(String expiryDateString) {
        try {
            LocalDate.parse(expiryDateString, dtf);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the expiry date is too far away in the future
     * or in the past and prompts the user if that's the case.
     * @param daysBetweenExpiryToday time till the expiry date
     * @param ingrName name of the ingredient its getting expiry updated
     * @return false if expiry is in the past or too far away in the future, true otherwise
     *
     * @author renzocanare
     */
    public static boolean isValidExpiryLength(long daysBetweenExpiryToday, String ingrName) {
        if (daysBetweenExpiryToday > TEN_YEARS_IN_DAYS) {
            UI.printLongExpiryDateMsg();
            Scanner input = new Scanner(System.in);
            String confirmDate = input.nextLine().toLowerCase();
            while (!confirmDate.matches(YES_NO_REGEX)) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmDate = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmDate.startsWith("n")) {
                UI.printAskIngrExpiryDate(ingrName);
                return false;
            }
        } else if (daysBetweenExpiryToday < ZERO_DAYS) {
            UI.printPassedExpiryDateMsg();
            return false;
        }
        return true;
    }

    /**
     * Add expiry date for an ingredient.
     * @param ingredientIndex index of the ingredient expiry is being added to
     *
     * @author renzocanare
     */
    public static void addExpiry(int ingredientIndex) throws FoodoramaException {
        String ingrName = ingredientList.get(ingredientIndex).getIngredientName();
        UI.printAskIngrExpiryDate(ingrName);

        Scanner input = new Scanner(System.in);
        String expiryDateString = input.nextLine();
        LocalDate expiryDate = null;

        int loop = LOOP;
        long daysBetweenExpiryToday = Long.MIN_VALUE;
        while (loop == LOOP) {
            UI.clearTerminalAndPrintNewPage();
            if (!isValidDateFormat(expiryDateString)) {
                throw new FoodoramaException(UI.getIncorrectExpiryDateFormatMsg());
            } else if (isValidDateFormat(expiryDateString)) {
                expiryDate = LocalDate.parse(expiryDateString, dtf);
                daysBetweenExpiryToday = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
            }
            if (isValidDateFormat(expiryDateString) && daysBetweenExpiryToday != Long.MIN_VALUE
                    && isValidExpiryLength(daysBetweenExpiryToday, ingrName)) {
                loop = EXIT;
            } else {
                expiryDateString = input.nextLine();
            }
        }
        ingredientList.get(ingredientIndex).setExpiryDate(expiryDate);
        UI.clearTerminalAndPrintNewPage();
        UI.printSetIngrExpiryDate(ingrName, expiryDate, daysBetweenExpiryToday);
    }

    /**
     * Edits the wastage of one of the ingredients present in the list.
     * @param ingrIndex index of item to be edited
     * @throws FoodoramaException if new wastage is negative or infinity
     *
     * @author renzocanare
     */
    public static void editWastage(int ingrIndex) throws FoodoramaException {

        String ingrName = ingredientList.get(ingrIndex).getIngredientName();
        UI.printAskNewWastageDish(ingrName);

        Scanner in = new Scanner(System.in);
        String inputIngredientWeight = in.nextLine();

        int loop = LOOP;
        double ingredientWeightValue;
        while (loop == LOOP) {
            String confirmAdd = "e";
            if (!isDouble(inputIngredientWeight)) {
                throw new FoodoramaException(UI.getInvalidNumberMsg());
            }

            ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
            while (ingredientWeightValue < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidIngrWasteValue(inputIngredientWeight);
                inputIngredientWeight = in.nextLine();
                if (!isDouble(inputIngredientWeight)) {
                    throw new FoodoramaException(UI.getInvalidNumberMsg());
                }
                ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
            }

            if (Double.isInfinite(ingredientWeightValue) || Double.isNaN(ingredientWeightValue)) {
                throw new FoodoramaException(UI.printNumericalInputInvalid("ingredient waste"));
            } else if (ingredientWeightValue > SOFT_WEIGHT_LIMIT) {
                UI.clearTerminalAndPrintNewPage();
                UI.printIngrValueHigh(ingrName);
                confirmAdd = in.nextLine();

                confirmAdd = getConfirmation(confirmAdd);
                if (confirmAdd.startsWith(NO)) {
                    UI.clearTerminalAndPrintNewPage();
                    UI.printEnterWeightOf(ingrName);
                    inputIngredientWeight = in.nextLine();
                    if (!isDouble(inputIngredientWeight)) {
                        throw new FoodoramaException(UI.getInvalidNumberMsg());
                    }
                    ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
                }
            }
            if ((isDouble(inputIngredientWeight) && (ingredientWeightValue >= 0)
                    && (ingredientWeightValue <= SOFT_WEIGHT_LIMIT)) || confirmAdd.startsWith(YES)) {
                loop = EXIT;
            }
        }

        Double newWeight = Double.parseDouble(inputIngredientWeight);
        Double ingrWeight = ingredientList.get(ingrIndex).getWastage();

        UI.clearTerminalAndPrintNewPage();
        UI.printConfirmDishWastageEditMsg(ingrWeight, newWeight);
        String confirmChange = in.nextLine().toLowerCase();
        while (!confirmChange.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmChange = in.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmChange.startsWith(YES)) {
            ingredientList.get(ingrIndex).setIngredientWaste(newWeight);
            UI.printDishWastageChanged(ingrName, newWeight);
        } else {
            UI.printDisregardMsg();
        }
    }

    /**
     * Edits the storage of one of the ingredients present in the list.
     * @param ingrIndex index of item to be edited
     * @throws FoodoramaException if new storage is negative or infinity
     *
     * @author renzocanare
     */
    public static void editStorage(int ingrIndex) throws FoodoramaException {

        String ingrName = ingredientList.get(ingrIndex).getIngredientName();
        UI.printAskNewStorageIngr(ingrName);

        Scanner in = new Scanner(System.in);
        String inputIngredientWeight = in.nextLine();

        int loop = LOOP;
        double ingredientWeightValue;
        while (loop == LOOP) {
            String confirmAdd = "e";
            if (!isDouble(inputIngredientWeight)) {
                throw new FoodoramaException(UI.getInvalidNumberMsg());
            }
            ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
            while (ingredientWeightValue < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidIngrWeight(ingrName);
                inputIngredientWeight = in.nextLine();
                if (!isDouble(inputIngredientWeight)) {
                    throw new FoodoramaException(UI.getInvalidNumberMsg());
                }
                ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
            }
            if (Double.isInfinite(ingredientWeightValue) | Double.isNaN(ingredientWeightValue)) {
                throw new FoodoramaException(UI.printNumericalInputInvalid("ingredient stored"));
            } else if (ingredientWeightValue > SOFT_WEIGHT_LIMIT) {
                UI.clearTerminalAndPrintNewPage();
                UI.printIngrValueHigh(ingrName);
                confirmAdd = in.nextLine();

                confirmAdd = getConfirmation(confirmAdd);
                if (confirmAdd.startsWith(NO)) {
                    UI.clearTerminalAndPrintNewPage();
                    UI.printEnterStoredWeightOf(ingrName);
                    inputIngredientWeight = in.nextLine();
                    if (!isDouble(inputIngredientWeight)) {
                        throw new FoodoramaException(UI.getInvalidNumberMsg());
                    }
                    ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
                }
            }
            if ((isDouble(inputIngredientWeight) && (ingredientWeightValue >= 0)
                    && (ingredientWeightValue <= SOFT_WEIGHT_LIMIT)) | confirmAdd.startsWith(YES)) {
                loop = EXIT;
            }
        }

        Double newWeight = Double.parseDouble(inputIngredientWeight);
        Double ingrWeight = ingredientList.get(ingrIndex).getIngredientWeight();

        UI.clearTerminalAndPrintNewPage();
        UI.printConfirmIngrStorageEditMsg(ingrWeight, newWeight);
        String confirmChange = in.nextLine().toLowerCase();
        while (!confirmChange.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmChange = in.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmChange.startsWith(YES)) {
            ingredientList.get(ingrIndex).setIngredientWeight(newWeight);
            UI.printIngrStorageChanged(ingrName, newWeight);
        } else {
            UI.printDisregardMsg();
        }

    }

    /**
     * Checks if the parameter numberString is a number.
     *
     * @param numberString the String to check if it is a number
     * @return true if the String is a number, false if it is not a number
     * @author Dniv-ra
     */
    public static boolean isNumber(String numberString) {
        if (numberString.matches(NUMBER_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if given string can be converted into a number.
     * @param numberString string to be checked
     * @return true if string can be converted into a double, false otherwise
     *
     * @author Rakesh12000
     */
    public static boolean isDouble(String numberString) {
        try {
            double numberInteger = Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Get user confirmation through a prompt.
     * @param confirmAdd user input as a string
     * @return final user input if it is not invalid
     *
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
