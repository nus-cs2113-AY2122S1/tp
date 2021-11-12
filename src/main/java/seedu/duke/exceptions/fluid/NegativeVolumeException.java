package seedu.duke.exceptions.fluid;

public class NegativeVolumeException extends FluidExceptions {
    @Override
    public String getMessage() {
        return "Please input a positive integer value for your volume!";
    }
}
