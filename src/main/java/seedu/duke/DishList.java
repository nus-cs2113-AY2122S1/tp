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
    public static int find(String dishName) {
        for (Dish dish: dishList) {
            if (dish.getDishName().equals(dishName.toLowerCase())) {
                return dishList.indexOf(dish);
            }
        }
        return -1;
    }

    public static void list() {
        System.out.println("Here are the dishes you have: ");
        for (int i=0; i<dishList.size(); i++) {
            String currentDish = dishList.get(i).getDishName();
            System.out.println((i+1) + ". " + currentDish);
        }
        System.out.println("You can use commands 'add' or 'find' to add new dishes or find existing ones!");
    }

    private static void delete(String dishName) {
        int dishIndex = DishList.find(dishName);
        if (dishIndex == -1) {
            System.out.println("Dish does not exist");
        } else {
            dishList.remove(dishIndex);
            System.out.println("Dish, " + dishName + " has been removed!");
        }
    }
}
