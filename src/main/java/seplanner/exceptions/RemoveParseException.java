package seplanner.exceptions;

import java.text.ParseException;

public class RemoveParseException extends ParserClassException {
    private final String format = "remove </uni> <index> ---------------- Remove the specified university\n"
            + "remove </mod> <index> ~~~~~~~~~~~~~~~~ Remove the specified module\n"
            + "remove /map <uni index> <map index> -- Remove the specified module mapping";

    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s           the detail message
     * @param errorOffset the position where the error is found while parsing.
     */
    public RemoveParseException(String s, int errorOffset, boolean isPrintFormat) {
        super(s, errorOffset, isPrintFormat);
    }

    public String getFormat() {
        return format;
    }
}
