package seedu.traveller.worldmap.exceptions;


public class EmptyVertexException extends WorldMapException {
    public EmptyVertexException(String s) {
        message = "\tCountry '" + s + "' doesn't exist!";
    }
}