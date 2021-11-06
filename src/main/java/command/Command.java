package command;

import java.util.LinkedHashMap;

/**
 * Represents the generic command. Helps to declare the abstract methods. It is inherited by all commands.
 */
public abstract class Command {
    protected LinkedHashMap<String, String> parameters;

    public abstract void execute();
}
