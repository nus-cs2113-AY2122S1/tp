package seedu.traveller.worldmap.exceptions;

//@@author Jach23
public class EmptyVertexException extends WorldMapException {
    public EmptyVertexException(String s) {
        message = "\tCountry '" + s + "' doesn't exist!";
    }
}