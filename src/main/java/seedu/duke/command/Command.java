package seedu.duke.command;

import seedu.duke.Ingredient;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(ArrayList<String> parameters);

}
