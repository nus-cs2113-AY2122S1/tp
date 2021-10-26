package seedu.traveller.worldmap.exceptions;


public class NonStringDistanceException extends WorldMapException {
    public NonStringDistanceException(String dist) {
        message = "\tDistance " + dist + " needs to be a number!";
    }
}