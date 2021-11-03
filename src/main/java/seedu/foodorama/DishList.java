package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;
import java.util.Scanner;

public class DishList {
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

    public static void delete(String dishName) {
        Scanner input = new Scanner(System.in);

        int listSize = dishList.size(); //listSize = N
        int dishIndex = DishList.find(dishName);
        if (dishIndex == -1) {
            UI.printDishNotExistMsg();
            assert dishList.size() == listSize : "dishList should be of size N";
        } else {
            UI.printConfirmDelDish();
            String confirmDel = input.nextLine().toLowerCase();
            while (!(confirmDel.equals(YES) | confirmDel.equals(NO))) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmDel = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmDel.equals(YES)) {
                dishList.remove(dishIndex);
                UI.printDishNameRemoved(dishName);
                assert dishList.size() == (listSize - 1) : "dishList should be of size N-1";
            } else {
                UI.printDisregardMsg();
            }
        }
    }

    public static void clearList() {
        Scanner input = new Scanner(System.in);
        UI.printConfirmClearDish();
        String confirmClear = input.nextLine().toLowerCase();

        while (!(confirmClear.equals(YES) | confirmClear.equals(NO))) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmClear.equals(YES)) {
            dishList.clear();
            assert dishList.size() == 0 : "dishList should be of size 0";
            UI.printDishListCleared();
        } else {
            UI.printDisregardMsg();
        }
    }

    public static void editName(int dishIndex) throws FoodoramaException {
        if (dishIndex == -1) {
            throw new FoodoramaException(UI.getDishNotExistEdit());
        } else if (dishIndex < 0 || dishIndex >= DishList.dishList.size()) {
            throw new FoodoramaException(UI.getDishIndexExceedSizeMsg());
        } else {
            String dishName = dishList.get(dishIndex).getDishName();
            UI.printAskNewNameDish(dishName);

            Scanner input = new Scanner(System.in);
            String newName = input.nextLine();

            UI.clearTerminalAndPrintNewPage();
            UI.printConfirmDishNameEditMsg(dishName, newName);
            String confirmChange = input.nextLine().toLowerCase();
            while (!(confirmChange.equals(YES) | confirmChange.equals(NO))) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmChange = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmChange.equals(YES)) {
                dishList.get(dishIndex).setDishName(newName);
                UI.printDishNameChanged(dishName, newName);
            } else {
                UI.printDisregardMsg();
            }
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
            Double dishWeight = dishList.get(dishIndex).getWastage();

            UI.clearTerminalAndPrintNewPage();
            UI.printConfirmDishWastageEditMsg(dishWeight, newWeight);
            String confirmChange = input.nextLine().toLowerCase();
            while (!(confirmChange.equals(YES) | confirmChange.equals(NO))) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmChange = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmChange.equals(YES)) {

                dishList.get(dishIndex).setDishWastage(newWeight);
                UI.printDishWastageChanged(dishName, newWeight);
            } else {
                UI.printDisregardMsg();
            }
        }
    }
}
