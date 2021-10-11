package seedu.duke;

public class Ingredient {

    private String ingredientName;

    private double ingredientWeight;
    private double ingredientWaste;

    public Ingredient(String ingredientName, double ingredientWeight) {
        this.ingredientName = ingredientName;
        this.ingredientWeight = ingredientWeight;
        this.ingredientWaste = 0;
    }

    public Ingredient(String ingredientName, double ingredientWeight, double ingredientWaste) {
        this.ingredientName = ingredientName;
        this.ingredientWeight = ingredientWeight;
        this.ingredientWaste = ingredientWaste;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void updateIngredientWeight(double weightChange) {
        ingredientWeight += weightChange;
        System.out.println("Stores of " + ingredientName + " is now " + ingredientWeight);
    }

    public void addWaste(Double waste) {
        ingredientWaste += waste;
        System.out.println("Wastage of " + ingredientName + " is now " + ingredientWaste);
    }

    @Override
    //Todo format into better string
    public String toString() {
        return ingredientName + '\n'
                + "   Storage: " + ingredientWeight + '\n'
                + "   Wastage: " + ingredientWaste;
    }

    public String formatData() {
        return ingredientName + "|"  + ingredientWeight + "|" + ingredientWaste;
    }


}
