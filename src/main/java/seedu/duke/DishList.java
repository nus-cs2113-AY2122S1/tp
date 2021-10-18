package seedu.duke;

import java.util.ArrayList;

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
            System.out.println(ui.getDishExistsMsg());
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
        int listSize = dishList.size(); //listSize = N
        int dishIndex = DishList.find(dishName);
        if (dishIndex == -1) {
            System.out.println(ui.getDishNotExistMsg());
            assert dishList.size() == listSize : "dishList should be of size N";
        } else {
            dishList.remove(dishIndex);
            ui.printDishNameRemoved(dishName);
            assert dishList.size() == (listSize - 1) : "dishList should be of size N-1";
        }
    }


    public static void clearList() {
        dishList.clear();
        assert dishList.size() == 0 : "dishList should be of size 0";
    }
}
