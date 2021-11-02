package seplanner.exceptions;

import java.text.ParseException;

public class ListParseException extends ParserClassException {
    private final String format = "list /muni --------------------------- "
            + "Display all universities from the master list\n"
            + "list /suni ~~~~~~~~~~~~~~~~~~~~~~~~~~~ Display all the universities and their module "
            + "mappings added by the user\n"
            + "list /mmod --------------------------- Display all the modules from the master list\n"
            + "list /suni ~~~~~~~~~~~~~~~~~~~~~~~~~~~ Display all the modules added by the user\n";

    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s           the detail message
     * @param errorOffset the position where the error is found while parsing.
     */
    public ListParseException(String s, int errorOffset) {
        super(s, errorOffset);
    }

    public String getFormat() {
        return format;
    }
}
