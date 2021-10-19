package gordon.command;

import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.util.Difficulty;

public class SetDifficultyCommand extends Command {
    String recipeName;
    Difficulty newDifficulty;

    public SetDifficultyCommand(String recipeName, Difficulty newDifficulty) {
        this.recipeName = recipeName;
        this.newDifficulty = newDifficulty;
    }

    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Setting difficulty...");
        try {
            cookbook.setDifficulty(recipeName, newDifficulty);
            System.out.println("Difficulty set successfully.");
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
