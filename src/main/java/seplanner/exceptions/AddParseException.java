package seplanner.exceptions;

// @@author leowyy99

/**
 * Exception for add command.
 */
public class AddParseException extends ParserClassException {
    private final String format = "add /uni <uni index/name> ~~~~~~~~~~~~ Add a university to the selected list\n"
            + "add /mod <mod index/name> ------------ Add a module to the selected list\n"
            +  "add /map <uni index> <mapping index> ~ Add that module mapping to the selected list\n";

    /**
     * Constructs an AddParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s The detail message
     * @param errorOffset The position where the error is found while parsing.
     * @param isPrintFormat Whether there is a need to output the format.
     */
    public AddParseException(String s, int errorOffset, boolean isPrintFormat) {
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
