package seedu.parser;

import seedu.contact.DetailType;
import seedu.exception.ForbiddenDetailException;
import seedu.exception.InvalidEmailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.InvalidGithubUsernameException;
import seedu.exception.InvalidLinkedinUsernameException;
import seedu.exception.InvalidNameException;
import seedu.exception.InvalidTelegramUsernameException;
import seedu.exception.InvalidTwitterUsernameException;
import seedu.exception.MissingArgException;
import seedu.exception.MissingDetailException;

import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class ContactParser extends RegexParser implements ContactDetails {


    private static final Logger LOGGER = Logger.getLogger(ContactParser.class.getName());

    public abstract String[] parseContactDetails(String userInput) throws InvalidFlagException,
            MissingArgException, MissingDetailException, ForbiddenDetailException,
            InvalidNameException, InvalidGithubUsernameException, InvalidTelegramUsernameException,
            InvalidLinkedinUsernameException, InvalidTwitterUsernameException, InvalidEmailException;

    /**
     * This method takes in the contactDetails array and populates it with contact details.
     * It will sift out the flags to decide what details to populate, using the
     * enumeration from DetailType.
     *
     * @param contactDetails String array containing contact details
     * @param detail         Unparsed detail
     * @throws InvalidFlagException If the flag given is not recognised
     */
    public void parseDetail(String[] contactDetails, String detail)
            throws InvalidFlagException, MissingDetailException, ForbiddenDetailException,
            InvalidNameException, InvalidGithubUsernameException, InvalidTelegramUsernameException,
            InvalidLinkedinUsernameException, InvalidTwitterUsernameException, InvalidEmailException {
        String[] destructuredDetails = detail.split(" ", NUMBER_OF_DETAILS);
        //for commands that specify a flag, but do not specify any argument for that flag
        //IndexOutOfBoundsException should not be thrown as the first if case will be true
        if (destructuredDetails.length == 1 || destructuredDetails[1].isBlank()) {
            throw new MissingDetailException();
        }
        assert destructuredDetails.length == NUMBER_OF_DETAILS;
        String flag = destructuredDetails[FLAG_INDEX_IN_DETAILS];
        String detailToStore = destructuredDetails[DETAIL_INDEX_IN_DETAILS].trim();
        if (detailToStore.equals("null")) {
            throw new ForbiddenDetailException();
        }
        int indexToStore;
        checkRegex(flag, detailToStore);
        indexToStore = getIndexToStore(flag);
        contactDetails[indexToStore] = detailToStore;
    }
}