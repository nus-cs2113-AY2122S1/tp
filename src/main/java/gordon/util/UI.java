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
        System.out.println("1. Add a recipe: add \"recipe name\" \"/ingredients\" 1+2+3 \"/steps\" 1+2+3 \"/calories\" 123" + System.lineSeparator() +
                "2. Delete a recipe: deleteRecipe \"Index of recipe\"" + System.lineSeparator() +
                "3. List all your recipes: listRecipes" + System.lineSeparator() +
                "4. Find a recipe: find \"Keyword\"" + System.lineSeparator() +
                "5. Check a specific recipe: check \"Name of Recipe\"" + System.lineSeparator() +
                "6. Tag a recipe: tag \"/ recipeName\" \"/ tagName1 + tagName2 + ...\"" + System.lineSeparator() +
                "7. Untag a recipe: untag \"/ recipeName\" \"/ tagName1 + tagName2 + ...\"" + System.lineSeparator() +
                "8. List all tags: listTags" + System.lineSeparator() +
                "9. Help me: help");
    }
}
