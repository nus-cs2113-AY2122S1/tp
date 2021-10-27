//@@author marcusbory
package seedu.parser;

import seedu.exception.ForbiddenDetailException;
import seedu.exception.InvalidEmailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.InvalidGithubUsernameException;
import seedu.exception.InvalidLinkedinUsernameException;
import seedu.exception.InvalidNameException;
import seedu.exception.InvalidTelegramUsernameException;
import seedu.exception.InvalidTwitterUsernameException;
import seedu.exception.MissingArgAddException;
import seedu.exception.MissingDetailException;

public class AddContactParser extends ContactParser {
    /**
     * Returns a String[] containing the user's input and the arguments specified for
     * each detail flag. Each detail is parsed individually using parseDetail.
     * <p>
     * Eg. userInput => add -n andre -g ng-andre -te ng_andre -tw andre -e andre@gmail.com -l ng-andre
     * output => ["andre", "ng-andre", "ng_andre", "ng-andre", "ng_andre", "andre", "andre@gmail.com"]
     * </p>
     * Note: Each index is properly identified by enumeration in contact/DetailType
     *
     * @param userInput User's complete untouched input
     * @return String[] Returns an array of details
     * @throws InvalidFlagException If the flag given is not recognised
     */

    public String[] parseContactDetails(String userInput) throws InvalidFlagException, MissingArgAddException,
            InvalidNameException, InvalidGithubUsernameException, InvalidTelegramUsernameException,
            InvalidLinkedinUsernameException, InvalidTwitterUsernameException,
            InvalidEmailException, MissingDetailException, ForbiddenDetailException {
        String[] contactDetails = new String[NUMBER_OF_FIELDS];
        String[] destructuredInputs = userInput.split(DETAIL_SEPARATOR);
        if (destructuredInputs.length < NUMBER_OF_ADD_ARGS) {
            throw new MissingArgAddException();
        }
        for (int i = CONTACT_PARAMS_START_INDEX; i < destructuredInputs.length; i++) {
            parseDetail(contactDetails, destructuredInputs[i]);
        }
        return contactDetails;
    }
}
