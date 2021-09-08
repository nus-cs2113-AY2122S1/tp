package seedu.duke;

import java.util.ArrayList;

public class Recipe implements Comparable<Recipe>{
    protected String name;
    protected int preparationTime;
    protected int cookingTime;
    protected ArrayList<Ingredient> ingredients;
    protected ArrayList<String> steps;
    protected float totalPrice;
    protected String[] tags;

    public Recipe(String name) {
        this.name = name;
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setTimes(int prepTime, int cookTime) {
        preparationTime = prepTime;
        cookingTime = cookTime;
    }

    public void addIngredient(Ingredient ingredient) {
        totalPrice += ingredient.getPrice();
        ingredients.add(ingredient);
    }

    public void removeIngredient(int index) {
        totalPrice -= ingredients.get(index).getPrice();
        ingredients.remove(index);
    }

    public void replaceIngredient(int index, Ingredient newIngredient) {
        totalPrice += newIngredient.getPrice();
        totalPrice -= ingredients.get(index).getPrice();
        ingredients.set(index, newIngredient);
    }

    public void addStep(String step) {
        steps.add(step);
    }

    public void removeStep(int index) {
        steps.remove(index);
    }

    public void replaceStep(int index, String newStep) {
        steps.set(index, newStep);
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        String outputString = name + System.lineSeparator();
        outputString += "Preparation time: " + preparationTime + System.lineSeparator();
        outputString += "Cooking time: " + cookingTime + System.lineSeparator();
        outputString += "Ingredients needed: " + System.lineSeparator();
        for (int i = 0; i < ingredients.size(); i++) {
            outputString += (i + 1) + ". ";
            outputString += ingredients.get(i).getDescription();
            outputString += System.lineSeparator();
        }
        outputString += "Method: " + System.lineSeparator();
        for (int j = 0; j < steps.size(); j++) {
            outputString += (j + 1) + ". ";
            outputString += steps.get(j);
            outputString += System.lineSeparator();
        }
        outputString += "Total price of ingredients: $" + String.format("%.2f", totalPrice) + System.lineSeparator();
        return outputString;
    }

    @Override
    public int compareTo(Recipe r) {
        if (this.totalPrice - r.getTotalPrice() > 0) {
            return 1;
        }
        else if (this.totalPrice - r.getTotalPrice() < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
