package seplanner.exceptions;

// @@author leowyy99

public class FindParseException extends ParserClassException {
    private final String format = "find /uni <search field> ------------- "
            + "Find universities that contains the search field\n"
            + "find /mod <search field> ~~~~~~~~~~~~~ Find module names that contain the search field\n"
            + "find /code <search field> ------------ Find module codes that contain the search field\n";

    /**
     * Constructs an FindParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s The detail message
     * @param errorOffset The position where the error is found while parsing.
     * @param isPrintFormat Whether there is a need to output the format.
     */
    public FindParseException(String s, int errorOffset, boolean isPrintFormat) {
        super(s, errorOffset, isPrintFormat);
    }

    /**
     * Get the format String.
     *
     * @return The command format.
     */
    public String getFormat() {
        return format;
    }
}
