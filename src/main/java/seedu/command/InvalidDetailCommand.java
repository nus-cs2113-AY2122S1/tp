package seedu.command;

import seedu.parser.FailedCommandType;
import seedu.ui.ExceptionTextUi;

public class InvalidDetailCommand extends Command {
    private FailedCommandType type;

    public InvalidDetailCommand(FailedCommandType type) {
        this.type = type;
    }

    public FailedCommandType getType() {
        return type;
    }

    public void execute() {
        switch (type) {
        case INVALID_NAME:
            ExceptionTextUi.invalidNameInput();
            break;
        case INVALID_GITHUB_USERNAME:
            ExceptionTextUi.invalidGithubUsernameInput();
            break;
        case INVALID_TELEGRAM:
            ExceptionTextUi.invalidTelegramUsernameInput();
            break;
        case INVALID_LINKEDIN:
            ExceptionTextUi.invalidLinkedinUsernameInput();
            break;
        case INVALID_MAIL:
            ExceptionTextUi.invalidEmailInput();
            break;
        case INVALID_TWITTER:
            ExceptionTextUi.invalidTwitterUsernameInput();
            break;
        case FORBIDDEN_DETAIL:
            ExceptionTextUi.forbiddenDetailMessage();
            break;
        default:
            return;
        }
    }
}
