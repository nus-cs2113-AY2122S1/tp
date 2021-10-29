package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;
import java.util.Scanner;

public class DishList {
    public static ArrayList<Dish> dishList = new ArrayList<>();
    public static Ui ui = new Ui();
    private static final String YES = "y";
    private static final String NO = "n";

    public static void add(String dishName) {
        if (DishList.find(dishName) == -1) {
            assert DishList.find(dishName) < 0;
            Dish dishToAdd = new Dish(dishName);
            dishList.add(dishToAdd);
            ui.printAddedDish(dishToAdd.getDishName());
        } else {
            assert DishList.find(dishName) >= 0;
            ui.printDishExistsMsg();
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
        ui.printDishList(dishList);
    }

    public static void graph() {
        ui.printDishListGraph(dishList);
    }

    public static void delete(String dishName) {
        Scanner input = new Scanner(System.in);

        int listSize = dishList.size(); //listSize = N
        int dishIndex = DishList.find(dishName);
        if (dishIndex == -1) {
            ui.printDishNotExistMsg();
            assert dishList.size() == listSize : "dishList should be of size N";
        } else {
            ui.printConfirmDelDish();
            String confirmDel = input.nextLine().toLowerCase();
            while (!(confirmDel.equals(YES) | confirmDel.equals(NO))) {
                ui.clearTerminalAndPrintNewPage();
                ui.printInvalidConfirmation();
                confirmDel = input.nextLine().toLowerCase();
            }
            ui.clearTerminalAndPrintNewPage();
            if (confirmDel.equals(YES)) {
                dishList.remove(dishIndex);
                ui.printDishNameRemoved(dishName);
                assert dishList.size() == (listSize - 1) : "dishList should be of size N-1";
            } else {
                ui.printDisregardMsg();
            }
        }
    }



    public static void clearList() {
        Scanner input = new Scanner(System.in);
        ui.printConfirmClearDish();
        String confirmClear = input.nextLine().toLowerCase();

        while (!(confirmClear.equals(YES) | confirmClear.equals(NO))) {
            ui.clearTerminalAndPrintNewPage();
            ui.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        ui.clearTerminalAndPrintNewPage();
        if (confirmClear.equals(YES)) {
            dishList.clear();
            assert dishList.size() == 0 : "dishList should be of size 0";
            ui.printDishListCleared();
        } else {
            ui.printDisregardMsg();
        }
    }

    public static void editName(int dishIndex) throws FoodoramaException {
        if (dishIndex == -1) {
            throw new FoodoramaException(ui.getDishNotExistEdit());
        } else if (dishIndex >= dishList.size()) {
            throw new FoodoramaException(ui.getDishIndexExceedSizeMsg());
        } else {
            String dishName = dishList.get(dishIndex).getDishName();
            ui.printAskNewNameDish(dishName);

            Scanner input = new Scanner(System.in);
            String newName = input.nextLine();

            ui.clearTerminalAndPrintNewPage();
            ui.printConfirmDishEditMsg(dishName, newName);
            String confirmChange = input.nextLine().toLowerCase();
            while (!(confirmChange.equals(YES) | confirmChange.equals(NO))) {
                ui.clearTerminalAndPrintNewPage();
                ui.printInvalidConfirmation();
                confirmChange = input.nextLine().toLowerCase();
            }
            ui.clearTerminalAndPrintNewPage();
            if (confirmChange.equals(YES)) {
                dishList.get(dishIndex).setDishName(newName);
                ui.printDishNameChanged(newName);
            } else {
                ui.printDisregardMsg();
            }
        }
    }
}
