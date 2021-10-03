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
        ingredients.add(ingredient);
    }

    public void addIngredient(Ingredient ingredient, int index) throws GordonException {
        try {
            ingredients.add(index, ingredient);
        } catch (IndexOutOfBoundsException e) {
            throw new GordonException(GordonException.INDEX_OOB);
        } catch (IllegalArgumentException e) {
            throw new GordonException(GordonException.INDEX_INVALID);
        }
    }

    public void removeIngredient(int index) throws GordonException {
        try {
            ingredients.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new GordonException(GordonException.INDEX_OOB);
        } catch (IllegalArgumentException e) {
            throw new GordonException(GordonException.INDEX_INVALID);
        }
    }

    public void addStep(String step) throws GordonException {
        steps.add(step);
    }

    public void addStep(String step, int index) throws GordonException {
        try {
            steps.add(index, step);
        } catch (IndexOutOfBoundsException e) {
            throw new GordonException(GordonException.INDEX_OOB);
        } catch (IllegalArgumentException e) {
            throw new GordonException(GordonException.INDEX_INVALID);
        }
    }

    public void removeStep(int index) throws GordonException {
        try {
            steps.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new GordonException(GordonException.INDEX_OOB);
        } catch (IllegalArgumentException e) {
            throw new GordonException(GordonException.INDEX_INVALID);
        }
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
