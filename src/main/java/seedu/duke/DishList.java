package seedu.duke;

import java.util.HashMap;

public class DishList {
    public static HashMap<String,Dish> dishList = new HashMap<>();

    public static void add(String dishName) {
        if (!dishList.containsKey(dishName)) {
            dishList.put(dishName, new Dish(dishName));
        } else {
            System.out.println("Dish already exists");
        }
    }
}
