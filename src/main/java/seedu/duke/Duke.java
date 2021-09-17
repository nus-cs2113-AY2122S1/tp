package seedu.duke;

import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Cookbook cBook = new Cookbook();

        Recipe recipeA = new Recipe("Caprese Salad");
        recipeA.setTimes(10, 0);
        recipeA.addIngredient(new Ingredient("2 tomatoes", 0.80));
        recipeA.addIngredient(new Ingredient("Fresh mozzarella", 1.50));
        recipeA.addIngredient(new Ingredient("Olive oil", 0.10));
        recipeA.addIngredient(new Ingredient("Salad leaves", 0.20));
        recipeA.addStep("Slice tomatoes and mozzarella into 1 inch slices");
        recipeA.addStep("Lay alternating on a plate");
        recipeA.addStep("Dress with salad leaves");
        recipeA.addStep("Drizzle with olive oil");
        recipeA.setCalories(200);
        recipeA.addTag("Vegan");
        cBook.addRecipe(recipeA);

        Recipe recipeB = new Recipe("Ice Lemon Tea");
        recipeB.setTimes(5, 0);
        recipeB.addIngredient(new Ingredient("Half a lemon", 0.20));
        recipeB.addIngredient(new Ingredient("Tea bag", 0.10));
        recipeB.addIngredient(new Ingredient("1 tsp sugar", 0.05));
        recipeB.addIngredient(new Ingredient("Ice cubes", 0));
        recipeB.addStep("Boil water and steep tea bags, adding sugar");
        recipeB.addStep("Add ice to glass");
        recipeB.addStep("Pour tea into glass");
        recipeB.addStep("Dress with lemon slice");
        recipeB.setCalories(50);
        recipeB.addTag("Vegan");
        cBook.addRecipe(recipeB);

        Recipe recipeC = new Recipe("An apple");
        recipeC.setTimes(5, 0);
        recipeC.addIngredient(new Ingredient("Apple", 1));
        recipeC.setCalories(20);
        recipeC.addTag("Vegan");
        cBook.addRecipe(recipeC);

        Recipe recipeD = new Recipe("Fried Rice");
        recipeD.setTimes(10, 20);
        recipeD.addIngredient(new Ingredient("Rice", 2));
        recipeD.addIngredient(new Ingredient("Meat", 4));
        recipeD.addIngredient(new Ingredient("Eggs", 1));
        recipeD.addIngredient(new Ingredient("Vegetables", 0.4));
        recipeD.addIngredient(new Ingredient("Oil", 0.1));
        recipeD.addStep("Cut vegetables");
        recipeD.addStep("Add oil to pan on medium low");
        recipeD.addStep("Fry meat for 5 min");
        recipeD.addStep("Add eggs and fry for 2 min");
        recipeD.addStep("And vegetables and saute for 1 min");
        recipeD.setCalories(230);
        cBook.addRecipe(recipeD);

        cBook.checkRecipe("ice lemon tea");

        cBook.sortByPrice();
        System.out.println("Sorted by price: ");
        System.out.println(cBook);

        cBook.sortByID();
        System.out.println("Sorted by ID: ");
        System.out.println(cBook);

        cBook.sortByCalories();
        System.out.println("Sorted by calories: ");
        System.out.println(cBook);

        Cookbook filteredCBook = new Cookbook();
        ArrayList<Recipe> filteredR = cBook.filterByTag("vegan");
        filteredCBook.recipes.addAll(filteredR);
        System.out.println("Recipes containing the 'Vegan' tag: ");
        System.out.println(filteredCBook);
    }
}
