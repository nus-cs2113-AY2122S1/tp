package gordon.command.find;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;
import gordon.util.Difficulty;

import java.util.ArrayList;
import java.util.Collections;

public class FindDifficultyCommand extends Command {
    Difficulty difficulty;

    public FindDifficultyCommand(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * <h2> void execute(cookbook).</h2>
     *
     * <p> This method calls the filterByDifficulty function and prints the sorted list of recipes</p>
     *
     * @param cookbook The recipe object to be referenced
     */
    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Searching by difficulty...");
        ArrayList<Recipe> diffFilter = cookbook.filterByDifficulty(difficulty);
        ArrayList<String> namesInOrder = new ArrayList<String>();
        
        for (int i = 0; i < diffFilter.size(); i++) {
            namesInOrder.add(diffFilter.get(i).getName());
        }

        // Sort
        Collections.sort(namesInOrder, String.CASE_INSENSITIVE_ORDER);

        if (diffFilter.size() == 0) {
            System.out.println("GordonException: " + GordonException.NO_RESULT_FOUND);
        } else {
            for (int i = 0; i < diffFilter.size(); i++) {
                System.out.println((i + 1) + ". " + namesInOrder.get(i)
                        + " (Difficulty: " + diffFilter.get(i).getDifficulty().name() + ")");
            }
        }
    }
}
