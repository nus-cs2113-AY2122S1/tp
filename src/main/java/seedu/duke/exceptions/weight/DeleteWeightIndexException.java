package seedu.duke.exceptions.weight;

import seedu.duke.ClickfitMessages;

public class DeleteWeightIndexException extends WeightException {
    @Override
    public String getMessage() {
        return ClickfitMessages.WEIGHT_DELETE_INDEX_ERROR;
    }
}
