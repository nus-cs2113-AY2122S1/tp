package seedu.duke.exceptions;

import static seedu.duke.common.Messages.WRONG_VIEW_VACC_INPUT;

public class InvalidViewVaccinationException extends InvalidInputException {
    public InvalidViewVaccinationException() {
        super(WRONG_VIEW_VACC_INPUT);
    }
}
