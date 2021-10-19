package gordon.command;

import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

import java.util.ArrayList;

public class FindTagsCommand extends Command {

    ArrayList<String> tags;

    public FindTagsCommand(ArrayList<String> tags) {
        this.tags = tags;
    }

    @Override
    public void execute(Cookbook cookbook) {
        ArrayList<Recipe> result = cookbook.filterByTags(tags);
        if (result.size() == 0) {
            System.out.println("GordonException: " + GordonException.NO_RESULT_FOUND);
        } else {
            System.out.println("Searching by tags...");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((i + 1) + ". " + result.get(i).getName());
            }
        }
    }
}
