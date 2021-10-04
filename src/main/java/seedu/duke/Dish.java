package seedu.duke;

import java.util.ArrayList;

public class Dish {
    String dishName;
    ArrayList<Ingredient> constituents = new ArrayList<>();

    public Dish(String dishName) {
        this.dishName = dishName;
    }

    public void addConstituent(String ingredientName) {
        //constituents.add() the ingredient from the ingredientList with name ingredientName
        //constituents.add(ingredientList.get(ingredientName))
    }
}
