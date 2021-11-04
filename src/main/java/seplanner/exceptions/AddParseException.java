package seplanner.exceptions;

import java.text.ParseException;

public class AddParseException extends ParserClassException {
    private final String format = "add /uni <uni index/name> ~~~~~~~~~~~~ Add a university to the selected list\n"
            + "add /mod <mod index/name> ------------ Add a module to the selected list\n"
            +  "add /map <uni index> <mapping index> ~ Add that module mapping to the selected list\n";

    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s           the detail message
     * @param errorOffset the position where the error is found while parsing.
     */
    public AddParseException(String s, int errorOffset, boolean isPrintFormat) {
        super(s, errorOffset, isPrintFormat);
    }

    public String getFormat() {
        return format;
    }
}
