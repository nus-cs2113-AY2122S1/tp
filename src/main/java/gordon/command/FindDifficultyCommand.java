package gordon.command;

import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;
import gordon.util.Difficulty;

import java.util.ArrayList;

public class FindDifficultyCommand extends Command {
    Difficulty difficulty;

    public FindDifficultyCommand(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Searching by difficulty...");
        ArrayList<Recipe> diffFilter = cookbook.filterByDifficulty(difficulty);
        for (int i = 0; i < diffFilter.size(); i++) {
            System.out.println((i + 1) + ". " + diffFilter.get(i).getName()
                    + " (Difficulty: " + diffFilter.get(i).getDifficulty().name() + ")");
        }
    }
}
