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
import seedu.exception.MissingArgEditException;
import seedu.exception.MissingDetailException;

public abstract class ContactParser extends RegexParser implements ContactDetails {
    public static final int CONTACT_PARAMS_START_INDEX = 1;
    public static final int NUMBER_OF_DETAILS = 2;
    public static final int NUMBER_OF_ADD_ARGS = 2;
    public static final int NUMBER_OF_EDIT_ARGS = 3;
    public static final int NUMBER_OF_FIELDS = 6;

    public static final String DETAIL_SEPARATOR = " -";
    public static final int FLAG_INDEX_IN_DETAILS = 0;
    public static final int DETAIL_INDEX_IN_DETAILS = 1;
    public static final int USER_INFO_INDEX = 2;

    public abstract String[] parseContactDetails(String userInput)
            throws InvalidFlagException, MissingDetailException, ForbiddenDetailException,
            InvalidNameException, InvalidGithubUsernameException, InvalidTelegramUsernameException,
            InvalidLinkedinUsernameException, InvalidTwitterUsernameException, InvalidEmailException,
            MissingArgEditException, MissingArgAddException;

    /**
     * This method takes in the contactDetails array and populates it with contact
     * details. It will sift out the flags to decide what details to populate, using
     * the enumeration from DetailType.
     *
     * @param contactDetails String array containing contact details
     * @param detail         Unparsed detail
     * @throws InvalidFlagException If the flag given is not recognised
     */
    public void parseDetail(String[] contactDetails, String detail)
            throws InvalidFlagException, MissingDetailException, ForbiddenDetailException, InvalidNameException,
            InvalidGithubUsernameException, InvalidTelegramUsernameException, InvalidLinkedinUsernameException,
            InvalidTwitterUsernameException, InvalidEmailException {
        String[] destructuredDetails = detail.split(" ", NUMBER_OF_DETAILS);
        // for commands that specify a flag, but do not specify any argument for that
        // flag
        // IndexOutOfBoundsException should not be thrown as the first if case will be
        // true
        if (destructuredDetails.length == 1 || destructuredDetails[1].isBlank()) {
            throw new MissingDetailException();
        }
        assert destructuredDetails.length == NUMBER_OF_DETAILS;
        String flag = destructuredDetails[FLAG_INDEX_IN_DETAILS];
        String detailToStore = destructuredDetails[DETAIL_INDEX_IN_DETAILS].trim();
        if (detailToStore.equals("null")) {
            throw new ForbiddenDetailException();
        }
        checkRegex(flag, detailToStore);
        int indexToStore = getIndexToStore(flag);
        contactDetails[indexToStore] = detailToStore;
    }
}
