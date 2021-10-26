package gordon.kitchen;

import gordon.exception.GordonException;
import gordon.util.Difficulty;
import gordon.util.Tag;

import java.util.ArrayList;
import java.util.Objects;

public class Recipe {
    protected String name;
    protected Difficulty difficulty = Difficulty.None;
    protected int preparationTime = -1;
    protected int cookingTime = -1;
    protected int totalTime = -1;
    protected ArrayList<String> ingredients;
    protected ArrayList<String> steps;
    protected float totalPrice = -1;
    protected int calories = -1;
    protected ArrayList<String> recipeTags;

    public Recipe(String name) {
        this.name = name;
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
        recipeTags = new ArrayList<>();
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public boolean containsIngredients(ArrayList<String> search) {
        ArrayList<String> ingredientsToLowercase = new ArrayList<>(ingredients);
        ingredientsToLowercase.replaceAll(String::toLowerCase);
        search.replaceAll(String::toLowerCase);
        return ingredientsToLowercase.containsAll(search);
    }

    public void addStep(String step) {
        steps.add(step);
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

    public void replaceIngredients(ArrayList<String> newIngredients) {
        ingredients = newIngredients;
    }

    public void replaceSteps(ArrayList<String> newSteps) {
        steps = newSteps;
    }

    /////////////////////////// TAGGING FUNCTIONALITIES ///////////////////////////
    public void addTagToRecipe(Tag tag, String recipeName, boolean isStorage) {
        try {
            // Checking if tag has been linked to recipe before
            if (doesRecipeTagExists(tag.getTagName())) {
                throw new GordonException(GordonException.DUPLICATE_TAG_NAME);
            }
            recipeTags.add(tag.getTagName());

            if (!isStorage) {
                System.out.println("Successfully tagged " + recipeName + " under " + tag.getTagName());
            }

        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }

    public void deleteTagFromRecipe(Tag tag) {
        recipeTags.remove(tag.getTagName());
    }

    public boolean doesRecipeTagExists(String tagName) {
        for (String recipeTag : recipeTags) {
            if (recipeTag.toLowerCase().trim().equals(tagName.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public boolean containsTags(ArrayList<String> search) {
        ArrayList<String> tagsToLowercase = new ArrayList<>(recipeTags);
        tagsToLowercase.replaceAll(String::trim);
        tagsToLowercase.replaceAll(String::toLowerCase);
        search.replaceAll(String::trim);
        search.replaceAll(String::toLowerCase);
        return tagsToLowercase.containsAll(search);
    }

    /////////////////////////// GET/SET FUNCTIONALITIES ///////////////////////////
    public String getName() {
        return name.trim();
    }

    /**
     * <h2>void setTimes(prepTime, cookTime).</h2>
     *
     * <p>This method sets the time values of each recipe.</p>
     *
     * @param prepTime The preparation time to be set to the recipe
     * @param cookTime The cooking time to be set to the recipe
     */
    public void setTimes(int prepTime, int cookTime) {
        preparationTime = prepTime;
        cookingTime = cookTime;
        totalTime = prepTime + cookTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * <h2>void setTotalPrice(newPrice).</h2>
     *
     * <p>This method sets the total price of a recipe.</p>
     *
     * @param newPrice The new total price that will be set to the recipe
     */
    public void setTotalPrice(float newPrice) {
        this.totalPrice = newPrice;
    }

    public int getCalories() {
        return calories;
    }

    /**
     * <h2>void setCalories(calories).</h2>
     *
     * <p>This method sets the calories for the recipe.</p>
     *
     * @param calories The number of calories that will be set to the recipe
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder(name + System.lineSeparator());

        if (difficulty != Difficulty.None) {
            outputString.append("Difficulty: ")
                    .append(System.lineSeparator())
                    .append(difficulty)
                    .append(System.lineSeparator());
        }

        if (calories > -1) {
            outputString.append("Calories (kcal): ")
                    .append(System.lineSeparator())
                    .append(calories)
                    .append(System.lineSeparator());
        }

        if (preparationTime > -1) {
            outputString.append("Preparation time: ")
                    .append(System.lineSeparator())
                    .append(preparationTime)
                    .append(" minute(s)")
                    .append(System.lineSeparator());
        }

        if (cookingTime > -1) {
            outputString.append("Cooking time: ")
                    .append(System.lineSeparator())
                    .append(cookingTime)
                    .append(" minute(s)")
                    .append(System.lineSeparator());
        }

        if (totalPrice > -1) {
            outputString.append("Total price of ingredients: ")
                    .append(System.lineSeparator())
                    .append("$")
                    .append(String.format("%.2f", totalPrice))
                    .append(System.lineSeparator());
        }

        outputString.append("Ingredients needed: ").append(System.lineSeparator());
        for (int i = 0; i < ingredients.size(); i++) {
            outputString.append(i + 1).append(". ");
            outputString.append(ingredients.get(i));
            outputString.append(System.lineSeparator());
        }

        outputString.append("Method: ").append(System.lineSeparator());
        for (int j = 0; j < steps.size(); j++) {
            outputString.append(j + 1).append(". ");
            outputString.append(steps.get(j));
            outputString.append(System.lineSeparator());
        }

        if (recipeTags.size() > 0) {
            outputString.append("Tags: ").append(System.lineSeparator());
            for (int k = 0; k < recipeTags.size(); k++) {
                outputString.append(k + 1).append(". ");
                outputString.append(recipeTags.get(k));
                outputString.append(System.lineSeparator());
            }
        }

        return outputString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Recipe recipe = (Recipe) o;
        return preparationTime == recipe.preparationTime
                && cookingTime == recipe.cookingTime
                && Float.compare(recipe.totalPrice, totalPrice) == 0
                && calories == recipe.calories && Objects.equals(name, recipe.name)
                && difficulty == recipe.difficulty
                && Objects.equals(ingredients, recipe.ingredients)
                && Objects.equals(steps, recipe.steps)
                && Objects.equals(recipeTags, recipe.recipeTags);
    }
}
