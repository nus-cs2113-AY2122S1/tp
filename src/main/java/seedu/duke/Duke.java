package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

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
        cBook.addRecipe(recipeB);

        cBook.sortByPrice();
    }
}
