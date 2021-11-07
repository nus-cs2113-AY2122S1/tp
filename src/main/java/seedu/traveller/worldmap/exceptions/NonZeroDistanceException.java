package seedu.traveller.worldmap.exceptions;

//@@author jach23
public class NonZeroDistanceException extends WorldMapException {
    public NonZeroDistanceException(Double dist) {
        message = "\tDistance " + dist + " needs to be non-zero!";
    }
}