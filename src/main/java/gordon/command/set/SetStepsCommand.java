package gordon.command.set;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;

import java.util.ArrayList;

public class SetStepsCommand extends Command {
    String recipeName;
    ArrayList<String> steps;

    public SetStepsCommand(String recipeName, ArrayList<String> steps) {
        this.recipeName = recipeName;
        this.steps = steps;
    }

    @Override
    public void execute(Cookbook cookbook) {
        System.out.println("Setting steps...");
        try {
            cookbook.setSteps(recipeName, steps);
            System.out.println("Steps set successfully.");
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
    }
}
