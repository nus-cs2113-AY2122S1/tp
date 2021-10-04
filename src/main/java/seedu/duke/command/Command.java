package seedu.duke.command;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(ArrayList<String> parameters);

}
