package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;
import java.util.Scanner;

public class DishList {
    public static final String YES_NO_REGEX = "^(y|yes|n|no)$";
    public static ArrayList<Dish> dishList = new ArrayList<>();
    public static Ui UI = new Ui();
    private static final String YES = "y";
    private static final String NO = "n";

    public static void add(String dishName) {
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

    public static double getGreatestWaste() {
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

    //Returns -1 if not present, index if present
    public static int find(String dishName) {
        for (Dish dish : dishList) {
            if (dish.getDishName().equals(dishName)) {
                return dishList.indexOf(dish);
            }
        }
        return -1;
    }

    public static void list() {
        UI.printDishList(dishList);
    }

    public static void graph() {
        UI.printDishListGraph(dishList);
    }

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
            assert (dishList.size() == (listSize - 1)) : "dishList should be of size N-1";
        } else {
            UI.printDisregardMsg();
        }
    }

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

    public static void editName(int dishIndex) throws FoodoramaException {
        String dishName = dishList.get(dishIndex).getDishName();
        UI.printAskNewNameDish(dishName);

        Scanner input = new Scanner(System.in);
        String newName = input.nextLine().toLowerCase();
        while (isNumber(newName) | isDouble(newName)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidDishName();
            newName = input.nextLine().toLowerCase();
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

    public static void editWastage(int dishIndex) throws FoodoramaException {
        if (dishIndex == -1) {
            throw new FoodoramaException(UI.getDishNotExistEdit());
        } else if (dishIndex < 0 || dishIndex >= DishList.dishList.size()) {
            throw new FoodoramaException(UI.getDishIndexExceedSizeMsg());
        } else {
            String dishName = dishList.get(dishIndex).getDishName();
            UI.printAskNewWastageDish(dishName);

            Scanner input = new Scanner(System.in);
            double newWeight;

            try {
                newWeight = Double.parseDouble(input.nextLine());
                if (newWeight < 0) {
                    throw new FoodoramaException("");
                }
            } catch (NumberFormatException | FoodoramaException e) {
                throw new FoodoramaException(UI.getInvalidNumberMsg());
            }
            if (Double.isInfinite(newWeight) | Double.isNaN(newWeight)) {
                throw new FoodoramaException(UI.printNumericalInputInvalid("dish waste"));
            }
            Double dishWeight = dishList.get(dishIndex).getWastage();
            UI.clearTerminalAndPrintNewPage();
            UI.printConfirmDishWastageEditMsg(dishWeight, newWeight);
            String confirmChange = input.nextLine().toLowerCase();
            while (!confirmChange.matches(YES_NO_REGEX)) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmChange = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmChange.startsWith(YES)) {
                dishList.get(dishIndex).setDishWastage(newWeight);
                UI.printDishWastageChanged(dishName, newWeight);
            } else {
                UI.printDisregardMsg();
            }
        }
    }

    public static boolean isNumber(String numberString) {
        try {
            int numberInteger = Integer.parseInt(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String numberString) {
        try {
            Double numberDouble = Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
