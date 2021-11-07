package gordon.kitchen;

import gordon.exception.GordonException;
import gordon.util.Difficulty;
import gordon.util.Tag;

import java.util.ArrayList;
import java.util.Objects;

/** <h1> Recipe class.</h1>
 *
 * <p>
 *     Stores the name, difficulty, preparation + cooking time, ingredients, steps, calories and tags of a recipe.
 *     Also contains methods that adds, updates or deletes the above-mentioned traits from a recipe.
 * </p>
 */
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

    /**
     * <h2> void setDifficulty(difficulty).</h2>
     *
     * <p> This method sets the difficulty to a recipe.</p>
     *
     * @param difficulty The difficulty to be set to the recipe
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * <h2> void addIngredient(ingredient).</h2>
     *
     * <p> This method adds an ingredient to a recipe.</p>
     *
     * @param ingredient The ingredient to be added to the recipe
     */
    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public boolean containsIngredients(ArrayList<String> search) {
        ArrayList<String> ingredientsToLowercase = new ArrayList<>(ingredients);
        ingredientsToLowercase.replaceAll(String::toLowerCase);
        search.replaceAll(String::toLowerCase);
        return ingredientsToLowercase.containsAll(search);
    }

    /**
     * <h2> void addStep(step).</h2>
     *
     * <p> This method adds a step to a recipe.</p>
     *
     * @param step The step to be added to the recipe
     */
    public void addStep(String step) {
        steps.add(step);
    }

    /**
     * <h2> void removeStep(index).</h2>
     *
     * <p> This method removes a step from the recipe, based on the specified index.</p>
     *
     * @param index The index in the steps array with a corresponding steps.
     * @throws GordonException if the index is out of bounds or if the index is invalid
     */
    public void removeStep(int index) throws GordonException {
        try {
            steps.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new GordonException(GordonException.INDEX_OOB);
        } catch (IllegalArgumentException e) {
            throw new GordonException(GordonException.INDEX_INVALID);
        }
    }

    /**
     * <h2> void replaceIngredients(newIngredients).</h2>
     *
     * <p> This method replaces the current ingredients in the recipe with new ingredients.</p>
     *
     * @param newIngredients The list of ingredients that replaces the current ingredients in the recipe
     */
    public void replaceIngredients(ArrayList<String> newIngredients) {
        ingredients = newIngredients;
    }

    /**
     * <h2> void replaceSteps(newSteps).</h2>
     *
     * <p> This method replaces the current steps in the recipe with new steps.</p>
     *
     * @param newSteps The list of steps that replaces the current steps in the recipe
     */
    public void replaceSteps(ArrayList<String> newSteps) {
        steps = newSteps;
    }

    /////////////////////////// TAGGING FUNCTIONALITIES ///////////////////////////

    /**
     * <h2> void addTagToRecipe(tag, recipeName, isStorage).</h2>
     *
     * <p> This method adds a tag to the recipe.</p>
     *
     * @param tag The tag to be added to the recipe
     * @param recipeName The name of the recipe where the tag is to be added
     * @param isStorage Whether the recipe was loaded from savefile
     */
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

    /**
     * <h2> void deleteTagFromRecipe(tag).</h2>
     *
     * <p> This method deletes a specified tag from the recipe.</p>
     *
     * @param tag The tag to be deleted from the recipe
     */
    public void deleteTagFromRecipe(Tag tag) {
        recipeTags.remove(tag.getTagName());
    }

    public boolean doesRecipeTagExists(String tagName) {
        for (String recipeTag : recipeTags) {
            if (recipeTag.trim().equals(tagName)) {
                return true;
            }
        }

        return false;
    }

    public boolean containsTags(ArrayList<String> search) {
        ArrayList<String> caseSensitiveTags = new ArrayList<>(recipeTags);
        caseSensitiveTags.replaceAll(String::trim);
        search.replaceAll(String::trim);
        return caseSensitiveTags.containsAll(search);
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
