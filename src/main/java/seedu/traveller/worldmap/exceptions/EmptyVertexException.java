package seedu.traveller.worldmap.exceptions;

//@@author jach23
public class EmptyVertexException extends WorldMapException {
    public EmptyVertexException(String s) {
        message = "\tCountry '" + s + "' doesn't exist!";
    }
}