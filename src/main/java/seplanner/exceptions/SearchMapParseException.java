package seplanner.exceptions;

import java.text.ParseException;

public class SearchMapParseException extends ParserClassException {
    String format = "searchmap <uni index> ~~~~~~~~~~~~~~~ Display the available module mappings for "
            + "selected modules for that university\n";

    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s           the detail message
     * @param errorOffset the position where the error is found while parsing.
     */
    public SearchMapParseException(String s, int errorOffset) {
        super(s, errorOffset);
    }

    public String getFormat() {
        return format;
    }
}
