package seedu.duke;

import seedu.duke.exceptions.FoodoramaException;

import java.util.ArrayList;
import java.util.Scanner;

public class DishList {
    public static ArrayList<Dish> dishList = new ArrayList<>();
    public static Ui ui = new Ui();

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
            while (!(confirmDel.equals("y") | confirmDel.equals("n"))) {
                ui.clearTerminalAndPrintNewPage();
                ui.printInvalidConfirmation();
                confirmDel = input.nextLine().toLowerCase();
            }
            ui.clearTerminalAndPrintNewPage();
            if (confirmDel.equals("y")) {
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

        while (!(confirmClear.equals("y") | confirmClear.equals("n"))) {
            ui.clearTerminalAndPrintNewPage();
            ui.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        ui.clearTerminalAndPrintNewPage();
        if (confirmClear.equals("y")) {
            dishList.clear();
            assert dishList.size() == 0 : "dishList should be of size 0";
            ui.printDishListCleared();
        } else {
            ui.printDisregardMsg();
        }
    }
}
