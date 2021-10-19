package gordon.command;

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
                    Tag extractedTag = cookbook.extractTag(tagName.trim());    // Tag must exist to be untagged
                    cookbook.deleteRecipeTag(extractedTag);                    // remove from every recipe that has it
                    cookbook.deleteCookbookTag(extractedTag);                  // remove from master-Tag (Cookbook)
                    System.out.println("Successfully deleted " + extractedTag.getTagName());
                }
            }
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
