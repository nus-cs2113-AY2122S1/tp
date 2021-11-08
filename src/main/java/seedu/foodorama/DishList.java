package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the list of dishes and the methods to add, delete, edit, list, find, clear and graph the dishes
 * in the dish list.
 *
 * @author Dniv-ra, renzocanare, Rakesh12000, jhsee5
 */
public class DishList {
    private static final String YES_NO_REGEX = "^(y|yes|n|no)$";
    public static final String NUMBER_REGEX = "^[-\\d\\s.]+$";
    public static ArrayList<Dish> dishList = new ArrayList<>();
    private static final Ui UI = new Ui();
    private static final String YES = "y";
    private static final String NO = "n";
    private static final int LOOP = 0;
    private static final int EXIT = 1;
    private static final int WEIGHT_SOFT_LIMIT = 1000;
    private static final int SIZE_OFFSET = 1;

    /**
     * Adds a dish with dishName to the dish list.
     *
     * @param dishName the dish name to be added to the dish list
     * @author Dniv-ra
     */
    public static void add(String dishName) {
        // if dishName does not exist in DishList
        if (DishList.find(dishName) == -1) {
            assert DishList.find(dishName) < 0;
            Dish dishToAdd = new Dish(dishName);
            dishList.add(dishToAdd);
            UI.printAddedDish(dishToAdd.getDishName());
        } else {
            assert DishList.find(dishName) >= 0;
            UI.printDishExistsMsg();
        }
    }

    /**
     * Gets greatest wastage of all dishes in the dish list.
     *
     * @return the weight of the greatest wastage of all dishes in the dish list
     * @author Dniv-ra
     */
    public static double getGreatestWaste() {
        // initialize greatest to zero
        double greatest = 0;
        for (Dish dish : dishList) {
            double currWaste = dish.getWastage();
            if (currWaste > greatest) {
                greatest = currWaste;
            }
        }
        assert greatest != 0 : "Exception should have been thrown earlier if list is empty";
        return greatest;
    }

    /**
     * Finds the index of a dish dishName in the dish list.
     *
     * @param dishName the dish name whose index in the dish list is to be found
     * @return the index of the dish in the dish list
     * @author Dniv-ra
     */
    //Returns -1 if not present, index if present
    public static int find(String dishName) {
        for (Dish dish : dishList) {
            if (dish.getDishName().equals(dishName)) {
                return dishList.indexOf(dish);
            }
        }
        return -1;
    }

    /**
     * Prints the list of dishes in the dish list.
     *
     * @author Rakesh12000
     */
    public static void list() {
        UI.printDishList(dishList);
    }

    /**
     * Prints the graph of the wastage of each dish against each other.
     *
     * @author Dniv-ra
     */
    public static void graph() {
        UI.printDishListGraph(dishList);
    }

    /**
     * Delete a dish of dishIndex from the list of dishes.
     * Prints a confirmation message to confirm the deletion of the dish from the dish list.
     *
     * @param dishIndex the index of the dish to be deleted
     * @author Rakesh12000
     */
    public static void delete(int dishIndex) {
        Scanner input = new Scanner(System.in);

        int listSize = dishList.size(); //listSize = N
        String dishName = dishList.get(dishIndex).getDishName();
        UI.printConfirmDelDish(dishName);
        String confirmDel = input.nextLine().toLowerCase();
        while (!confirmDel.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmDel = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmDel.startsWith(YES)) {
            dishList.remove(dishIndex);
            UI.printDishNameRemoved(dishName);
            assert (dishList.size() == (listSize - SIZE_OFFSET)) : "dishList should be of size N-1";
        } else {
            UI.printDisregardMsg();
        }
    }

    /**
     * Deletes all dishes from the list of dishes.
     * Prints a confirmation message to confirm the deletion of all dishes from the dish list.
     *
     * @author Rakesh12000
     */
    public static void clearList() {
        Scanner input = new Scanner(System.in);
        UI.printConfirmClearDish();
        String confirmClear = input.nextLine().toLowerCase();

        while (!confirmClear.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmClear.startsWith(YES)) {
            dishList.clear();
            assert dishList.size() == 0 : "dishList should be of size 0";
            UI.printDishListCleared();
        } else {
            UI.printDisregardMsg();
        }
    }

    /**
     * Edits the dish name of a dish with dishIndex in the dish list.
     * Prints a confirmation message to confirm the new name of the dish to be edited.
     *
     * @param dishIndex the index of the dish to be edited
     * @throws FoodoramaException when the new name input is a number or is blank
     * @author Rakesh12000
     */
    public static void editName(int dishIndex) throws FoodoramaException {
        String dishName = dishList.get(dishIndex).getDishName();
        UI.printAskNewNameDish(dishName);

        Scanner input = new Scanner(System.in);
        String newName = input.nextLine().toLowerCase();
        while (isNumber(newName) || isDouble(newName)) {
            throw new FoodoramaException(UI.getInvalidDishName());
        }

        if (newName.isBlank()) {
            throw new FoodoramaException(UI.getBlankName("dish"));
        }

        UI.clearTerminalAndPrintNewPage();
        UI.printConfirmDishNameEditMsg(dishName, newName);
        String confirmChange = input.nextLine().toLowerCase();
        while (!confirmChange.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmChange = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmChange.startsWith(YES)) {
            dishList.get(dishIndex).setDishName(newName);
            UI.printDishNameChanged(dishName, newName);
        } else {
            UI.printDisregardMsg();
        }
    }

    /**
     * Edit the wastage weight of a dish in the dish list.
     * Prints a confirmation message when the weight of the new wastage is unusually large.
     *
     * @param dishIndex the index of the dish to be edited
     * @throws FoodoramaException when the new wastage weight input is less than zero, is not a number or is blank
     * @author renzocanare
     */
    public static void editWastage(int dishIndex) throws FoodoramaException {
        // if dishIndex cannot be found
        if (dishIndex == -1) {
            throw new FoodoramaException(UI.getDishNotExistEdit());
        // if dishIndex is out of bounds
        } else if (dishIndex < 0 || dishIndex >= DishList.dishList.size()) {
            throw new FoodoramaException(UI.getDishIndexExceedSizeMsg());
        } else {
            String dishName = dishList.get(dishIndex).getDishName();
            UI.printAskNewWastageDish(dishName);

            Scanner in = new Scanner(System.in);
            String newWeight = in.nextLine();

            int loop = LOOP;
            double inputWastage;
            while (loop == LOOP) {
                String confirmAdd = "e";
                if (!isDouble(newWeight)) {
                    throw new FoodoramaException(UI.getInvalidNumberMsg());
                }

                inputWastage = Double.parseDouble(newWeight);
                while (inputWastage < 0) {
                    UI.clearTerminalAndPrintNewPage();
                    UI.printInvalidDishWasteValue(dishName);
                    newWeight = in.nextLine();
                    if (!isDouble(newWeight)) {
                        throw new FoodoramaException(UI.getInvalidNumberMsg());
                    }
                    inputWastage = Double.parseDouble(newWeight);
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
                        newWeight = in.nextLine();
                        if (!isDouble(newWeight)) {
                            throw new FoodoramaException(UI.getInvalidNumberMsg());
                        }
                        inputWastage = Double.parseDouble(newWeight);
                    }
                }
                if ((isDouble(newWeight) && (inputWastage >= 0)
                        && (inputWastage <= WEIGHT_SOFT_LIMIT)) | confirmAdd.startsWith(YES)) {
                    loop = EXIT;
                }
            }

            inputWastage = Double.parseDouble(newWeight);
            Double dishWeight = dishList.get(dishIndex).getWastage();
            UI.clearTerminalAndPrintNewPage();
            UI.printConfirmDishWastageEditMsg(dishWeight, inputWastage);
            String confirmChange = in.nextLine().toLowerCase();
            while (!confirmChange.matches(YES_NO_REGEX)) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmChange = in.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmChange.startsWith(YES)) {
                dishList.get(dishIndex).setDishWastage(inputWastage);
                UI.printDishWastageChanged(dishName, inputWastage);
            } else {
                UI.printDisregardMsg();
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
    public static boolean isNumber(String numberString) {
        if (numberString.matches(NUMBER_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the parameter numberString is a double.
     *
     * @param numberString the String to check if it is an double
     * @return true is the String is an double, false if it is not a double
     * @author Dniv-ra
     */
    public static boolean isDouble(String numberString) {
        try {
            Double numberDouble = Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Gets the confirmation input from the user.
     *
     * @param confirmAdd the String of input from the user
     * @return the input from the user
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
