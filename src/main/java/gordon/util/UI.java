package gordon.util;

public class UI {
    public void printIntro() {
        System.out.println("I'm Gordon! How are you.");
        System.out.println("Let's get to work shall we.");
        System.out.println("Here's what we can get started with." + System.lineSeparator());
    }

    public void printExitMessage() {
        System.out.println("Pack your bags, you're off the show");
    }

    public void printHelp() {
        System.out.println("1. Add a recipe: addRecipe \"recipe name\" \"/ingredients\" 1+2 \"/steps\" 1+2");
        System.out.println("2. Delete a recipe: deleteRecipe \"Index of recipe\"");
        System.out.println("3. List all your recipes: listRecipes");
        System.out.println("4. Find a recipe: find \"Keyword\"");
        System.out.println("5. Check a specific recipe: check \"Name of Recipe\"");
        System.out.println("6. Add calories to recipe: set \"recipe name\" \"/calories\" numberOfCalories ");
        System.out.println("7. Add difficulty levels to recipe: set \"recipe name\" \"/difficulty\" difficultyLevel ");
        System.out.println("8. Add cooking and preparation time to recipe: 
                           set \"recipe name\" \"/time\" cookingTime, preparationTime");
        System.out.println("9. Add price to recipe: set \"recipe name\" \"/price\" recipePrice ");
        System.out.println("10. Tag a recipe: tag \"/ recipeName\" \"/ tagName1 + tagName2 + ...\"");
        System.out.println("11. Untag a recipe: untag \"/ recipeName\" \"/ tagName1 + tagName2 + ...\"");
        System.out.println("12. List all tags: listTags");
        System.out.println("13. Help me: help");
        System.out.println("");
    }
}
