package seedu.command;

import seedu.parser.FailedCommandType;
import seedu.ui.TextUi;
import seedu.ui.ExceptionTextUi;

public class FailedCommand extends Command {
    private FailedCommandType type;

    public FailedCommand(FailedCommandType type) {
        this.type = type;
    }

    public FailedCommandType getType() {
        return type;
    }

    public void execute() {
        switch (type) {
        case GENERAL:
            ExceptionTextUi.invalidCommandMessage();
            break;
        case INVALID_FLAG:
            ExceptionTextUi.invalidFlagMessage();
            break;
        case INVALID_INDEX:
            ExceptionTextUi.invalidIndexMessage();
            break;
        case MISSING_ARG:
            ExceptionTextUi.missingArgMessage();
            break;
        case MISSING_DETAIL:
            ExceptionTextUi.missingDetailMessage();
            break;
        case INVALID_NUM:
            ExceptionTextUi.invalidNumMessage();
            break;
        case INVALID_FORMAT:
            ExceptionTextUi.invalidFormatMessage();
            break;
        case INVALID_NAME:
            TextUi.invalidNameInput();
            break;
        case INVALID_GITHUB_USERNAME:
            TextUi.invalidGithubUsernameInput();
            break;
        case INVALID_TELEGRAM:
            TextUi.invalidTelegramUsernameInput();
            break;
        case INVALID_LINKEDIN:
            TextUi.invalidLinkedinInput();
            break;
        case INVALID_MAIL:
            TextUi.invalidEmailInput();
            break;
        case INVALID_TWITTER:
            TextUi.invalidTwitterUsernameInput();
            break;
        case NUM_OUT_OF_BOUND:
            ExceptionTextUi.numOutOfRangeMessage(contactList.getListSize() - 1);
            break;
        case MISSING_NAME:
            ExceptionTextUi.missingNameMessage();
            break;
        case FORBIDDEN_DETAIL:
            ExceptionTextUi.forbiddenDetailMessage();
            break;
        default:
            return;
        }
    }
}
