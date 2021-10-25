package gordon.command.tag;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.util.Tag;

public class TagAddCommand extends Command {

    private String recipeName;
    private String[] tagNames;

    public TagAddCommand(String recipeName, String[] tagNames) {
        this.recipeName = recipeName;
        this.tagNames = tagNames;
    }

    @Override
    public void execute(Cookbook cookbook) {
        try {
            cookbook.isRecipeExist(recipeName);    // Tag cannot exist without accompanying recipe
            for (String tagName : tagNames) {
                Tag createdTag = new Tag(tagName.trim(), recipeName);

                // if master-Tag doesn't exist in Cookbook
                if (!cookbook.doesCookbookTagExists(tagName.trim())) {
                    cookbook.addCookbookTag(createdTag);     // create a master-Tag in Cookbook
                } else {
                    cookbook.appendRecipeToCookbookTag(tagName.trim(), recipeName);    // modify master-Tag in Cookbook
                }

                cookbook.addTagToRecipes(createdTag);
            }
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
