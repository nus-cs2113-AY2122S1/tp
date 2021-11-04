package seplanner.exceptions;

import java.text.ParseException;

public class FindParseException extends ParserClassException {
    private final String format = "find /uni <search field> ------------- "
            + "Find universities that contains the search field\n"
            + "find /mod <search field> ~~~~~~~~~~~~~ Find module names that contain the search field\n"
            + "find /code <search field> ------------ Find module codes that contain the search field\n";

    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s           the detail message
     * @param errorOffset the position where the error is found while parsing.
     */
    public FindParseException(String s, int errorOffset, boolean isPrintFormat) {
        super(s, errorOffset, isPrintFormat);
    }

    public String getFormat() {
        return format;
    }
}
