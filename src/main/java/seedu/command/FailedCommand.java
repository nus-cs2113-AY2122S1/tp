package seedu.command;

import seedu.parser.FailedCommandType;
import seedu.ui.TextUi;

public class FailedCommand extends Command {
    private FailedCommandType type;

    public FailedCommand(FailedCommandType type) {
        this.type = type;
    }

    public void execute() {
        switch (type) {
        case GENERAL:
            TextUi.invalidCommandMessage();
            break;
        default:
            return;
        }
    }
}
