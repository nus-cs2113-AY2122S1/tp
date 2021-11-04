package seplanner.exceptions;

// @@author leowyy99

public class SearchMapParseException extends ParserClassException {
    String format = "searchmap <uni index> ~~~~~~~~~~~~~~~ Display the available module mappings for "
            + "selected modules for that university\n";

    /**
     * Constructs an SearchMapParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s The detail message
     * @param errorOffset The position where the error is found while parsing.
     * @param isPrintFormat Whether there is a need to output the format.
     */
    public SearchMapParseException(String s, int errorOffset, boolean isPrintFormat) {
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
