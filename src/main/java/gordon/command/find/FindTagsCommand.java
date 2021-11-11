package gordon.command.find;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;

import java.util.ArrayList;
import java.util.Collections;

public class FindTagsCommand extends Command {

    ArrayList<String> tags;

    public FindTagsCommand(ArrayList<String> tags) {
        this.tags = tags;
    }

    /**
     * <h2> void execute(cookbook).</h2>
     *
     * <p> This method calls the filterByTags function and prints the sorted list of tags</p>
     *
     * @param cookbook The recipe object to be referenced
     */
    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Searching by tags...");
        ArrayList<Recipe> result = cookbook.filterByTags(tags);
        ArrayList<String> namesInOrder = new ArrayList<String>();

        for (int i = 0; i < result.size(); i++) {
            namesInOrder.add(result.get(i).getName());
        }

        // Sort
        Collections.sort(namesInOrder, String.CASE_INSENSITIVE_ORDER);

        if (result.size() == 0) {
            System.out.println("GordonException: " + GordonException.NO_RESULT_FOUND);
        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.println((i + 1) + ". " + namesInOrder.get(i));
            }
        }
    }
}
