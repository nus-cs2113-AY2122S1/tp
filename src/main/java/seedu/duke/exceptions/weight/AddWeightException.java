package seedu.duke.exceptions.weight;

import seedu.duke.ClickfitMessages;

public class AddWeightException extends WeightException {
    @Override
    public String getMessage() {
        return ClickfitMessages.WEIGHT_ADD_FORMAT_ERROR;
    }
}
