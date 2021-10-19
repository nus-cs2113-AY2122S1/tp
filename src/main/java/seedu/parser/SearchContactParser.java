package seedu.parser;

import seedu.exception.InvalidFlagException;
import seedu.exception.MissingArgException;

import static seedu.parser.ContactParser.NUMBER_OF_SEARCH_ARGS;
import static seedu.parser.ContactParser.getIndexToStore;

public class SearchContactParser {
    //return only search query as a String
    public String parseSearchQuery(String userInput) throws MissingArgException {
        String[] destructuredInputs = userInput.split(" ", NUMBER_OF_SEARCH_ARGS);
        if (destructuredInputs.length == 2) { //search for name if no flag specified
            return destructuredInputs[1].toLowerCase().trim();
        } else if (destructuredInputs.length == 3) { //search for name with flag
            return destructuredInputs[2].toLowerCase().trim();
        }
        //no query specified
        //destructuredInputs.length < 2
        throw new MissingArgException();
    }

    public int getDetailFlag(String userInput) throws MissingArgException, InvalidFlagException {
        String[] destructuredInputs = userInput.split(" ", NUMBER_OF_SEARCH_ARGS);
        //"search  " with one or two spaces should not have an issue since the input is trimmed
        if (destructuredInputs.length < 2) { //no arguments specified, only "search"
            throw new MissingArgException();
        } else if (destructuredInputs.length == 2) { //no flag specified
            return -1;
        } else {
            assert destructuredInputs.length == 3;
            return getIndexToStore(destructuredInputs[2]);
        }
    }
}
