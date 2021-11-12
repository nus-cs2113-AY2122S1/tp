package seplanner.exceptions;

import java.text.ParseException;

// @@author leowyy99

public abstract class ParserClassException extends ParseException {
    boolean isPrintFormat;

    /**
     * Constructs an AddParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s The detail message
     * @param errorOffset The position where the error is found while parsing.
     * @param isPrintFormat Whether there is a need to output the format.
     */
    public ParserClassException(String s, int errorOffset, boolean isPrintFormat) {
        super(s, errorOffset);
        this.isPrintFormat = isPrintFormat;
    }

    /**
     * Get isPrintFormat.
     *
     * @return Whether to output format.
     */
    public boolean isPrintFormat() {
        return isPrintFormat;
    }

    /**
     * Get the format String.
     *
     * @return The command format.
     */
    public abstract String getFormat();
}
