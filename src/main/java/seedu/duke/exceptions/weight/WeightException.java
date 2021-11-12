package seedu.duke.exceptions.weight;

public class WeightException extends Exception {
    @Override
    public String getMessage() {
        return "An unknown error has occurred in Weight Tracker";
    }
}
