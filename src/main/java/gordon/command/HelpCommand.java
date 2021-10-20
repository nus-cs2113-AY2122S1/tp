package gordon.command;

import gordon.kitchen.Cookbook;

public class HelpCommand extends Command {
    static final String helpString = "To add a recipe: add \"recipe name\" \"/ingredients\" 1+2+3 \"/steps\" 1+2+3 \"/calories\" 123" + System.lineSeparator() +
            "To delete a recipe: deleteRecipe \"Index of recipe\"" + System.lineSeparator() +
            "To see all your recipes: listRecipes" + System.lineSeparator() +
            "To find a recipe: find \"Keyword\"" + System.lineSeparator() +
            "To check a specific recipe: check \"Name of Recipe\"" + System.lineSeparator() +
            "To tag a recipe: tag / recipeName / tagName1 + tagName2 + ..." + System.lineSeparator() +
            "To untag a recipe: untag / recipeName / tagName1 + tagName2 + ..." + System.lineSeparator() +
            "To list all tags: listTags";

    @Override
    public void execute(Cookbook cookbook) {
        System.out.println(helpString);
    }
}
