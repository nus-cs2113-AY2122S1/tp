package gordon.kitchen;

import gordon.exception.GordonException;
import gordon.util.Difficulty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Objects;

class RecipeTest {
    @Test
    public void testToString() {
        Recipe newRecipe = new Recipe("Coffee");
        newRecipe.addIngredient("Coffee beans");
        newRecipe.addIngredient("Water");
        newRecipe.addStep("Boil water");
        newRecipe.addStep("Grind beans");
        newRecipe.addStep("Pour water over grounds");
        newRecipe.setCalories(10);
        String expected = "Coffee" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "10" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Coffee beans" + System.lineSeparator()
                + "2. Water" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Boil water" + System.lineSeparator()
                + "2. Grind beans" + System.lineSeparator()
                + "3. Pour water over grounds" + System.lineSeparator();
        assertEquals(expected, newRecipe.toString());
    }

    @Test
    public void testGetName() {
        Recipe newRecipe = new Recipe("Coffee");
        assertEquals("Coffee", newRecipe.name);
    }

    @Test
    public void testRemove() {
        try {
            Recipe newRecipe = new Recipe("Coffee");
            newRecipe.addIngredient("Coffee beans");
            newRecipe.addIngredient("Water");
            newRecipe.addStep("Boil water");
            newRecipe.addStep("Grind beans");
            newRecipe.addStep("Pour water over grounds");
            newRecipe.removeStep(1);
            String expected = "Coffee" + System.lineSeparator()
                    + "Ingredients needed: " + System.lineSeparator()
                    + "1. Coffee beans" + System.lineSeparator()
                    + "2. Water" + System.lineSeparator()
                    + "Method: " + System.lineSeparator()
                    + "1. Boil water" + System.lineSeparator()
                    + "2. Pour water over grounds" + System.lineSeparator();
            assertEquals(expected, newRecipe.toString());
            newRecipe.removeStep(3098);
            fail();
        } catch (GordonException g) {
            System.out.println(g.getMessage());
        }
    }

    @Test
    public void addIngredient_newIngredient_success() {
        Recipe newRecipe = new Recipe("Chicken rice");
        newRecipe.addIngredient("Chicken");
        newRecipe.addIngredient("Rice");
        newRecipe.addStep("Cook rice");
        newRecipe.addStep("Cook chicken");
        newRecipe.addStep("Mix");
        newRecipe.addIngredient("Salt");
        String expected = "Chicken rice" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "3. Salt" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook rice" + System.lineSeparator()
                + "2. Cook chicken" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator();
        assertEquals(expected, newRecipe.toString());
    }

    @Test
    public void testMultipleSetDifficulty() {
        Recipe newRecipe = new Recipe("Curry Rice");
        newRecipe.addIngredient("Curry Sauce");
        newRecipe.addIngredient("Rice");
        newRecipe.addStep("Cook Rice");
        newRecipe.addStep("Dissolve Curry cubes");
        newRecipe.addStep("Pour sauce on rice");
        newRecipe.setDifficulty(Difficulty.Medium);
        newRecipe.setDifficulty(Difficulty.Hard);
        String expected = "Curry Rice" + System.lineSeparator()
                + "Difficulty: " + System.lineSeparator()
                + "Hard" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Curry Sauce" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook Rice" + System.lineSeparator()
                + "2. Dissolve Curry cubes" + System.lineSeparator()
                + "3. Pour sauce on rice" + System.lineSeparator();
        assertEquals(expected, newRecipe.toString());
    }

    @Test
    public void testMultipleSetCalories() {
        Recipe newRecipe = new Recipe("Curry Rice");
        newRecipe.addIngredient("Curry Sauce");
        newRecipe.addIngredient("Rice");
        newRecipe.addStep("Cook Rice");
        newRecipe.addStep("Dissolve Curry cubes");
        newRecipe.addStep("Pour sauce on rice");
        newRecipe.setCalories(500);
        newRecipe.setCalories(300);
        String expected = "Curry Rice" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "300" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Curry Sauce" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook Rice" + System.lineSeparator()
                + "2. Dissolve Curry cubes" + System.lineSeparator()
                + "3. Pour sauce on rice" + System.lineSeparator();
        assertEquals(expected, newRecipe.toString());
    }

    @Test
    public void testMultipleSetTimes() {
        Recipe newRecipe = new Recipe("Curry Rice");
        newRecipe.addIngredient("Curry Sauce");
        newRecipe.addIngredient("Rice");
        newRecipe.addStep("Cook Rice");
        newRecipe.addStep("Dissolve Curry cubes");
        newRecipe.addStep("Pour sauce on rice");
        newRecipe.setTimes(50, 30);
        newRecipe.setTimes(60, 70);
        String expected = "Curry Rice" + System.lineSeparator()
                + "Preparation time: " + System.lineSeparator()
                + "60 minute(s)" + System.lineSeparator()
                + "Cooking time: " + System.lineSeparator()
                + "70 minute(s)" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Curry Sauce" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook Rice" + System.lineSeparator()
                + "2. Dissolve Curry cubes" + System.lineSeparator()
                + "3. Pour sauce on rice" + System.lineSeparator();
        assertEquals(expected, newRecipe.toString());
    }

    @Test
    public void testMultipleSetPrice() {
        Recipe newRecipe = new Recipe("Curry Rice");
        newRecipe.addIngredient("Curry Sauce");
        newRecipe.addIngredient("Rice");
        newRecipe.addStep("Cook Rice");
        newRecipe.addStep("Dissolve Curry cubes");
        newRecipe.addStep("Pour sauce on rice");
        newRecipe.setTotalPrice(3.10F);
        newRecipe.setTotalPrice(2.90F);
        String expected = "Curry Rice" + System.lineSeparator()
                + "Total price of ingredients: " + System.lineSeparator()
                + "$2.90" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Curry Sauce" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook Rice" + System.lineSeparator()
                + "2. Dissolve Curry cubes" + System.lineSeparator()
                + "3. Pour sauce on rice" + System.lineSeparator();
        assertEquals(expected, newRecipe.toString());
    }

    @Test
    public void testReplaceIngredients() {
        Recipe newRecipe = new Recipe("Curry Rice");
        newRecipe.addIngredient("Curry Sauce");
        newRecipe.addIngredient("Rice");
        newRecipe.addStep("Cook Rice");
        newRecipe.addStep("Dissolve Curry cubes");
        newRecipe.addStep("Pour sauce on rice");

        ArrayList<String> newIngredients = new ArrayList<>();
        newIngredients.add("Mushrooms");
        newIngredients.add("Curry Pork");

        newRecipe.replaceIngredients(newIngredients);
        String expected = "Curry Rice" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Mushrooms" + System.lineSeparator()
                + "2. Curry Pork" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook Rice" + System.lineSeparator()
                + "2. Dissolve Curry cubes" + System.lineSeparator()
                + "3. Pour sauce on rice" + System.lineSeparator();
        assertEquals(expected, newRecipe.toString());
    }

    @Test
    public void testReplaceSteps() {
        Recipe newRecipe = new Recipe("Curry Rice");
        newRecipe.addIngredient("Mushrooms");
        newRecipe.addIngredient("Curry Pork");
        newRecipe.addStep("Cook Rice");
        newRecipe.addStep("Dissolve Curry cubes");
        newRecipe.addStep("Pour sauce on rice");

        ArrayList<String> newSteps = new ArrayList<>();
        newSteps.add("Simmer mushrooms");
        newSteps.add("Chop pork");

        newRecipe.replaceSteps(newSteps);
        String expected = "Curry Rice" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Mushrooms" + System.lineSeparator()
                + "2. Curry Pork" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Simmer mushrooms" + System.lineSeparator()
                + "2. Chop pork" + System.lineSeparator();
        assertEquals(expected, newRecipe.toString());
    }


}
