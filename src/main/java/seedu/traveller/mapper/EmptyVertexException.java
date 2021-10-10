package seedu.traveller.mapper;

import seedu.traveller.exceptions.TravellerException;

public class EmptyVertexException extends TravellerException {
    public EmptyVertexException() {
        message = "\tEither of these nodes doesn't exist!";
    }
}
