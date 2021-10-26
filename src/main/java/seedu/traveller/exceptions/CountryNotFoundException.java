package seedu.traveller.exceptions;


//@@author Uxinnn
public class CountryNotFoundException extends TravellerException {
    public CountryNotFoundException() {
        message = "\tPlease input a valid country.";
    }
}
