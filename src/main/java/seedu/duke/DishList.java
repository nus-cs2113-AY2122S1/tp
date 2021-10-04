package seedu.duke;

import java.util.ArrayList;

public class DishList {
    public static ArrayList<Dish> dishList = new ArrayList<>();

    public static void add(String dishName) {
        if (DishList.find(dishName) == -1) {
            Dish dishToAdd = new Dish(dishName.toLowerCase());
            dishList.add(dishToAdd);
            System.out.println("Added:" + dishToAdd.getDishName());
        } else {
            System.out.println("Dish already exists");
        }
    }

    //Returns -1 if not present, index if present
    private static int find(String dishName) {
        for (Dish dish: dishList) {
            if (dish.getDishName().equals(dishName.toLowerCase())) {
                return dishList.indexOf(dish);
            }
        }
        return -1;
    }
}
