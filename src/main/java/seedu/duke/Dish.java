package seedu.duke;

import java.util.ArrayList;

public class Dish {
    private String dishName;
    private ArrayList<Ingredient> constituents = new ArrayList<>();

    public Dish(String dishName) {
        this.dishName = dishName;
    }

    public String getDishName() {
        return dishName;
    }

    public void addConstituent(String ingredientName) {
        //constituents.add() the ingredient from the ingredientList with name ingredientName
    }
}
