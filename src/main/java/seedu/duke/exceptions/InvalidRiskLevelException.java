package seedu.duke.exceptions;

import static seedu.duke.common.Messages.MESSAGE_INVALID_RISK_LEVEL;

public class InvalidRiskLevelException extends DukeException {
    public InvalidRiskLevelException() {
        super(MESSAGE_INVALID_RISK_LEVEL);
    }

}
