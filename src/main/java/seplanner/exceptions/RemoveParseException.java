package seplanner.exceptions;

// @@author leowyy99

public class RemoveParseException extends ParserClassException {
    private final String format = "remove </uni> <index> ---------------- Remove the specified university\n"
            + "remove </mod> <index> ~~~~~~~~~~~~~~~~ Remove the specified module\n"
            + "remove /map <uni index> <map index> -- Remove the specified module mapping";

    /**
     * Constructs an RemoveParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s The detail message
     * @param errorOffset The position where the error is found while parsing.
     * @param isPrintFormat Whether there is a need to output the format.
     */
    public RemoveParseException(String s, int errorOffset, boolean isPrintFormat) {
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
