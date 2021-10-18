package seedu.duke.command;

import seedu.duke.exceptions.FoodoramaException;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(ArrayList<String> parameters) throws FoodoramaException;

}
