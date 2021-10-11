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

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getIngredientWeight() {
        return ingredientWeight;
    }

    public void setIngredientWeight(double ingredientWeight) {
        this.ingredientWeight = ingredientWeight;
    }

    public void addWaste(Double waste) {
        ingredientWaste += waste;
        System.out.println("Wastage of " + ingredientName + " is now " + ingredientWaste);
    }

//    @Override
//    //Todo format into better string
//    public String toString() {
//        return "Ingredient{"
//                + "ingredientName='"
//                + ingredientName
//                + '\''
//                + ", ingredientWeight="
//                + ingredientWeight
//                + '}';
//    }

    @Override
    public String toString() {
        return "[Ingr] "
                + ingredientName
                + " | Weight: "
                + ingredientWeight + " KG";
    }
}
