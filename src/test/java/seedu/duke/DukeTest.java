package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DukeTest {
    @Test
    public void sampleTest() throws GordonException {

        assertTrue(true);

        Recipe recipeA = new Recipe("Caprese Salad");
        recipeA.setTimes(10, 0);
        recipeA.addIngredient(new Ingredient("2 tomatoes"));
        recipeA.addIngredient(new Ingredient("Fresh mozzarella"));
        recipeA.addIngredient(new Ingredient("Olive oil"));
        recipeA.addIngredient(new Ingredient("Salad leaves"));
        recipeA.addStep("Slice tomatoes and mozzarella into 1 inch slices");
        recipeA.addStep("Lay alternating on a plate");
        recipeA.addStep("Dress with salad leaves");
        recipeA.addStep("Drizzle with olive oil");
        recipeA.setCalories(200);
        recipeA.addTag("Vegan");

        Cookbook cookbook = new Cookbook();
        cookbook.addRecipe(recipeA);

        Recipe recipeB = new Recipe("Ice Lemon Tea");
        recipeB.setTimes(5, 0);
        recipeB.addIngredient(new Ingredient("Half a lemon"));
        recipeB.addIngredient(new Ingredient("Tea bag"));
        recipeB.addIngredient(new Ingredient("1 tsp sugar"));
        recipeB.addIngredient(new Ingredient("Ice cubes"));
        recipeB.addStep("Boil water and steep tea bags, adding sugar");
        recipeB.addStep("Add ice to glass");
        recipeB.addStep("Pour tea into glass");
        recipeB.addStep("Dress with lemon slice");
        recipeB.setCalories(50);
        recipeB.addTag("Vegan");
        cookbook.addRecipe(recipeB);

        Recipe recipeC = new Recipe("An apple");
        recipeC.setTimes(5, 0);
        recipeC.addIngredient(new Ingredient("Apple"));
        recipeC.setCalories(20);
        recipeC.addTag("Vegan");
        cookbook.addRecipe(recipeC);

        Recipe recipeD = new Recipe("Fried Rice");
        recipeD.setTimes(10, 20);
        recipeD.addIngredient(new Ingredient("Rice"));
        recipeD.addIngredient(new Ingredient("Meat"));
        recipeD.addIngredient(new Ingredient("Eggs"));
        recipeD.addIngredient(new Ingredient("Vegetables"));
        recipeD.addIngredient(new Ingredient("Oil"));
        recipeD.addStep("Cut vegetables");
        recipeD.addStep("Add oil to pan on medium low");
        recipeD.addStep("Fry meat for 5 min");
        recipeD.addStep("Add eggs and fry for 2 min");
        recipeD.addStep("And vegetables and saute for 1 min");
        recipeD.setCalories(230);
        cookbook.addRecipe(recipeD);

        cookbook.checkRecipe("ice lemon tea");

        cookbook.sortByPrice();
        System.out.println("Sorted by price: ");
        System.out.println(cookbook);

        cookbook.sortByID();
        System.out.println("Sorted by ID: ");
        System.out.println(cookbook);

        cookbook.sortByCalories();
        System.out.println("Sorted by calories: ");
        System.out.println(cookbook);

        Cookbook filteredCBook = new Cookbook();
        ArrayList<Recipe> filteredR = cookbook.filterByTag("vegan");
        filteredCBook.recipes.addAll(filteredR);
        System.out.println("Recipes containing the 'Vegan' tag: ");
        System.out.println(filteredCBook);
    }
}
