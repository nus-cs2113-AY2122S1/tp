package seedu.duke.exceptions.weight;

import seedu.duke.ClickfitMessages;

public class DeleteWeightException extends WeightException {
    @Override
    public String getMessage() {
        return ClickfitMessages.WEIGHT_DELETE_FORMAT_ERROR;
    }
}
