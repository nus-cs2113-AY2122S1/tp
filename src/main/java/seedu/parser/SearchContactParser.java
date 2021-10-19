package seedu.parser;

import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;
import seedu.exception.MissingArgException;

import static seedu.parser.ContactParser.NUMBER_OF_SEARCH_ARGS;

public class SearchContactParser implements ContactDetails {
    //return only search query as a String
    public String parseSearchQuery(String userInput) throws MissingArgException {
        String[] destructuredInputs = userInput.split(" ", NUMBER_OF_SEARCH_ARGS);
        if (destructuredInputs.length == 2) { //search for name if no flag specified
            return destructuredInputs[1].toLowerCase().trim();
        } else if (destructuredInputs.length == NUMBER_OF_SEARCH_ARGS) { //search for name with flag
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
            return DetailType.NAME.getIndex(); //search names
        } else {
            assert destructuredInputs.length == 3;
            if (destructuredInputs[1].contains("-")) { //check for flag
                String flag = destructuredInputs[1].trim().substring(1);
                return getIndexToStore(flag);
            }
            throw new InvalidFlagException();
        }
    }
}
