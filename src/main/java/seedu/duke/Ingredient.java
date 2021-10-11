package seedu.duke;

public class Ingredient {

    private String ingredientName;
    private double ingredientWeight;
    private double ingredientWaste_ingr;
    public double ingredientWaste_dish;

    public Ingredient(String ingredientName, double ingredientWeight) {
        this.ingredientName = ingredientName;
        this.ingredientWeight = ingredientWeight;
        this.ingredientWaste_ingr = 0;
    }

    public Ingredient(String ingredientName, double ingredientWeight, double ingredientWaste) {
        this.ingredientName = ingredientName;
        this.ingredientWeight = ingredientWeight;
        this.ingredientWaste_ingr = ingredientWaste;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void updateIngredientWeight(double weightChange) {
        ingredientWeight += weightChange;
        System.out.println("Stores of " + ingredientName + " is now " + ingredientWeight);
    }

    public void addWaste(Double waste) {
        ingredientWaste_ingr += waste;
        double totalWaste = ingredientWaste_ingr + ingredientWaste_dish;
        System.out.println("Wastage of " + ingredientName + " is now " + totalWaste);
    }

    @Override
    public String toString() {
        double totalWaste = ingredientWaste_ingr + ingredientWaste_dish;
        //Todo add constituents
        return ingredientName + '\n'
                + "   Storage: " + ingredientWeight + '\n'
                + "   Wastage: " + totalWaste;
    }

    public String formatData() {
        return ingredientName + "|"  + ingredientWeight + "|" + ingredientWaste_ingr;
    }


    public void addDishWaste(Double value) {
        ingredientWaste_dish += value;
    }
}
