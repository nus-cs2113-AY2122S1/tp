package seedu.parser;

import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;
import seedu.exception.MissingArgSearchException;

import static seedu.parser.ContactParser.FLAG_INDEX_IN_DETAILS;
import static seedu.parser.ContactParser.NUMBER_OF_DETAILS;

//@@author ng-andre
public class SearchContactParser implements ContactDetails {
    /**
     * Takes in the searchInput and returns a String array of the contact's fields to be edited.
     *
     * @param searchInput The command input from the user without the command word 'search'
     * @return String array of size 6 containing user details to be edited, or null if field is not specified.
     * @throws MissingArgSearchException if the searchInput is missing parameters eg query
     */
    //return only search query as a String
    public String parseSearchQuery(String searchInput) throws MissingArgSearchException {
        String[] destructuredInputs = searchInput.trim().split(" ", NUMBER_OF_DETAILS);
        if (searchInput.trim().charAt(FLAG_INDEX_IN_DETAILS) == '-') { //if there is a flag, remove it
            if (destructuredInputs.length < NUMBER_OF_DETAILS) { //input starts with - but only flag or weird input
                throw new MissingArgSearchException();
            }
            //contains flag and search query
            return destructuredInputs[NUMBER_OF_DETAILS - 1].toLowerCase().trim();
        }
        //assert no flag exists therefore searchInput is the search query
        return searchInput;
    }

    /**
     * Parses the detail flag from searchInput and returns an int representing the flag index.
     *
     * @param searchInput The command input from the user without the command word 'search'
     * @return Integer representing the flag index from the user input
     * @throws MissingArgSearchException if the searchInput is missing parameters eg query
     * @throws InvalidFlagException if the flag specified is not one of the six flags allowed.
     */
    public int getDetailFlag(String searchInput) throws MissingArgSearchException, InvalidFlagException {
        //userInput is not null since searchInput was obtained from getSearchInput
        if (searchInput.trim().charAt(FLAG_INDEX_IN_DETAILS) == '-') {
            //if a flag is specified then parse the flag
            //handles inputs "search -g" which is missing search query
            String[] destructuredInputs = searchInput.trim().split(" ", NUMBER_OF_DETAILS);
            if (destructuredInputs.length < NUMBER_OF_DETAILS) { //input starts with - but only flag or weird input
                throw new MissingArgSearchException();
            }
            String flag = destructuredInputs[FLAG_INDEX_IN_DETAILS].trim().substring(1);
            return getIndexToStore(flag);
        }
        //no flag is specified
        return DetailType.NAME.getIndex(); //search names
    }

    /**
     * Removes the search command word from the userInput.
     *
     * @param userInput The command input from the user in full.
     * @return userInput without the search command word.
     * @throws MissingArgSearchException if the searchInput is missing parameters eg query
     */
    public String getSearchInput(String userInput) throws MissingArgSearchException {
        //remove the search command
        String[] destructuredInputs = userInput.trim().split(" ", NUMBER_OF_DETAILS);
        if (destructuredInputs.length < NUMBER_OF_DETAILS) { //no arguments specified, only "search"
            throw new MissingArgSearchException();
        }
        assert destructuredInputs.length == NUMBER_OF_DETAILS;
        return destructuredInputs[NUMBER_OF_DETAILS - 1];
    }

}
