package seedu.parser;

import seedu.exception.DuplicateDetailException;
import seedu.exception.ForbiddenDetailException;
import seedu.exception.InvalidEmailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.InvalidGithubUsernameException;
import seedu.exception.InvalidLinkedinUsernameException;
import seedu.exception.InvalidNameException;
import seedu.exception.InvalidTelegramUsernameException;
import seedu.exception.InvalidTwitterUsernameException;
import seedu.exception.MissingArgEditException;
import seedu.exception.MissingDetailException;

//@@author ng-andre
public class EditContactParser extends ContactParser {
    public static final String BUFFER = " ";

    /**
     * Takes in the userInput and returns a String array of the contact's fields to be edited.
     * @param userInput The command input from the user in full.
     * @return String array of size 6 containing user details to be edited, or null if field is not specified.
     * @throws InvalidFlagException if the flag specified is not one of the six flags allowed.
     * @throws MissingDetailException if there is a flag specified but no detail specified
     * @throws ForbiddenDetailException if the detail specified contains illegal Strings eg. null
     * @throws InvalidNameException if the name contains illegal characters
     * @throws InvalidGithubUsernameException if the Github username contains illegal characters
     * @throws InvalidTelegramUsernameException if the Telegram username contains illegal characters
     * @throws InvalidLinkedinUsernameException if the LinkedIn username contains illegal characters
     * @throws InvalidTwitterUsernameException if the Twitter handle contains illegal characters
     * @throws InvalidEmailException if the email contains illegal characters
     * @throws MissingArgEditException if the userInput is missing arguments such as index
     * @throws DuplicateDetailException if the userInput has duplicate flags
     */
    public String[] parseContactDetails(String userInput)
            throws InvalidFlagException, MissingDetailException, ForbiddenDetailException, InvalidNameException,
            InvalidGithubUsernameException, InvalidTelegramUsernameException, InvalidLinkedinUsernameException,
            InvalidTwitterUsernameException, InvalidEmailException, MissingArgEditException,
            DuplicateDetailException {

        String[] inputDetails = userInput.split(" ", NUMBER_OF_EDIT_ARGS);
        if (inputDetails.length < NUMBER_OF_EDIT_ARGS) {
            //if arguments are missing e.g. edit 2
            throw new MissingArgEditException();
        }

        assert (inputDetails.length == NUMBER_OF_EDIT_ARGS);

        //buffer is used to ensure first flag can be read
        String[] destructuredInputs = (BUFFER + inputDetails[USER_INFO_INDEX]).split(DETAIL_SEPARATOR);
        //handles illegal input "edit 0 -" and "edit 0 [invalid string]"
        //valid input will take the form of [, -flag input] so min length should be 2
        if (destructuredInputs.length < NUMBER_OF_EDIT_ARGS - 1) {
            throw new MissingArgEditException();
        }

        //initialise null array of strings
        String[] contactDetails = new String[NUMBER_OF_FIELDS];
        for (int i = CONTACT_PARAMS_START_INDEX; i < destructuredInputs.length; i++) {
            parseDetail(contactDetails, destructuredInputs[i]);
        }

        return contactDetails;
    }
}
