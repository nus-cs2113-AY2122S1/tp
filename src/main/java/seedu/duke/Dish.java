package seedu.duke;

import seedu.duke.command.CommandNames;

import java.util.ArrayList;

public class Dish {
    private String dishName;
    private Double wastage;
    private ArrayList<Ingredient> constituents = new ArrayList<>();

    public Dish(String dishName) {
        this.dishName = dishName;
        this.wastage = 0.0;
    }

    public String getDishName() {
        return dishName;
    }

    public void addConstituent(String ingredientName) {
        int ingredientIndex = IngredientList.find(ingredientName);
        if (ingredientIndex == -1) {
            System.out.println("Ingredient does not exist");
        } else {
            constituents.add(IngredientList.ingredientList.get(ingredientIndex));
            System.out.println("Added " + ingredientName + " as ingredient of " + dishName);
        }
    }

    public void addWaste(Double value) {
        wastage += value;
        System.out.println("Wastage of " + dishName + " is now " + wastage);
        //Todo proportion stuff
        /*
        for (Ingredient ingredient: constituents) {
            ingredient.addWeight(value);
            System.out.println("Wastage of " + ingredient.getIngredientName() +
            " is now " + ingredient.getIngredientWeight());
        }
        */
    }

    @Override
    //Todo Format into proper string
    public String toString() {
        return "Dish{"
                + "dishName='"
                + dishName
                + '\''
                + ", constituents="
                + constituents
                + '}';
    }
}
