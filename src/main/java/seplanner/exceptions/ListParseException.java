package seplanner.exceptions;

// @@author leowyy99

public class ListParseException extends ParserClassException {
    private final String format = "list /muni --------------------------- "
            + "Display all universities from the master list\n"
            + "list /suni ~~~~~~~~~~~~~~~~~~~~~~~~~~~ Display all the universities and their module "
            + "mappings added by the user\n"
            + "list /mmod --------------------------- Display all the modules from the master list\n"
            + "list /suni ~~~~~~~~~~~~~~~~~~~~~~~~~~~ Display all the modules added by the user\n";

    /**
     * Constructs an ListParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s The detail message
     * @param errorOffset The position where the error is found while parsing.
     * @param isPrintFormat Whether there is a need to output the format.
     */

    public ListParseException(String s, int errorOffset, boolean isPrintFormat) {
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
