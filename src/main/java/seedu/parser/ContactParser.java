package seedu.parser;

import seedu.contact.DetailType;
import seedu.exception.ForbiddenDetailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.MissingArgException;
import seedu.exception.MissingDetailException;

public abstract class ContactParser {
    public static final int CONTACT_PARAMS_START_INDEX = 1;
    public static final int NUMBER_OF_DETAILS = 2;
    public static final int NUMBER_OF_ADD_ARGS = 2;
    public static final int NUMBER_OF_EDIT_ARGS = 3;
    public static final int NUMBER_OF_FIELDS = 6;

    public static final String DETAIL_SEPARATOR = " -";
    public static final int FLAG_INDEX_IN_DETAILS = 0;
    public static final int DETAIL_INDEX_IN_DETAILS = 1;
    public static final int EDIT_USER_INDEX = 1;
    public static final int USER_INFO_INDEX = 2;

    public static final String NAME_FLAG = "n";
    public static final String GITHUB_FLAG = "g";
    public static final String TELEGRAM_FLAG = "te";
    public static final String TWITTER_FLAG = "tw";
    public static final String EMAIL_FLAG = "e";
    public static final String LINKEDIN_FLAG = "l";

    public abstract String[] parseContactDetails(String userInput) throws InvalidFlagException,
            MissingArgException, MissingDetailException, ForbiddenDetailException;

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
            throws InvalidFlagException, MissingDetailException, ForbiddenDetailException {
        String[] destructuredDetails = detail.split(" ", NUMBER_OF_DETAILS);
        //for commands that specify a flag, but do not specify any argument for that flag
        //IndexOutOfBoundsException should not be thrown as the first if case will be true
        if (destructuredDetails.length == 1 || destructuredDetails[1].isBlank()) {
            throw new MissingDetailException();
        }
        int indexToStore;
        assert destructuredDetails.length == NUMBER_OF_DETAILS;
        String flag = destructuredDetails[FLAG_INDEX_IN_DETAILS];
        String detailToStore = destructuredDetails[DETAIL_INDEX_IN_DETAILS].trim();
        if (detailToStore.equals("null")) {
            throw new ForbiddenDetailException();
        }
        indexToStore = getIndexToStore(flag);
        contactDetails[indexToStore] = detailToStore;
    }



    private int getIndexToStore(String flag) throws InvalidFlagException {
        int indexToStore;
        switch (flag) {
        case NAME_FLAG:
            indexToStore = DetailType.NAME.getIndex();
            break;
        case GITHUB_FLAG:
            indexToStore = DetailType.GITHUB.getIndex();
            break;
        case TELEGRAM_FLAG:
            indexToStore = DetailType.TELEGRAM.getIndex();
            break;
        case TWITTER_FLAG:
            indexToStore = DetailType.TWITTER.getIndex();
            break;
        case EMAIL_FLAG:
            indexToStore = DetailType.EMAIL.getIndex();
            break;
        case LINKEDIN_FLAG:
            indexToStore = DetailType.LINKEDIN.getIndex();
            break;
        default:
            throw new InvalidFlagException();
        }
        return indexToStore;
    }
}
