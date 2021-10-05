package seedu.duke.command;

import seedu.duke.Ingredient;

import java.util.ArrayList;

public class TestCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        System.out.println("This is a test");
    }

    @Override
    public void executeList(ArrayList<String> parameters) {
        // blank as TestCommand will not use executeList
    }
}
