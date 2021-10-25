package seedu.foodorama.command;

import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(ArrayList<String> parameters) throws FoodoramaException;

}
