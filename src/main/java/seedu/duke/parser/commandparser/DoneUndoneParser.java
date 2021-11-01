package seedu.duke.parser.commandparser;

import seedu.duke.commands.Command;
import seedu.duke.parser.Parser;

public abstract class DoneUndoneParser extends Parser {

    public Command getDoneCommand(String commandDetails) {
        return null;
    }

    public Command getUndoCommand(String commandDetails) {
        return null;
    }
}
