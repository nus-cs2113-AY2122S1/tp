package seedu.duke;

import java.util.ArrayList;

public class Dish {
    private String dishName;
    private Double wastage;
    public ArrayList<Ingredient> constituents = new ArrayList<>();

    public Dish(String dishName) {
        this.dishName = dishName;
        this.wastage = 0.0;
    }

    public Dish(String dishName, double wastage) {
        this.dishName = dishName;
        this.wastage = wastage;
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
        assert value > 0 : "Adding negative waste is impossible";
        wastage += value;
        System.out.println("Wastage of " + dishName + " is now " + wastage);
//        //Todo proportion stuff and prevent feedback loop
//        for (Ingredient ingredient: constituents) {
//            ingredient.addWaste(value);
//        }
    }



    @Override
    public String toString() {
        return dishName + '\n' + "   Wastage: " + wastage;
    }

    public String formatData() {
        String output = "";
        output = output + dishName + "|" + wastage;
        for (Ingredient ingredient: constituents) {
            output = output + "|" + ingredient.getIngredientName() ;
        }
        return output;
    }
}
