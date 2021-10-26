package seedu.command;

import seedu.parser.FailedCommandType;
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
        case INVALID_FLAG: // Fallthrough
        case INVALID_INDEX: // Fallthrough
        case INVALID_NUM: // Fallthrough
        case INVALID_FORMAT: // Fallthrough
        case NUM_OUT_OF_BOUND: // Fallthrough
        case NUM_OUT_OF_BOUND_EDIT: // Fallthrough
        case INVALID_DELETE: //Fallthrough
            invalidUserInputExecute();
            break;
        case MISSING_ARG: // Fallthrough
        case MISSING_ARGS_EDIT: // Fallthrough
        case MISSING_ARGS_ADD: // Fallthrough
        case MISSING_ARGS_SEARCH: // Fallthrough
        case MISSING_DETAIL: // Fallthrough
        case MISSING_NAME: // Fallthrough
        case MISSING_INDEX: // Fallthrough
            missingUserInputExecute();
            break;
        default:
            return;
        }
    }

    private void invalidUserInputExecute() {
        switch (type) {
        case INVALID_FLAG:
            ExceptionTextUi.invalidFlagMessage();
            break;
        case INVALID_INDEX:
            ExceptionTextUi.invalidIndexMessage();
            break;
        case INVALID_NUM:
            ExceptionTextUi.invalidNumMessage();
            break;
        case INVALID_FORMAT:
            ExceptionTextUi.invalidFormatMessage();
            break;
        case NUM_OUT_OF_BOUND:
            ExceptionTextUi.numOutOfRangeMessage(contactList.getListSize());
            break;
        case NUM_OUT_OF_BOUND_EDIT:
            ExceptionTextUi.numOutOfRangeEditMessage(contactList.getListSize());
            break;
        case INVALID_DELETE:
            ExceptionTextUi.invalidDeleteFlag();
            break;
        default:
            return;
        }
    }

    private void missingUserInputExecute() {
        switch (type) {
        case MISSING_ARG:
            ExceptionTextUi.missingArgMessage();
            break;
        case MISSING_ARGS_EDIT:
            ExceptionTextUi.missingArgEditMessage();
            break;
        case MISSING_ARGS_ADD:
            ExceptionTextUi.missingArgAddMessage();
            break;
        case MISSING_ARGS_SEARCH:
            ExceptionTextUi.missingArgSearchMessage();
            break;
        case MISSING_DETAIL:
            ExceptionTextUi.missingDetailMessage();
            break;
        case MISSING_NAME:
            ExceptionTextUi.missingNameMessage();
            break;
        case MISSING_INDEX:
            ExceptionTextUi.missingIndexMessage();
            break;
        default:
            return;
        }
    }
}
