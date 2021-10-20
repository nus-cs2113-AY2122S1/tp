package seedu.traveller.exceptions;


public class DuplicateTripException extends TravellerException {
    public DuplicateTripException(String tripName) {
        message = "\tYou already have a trip named: " + tripName;
    }
}