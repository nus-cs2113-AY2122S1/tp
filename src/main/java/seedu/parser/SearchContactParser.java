package seedu.parser;

import seedu.exception.MissingArgException;

import java.util.Locale;

public class SearchContactParser {

    private static final int NUMBER_OF_SEARCH_ARGS = 3;

    //return only search query as a String
    public String parseSearchQuery(String userInput) throws MissingArgException {
        String[] destructuredInputs = userInput.split(" ", NUMBER_OF_SEARCH_ARGS);
        if (destructuredInputs.length == 2) { //search for name if no flag specified
            return destructuredInputs[1].toLowerCase().trim();
        } else if (destructuredInputs.length == 3) { //search for name with flag
            return destructuredInputs[2].toLowerCase().trim();
        }
        throw new MissingArgException(); //if no query is specified
    }
}
