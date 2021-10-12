package seedu.duke;

import java.util.ArrayList;

public class Dish {
    private ArrayList<Ingredient> constituents = new ArrayList<>();
    private Ui ui = new Ui();
    private String dishName;
    private Double wastage;
    //Each dish contributes a portion of its wastage to constituent ingredients
    private Double ingredientContribution;

    public Dish(String dishName) {
        this.dishName = dishName;
        this.wastage = 0.0;
        this.ingredientContribution = 0.0;
    }

    public Dish(String dishName, double wastage, double ingredientContribution) {
        this.dishName = dishName;
        this.wastage = wastage;
        this.ingredientContribution = ingredientContribution;
    }

    public String getDishName() {
        return dishName;
    }

    public Double getIngredientContribution() {
        return ingredientContribution;
    }

    public ArrayList<Ingredient> getConstituents() {
        return constituents;
    }

    public void setConstituents(ArrayList<Ingredient> constituents) {
        this.constituents = constituents;
    }

    public void addConstituent(String ingredientName) {
        int ingredientIndex = IngredientList.find(ingredientName);
        if (ingredientIndex == -1) {
            System.out.println(ui.getIngrNotExistMsg());
        } else {
            //Subtract the old contribution if it exists
            for (Ingredient ingredient : constituents) {
                ingredient.addDishWaste(-ingredientContribution);
            }
            constituents.add(IngredientList.ingredientList.get(ingredientIndex));
            System.out.println("Added " + ingredientName + " as ingredient of " + dishName);
            //Modify the ingredient contribution to reflect the change
            ingredientContribution = wastage / constituents.size();
            //Add new contribution
            for (Ingredient ingredient : constituents) {
                //Change in contribution is change in wastage / num of constituents
                ingredient.addDishWaste(ingredientContribution);
            }
        }
    }

    public void addWaste(Double value) {
        assert value > 0 : "Adding negative waste is impossible";
        wastage += value;
        System.out.println("Wastage of " + dishName + " is now " + wastage + " kg");
        if (!constituents.isEmpty()) {
            //Todo proportion stuff and prevent feedback loop
            ingredientContribution = wastage / constituents.size();
            for (Ingredient ingredient : constituents) {
                //Change in contribution is change in wastage / num of constituents
                ingredient.addDishWaste(value / constituents.size());
            }
        }

    }


    @Override
    public String toString() {
        String constituentList = "";
        if (!constituents.isEmpty()) {
            for (Ingredient ingredient : constituents) {
                constituentList = constituentList + "," + ingredient.getIngredientName();
            }
            constituentList = constituentList.replaceFirst(",", "");
        } else {
            constituentList = "None";
        }
        return dishName + '\n'
                + "   Wastage: " + wastage + " kg" + System.lineSeparator()
                + "   Constituents: " + constituentList;
    }

    public String formatData() {
        String output = "";
        output = output + dishName + "|" + wastage + "|" + ingredientContribution;
        for (Ingredient ingredient : constituents) {
            output = output + "|" + ingredient.getIngredientName();
        }
        return output;
    }
}
