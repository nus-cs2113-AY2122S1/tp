package seplanner.exceptions;

import java.text.ParseException;

public abstract class ParserClassException extends ParseException {
    boolean isPrintFormat;

    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s           the detail message
     * @param errorOffset the position where the error is found while parsing.
     */
    public ParserClassException(String s, int errorOffset, boolean isPrintFormat) {
        super(s, errorOffset);
        this.isPrintFormat = isPrintFormat;
    }

    public boolean isPrintFormat() {
        return isPrintFormat;
    }

    public abstract String getFormat();
}
