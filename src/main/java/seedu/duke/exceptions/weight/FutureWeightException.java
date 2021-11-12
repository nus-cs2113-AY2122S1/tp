package seedu.duke.exceptions.weight;

import seedu.duke.ClickfitMessages;

public class FutureWeightException extends WeightException {
    @Override
    public String getMessage() {
        return "Please input a date before or equal to today's date!";
    }
}
