package gordon.command.tag;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.util.Tag;

public class TagDeleteCommand extends Command {
    private String[] tagNames;

    public TagDeleteCommand(String[] tagNames) {
        this.tagNames = tagNames;
    }

    @Override
    public void execute(Cookbook cookbook) {
        try {
            for (String tagName : tagNames) {
                if (!tagName.equals(" ")) {
                    Tag extractedTag = cookbook.extractCookbookTag(tagName.trim());
                    cookbook.deleteTagFromRecipes(extractedTag);
                    cookbook.deleteCookbookTag(extractedTag);
                    System.out.println("Successfully deleted " + extractedTag.getTagName() + " tag");
                }
            }
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
