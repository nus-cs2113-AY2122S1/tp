package seedu.duke.exceptions.weight;

import seedu.duke.ClickfitMessages;

public class NoWeightsException extends WeightException {
    @Override
    public String getMessage() {
        return ClickfitMessages.WEIGHT_EMPTY_ERROR;
    }
}
