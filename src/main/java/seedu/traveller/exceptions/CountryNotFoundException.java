package seedu.traveller.exceptions;


//@@author Jach23
public class CountryNotFoundException extends TravellerException {
    public CountryNotFoundException() {
        message = "\tPlease input a valid country.";
    }
}
