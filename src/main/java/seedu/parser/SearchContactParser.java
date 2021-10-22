package seedu.parser;

import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;
import seedu.exception.MissingArgException;

import java.util.Locale;

import static seedu.parser.ContactParser.FLAG_INDEX_IN_DETAILS;
import static seedu.parser.ContactParser.NUMBER_OF_DETAILS;
import static seedu.parser.ContactParser.NUMBER_OF_SEARCH_ARGS;

public class SearchContactParser implements ContactDetails {
    //return only search query as a String
    public String parseSearchQuery(String searchInput) throws MissingArgException {
        String[] destructuredInputs = searchInput.trim().split(" ", NUMBER_OF_DETAILS);
        if (searchInput.trim().charAt(FLAG_INDEX_IN_DETAILS) == '-') { //if there is a flag, remove it
            if (destructuredInputs.length < 2) { //input starts with - but only flag or weird input
                throw new MissingArgException();
            }
            //contains flag and search query
            return destructuredInputs[1].toLowerCase().trim();
        }
        //assert no flag exists therefore searchInput is the search query
        return searchInput;
    }

    public int getDetailFlag(String searchInput) throws MissingArgException, InvalidFlagException {
        //userInput is not null since searchInput was obtained from getSearchInput
        if (searchInput.trim().charAt(FLAG_INDEX_IN_DETAILS) == '-') {
            //if a flag is specified then parse the flag
            //handles inputs "search -g" which is missing search query
            String[] destructuredInputs = searchInput.trim().split(" ", NUMBER_OF_DETAILS);
            if (destructuredInputs.length < 2) { //input starts with - but only flag or weird input
                throw new MissingArgException();
            }
            String flag = destructuredInputs[0].trim().substring(1);
            return getIndexToStore(flag);
        }
        //no flag is specified
        return DetailType.NAME.getIndex(); //search names
    }

    public String getSearchInput(String userInput) throws MissingArgException {
        //remove the search command
        String[] destructuredInputs = userInput.trim().split(" ", NUMBER_OF_DETAILS);
        if (destructuredInputs.length < 2) { //no arguments specified, only "search"
            throw new MissingArgException();
        }
        assert destructuredInputs.length == 2;
        return destructuredInputs[1];
    }

}
