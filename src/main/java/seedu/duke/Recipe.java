package seedu.duke;

import java.util.ArrayList;

public class Recipe {
    protected String name;
    protected int id;
    protected Difficulty difficulty;
    protected int preparationTime;
    protected int cookingTime;
    protected ArrayList<Ingredient> ingredients;
    protected ArrayList<String> steps;
    protected float totalPrice;
    protected int calories;
    protected ArrayList<String> tags;

    public Recipe(String name) {
        this.name = name;
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
        tags = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
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

    public void addTag(String tag) {
        tags.add(tag.toLowerCase());
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder(name + System.lineSeparator());
        outputString.append("Preparation time: ").append(preparationTime).append(System.lineSeparator());
        outputString.append("Cooking time: ").append(cookingTime).append(System.lineSeparator());
        outputString.append("Ingredients needed: ").append(System.lineSeparator());
        for (int i = 0; i < ingredients.size(); i++) {
            outputString.append(i + 1).append(". ");
            outputString.append(ingredients.get(i).getDescription());
            outputString.append(System.lineSeparator());
        }
        outputString.append("Method: ").append(System.lineSeparator());
        for (int j = 0; j < steps.size(); j++) {
            outputString.append(j + 1).append(". ");
            outputString.append(steps.get(j));
            outputString.append(System.lineSeparator());
        }
        outputString.append("Total price of ingredients: $")
                .append(String.format("%.2f", totalPrice))
                .append(System.lineSeparator());
        return outputString.toString();
    }
}
