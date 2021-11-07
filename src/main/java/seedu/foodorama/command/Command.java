package seedu.foodorama.command;

import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

/**
 * Parent class to all other Commands that provides the execute method, allowing Users
 * to carry out their desired Command.
 *
 * @author Dniv-ra
 */
public abstract class Command {

    /**
     * Provides the execute method for all other Commands.
     *
     * @author Dniv-ra
     */
    public abstract void execute(ArrayList<String> parameters) throws FoodoramaException;

}
