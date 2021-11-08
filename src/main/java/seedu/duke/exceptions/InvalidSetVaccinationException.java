package seedu.duke.exceptions;

import static seedu.duke.common.Messages.WRONG_SET_VACC_INPUT;

public class InvalidSetVaccinationException extends InvalidInputException {
    public InvalidSetVaccinationException() {
        super(WRONG_SET_VACC_INPUT);
    }
}
