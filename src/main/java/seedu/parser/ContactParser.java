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

public abstract class ContactParser {
    public static final int CONTACT_PARAMS_START_INDEX = 1;
    public static final int NUMBER_OF_DETAILS = 2;
    public static final int NUMBER_OF_ADD_ARGS = 2;
    public static final int NUMBER_OF_EDIT_ARGS = 3;

    public static final String DETAIL_SEPARATOR = " -";
    public static final int FLAG_INDEX_IN_DETAILS = 0;
    public static final int DETAIL_INDEX_IN_DETAILS = 1;
    public static final int EDIT_USER_INDEX = 1;
    public static final int USER_INFO_INDEX = 2;

    public static final String NAME_FLAG = "n";
    public static final String GITHUB_FLAG = "g";

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


    public void checkRegex(String flag, String detailToParse)
            throws InvalidGithubUsernameException, InvalidNameException, InvalidFlagException,
            InvalidTelegramUsernameException, InvalidTwitterUsernameException,
            InvalidLinkedinUsernameException, InvalidEmailException {
        switch (flag) {
        case "n":
            checkNameRegex(detailToParse);
            break;
        case "g":
            checkGithubUsernameRegex(detailToParse);
            break;
        case "tele":
            checkTelegramUsernameRegex(detailToParse);
            break;
        case "twit":
            checkTwitterUsernameRegex(detailToParse);
            break;
        case "link":
            checkLinkedinUsernameRegex(detailToParse);
            break;
        case "email":
            checkEmailRegex(detailToParse);
            break;
        default:
            throw new InvalidFlagException();
        }
    }

    private void checkEmailRegex(String detailToParse) throws InvalidEmailException {
        //allow lowercase email ids
        String emailRegex = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        if (!detailToParse.matches(emailRegex)) {
            throw new InvalidEmailException();
        }
    }

    private void checkLinkedinUsernameRegex(String detailToParse) throws InvalidLinkedinUsernameException {
        //allows lowercase, numbers, underscore and hyphen. Length must be 3-100 characters
        String linkedinRegex = "^[a-z0-9-_]{3,100}$";
        if (!detailToParse.matches(linkedinRegex)) {
            throw new InvalidLinkedinUsernameException();
        }
    }

    private void checkTwitterUsernameRegex(String detailToParse) throws InvalidTwitterUsernameException {
        //allows lowercase, numbers and underscore. Length must be max 15 characters
        String twitterRegex = "^[a-z0-9_]{1,15}$";
        if (!detailToParse.matches(twitterRegex)) {
            throw new InvalidTwitterUsernameException();
        }
    }

    private void checkTelegramUsernameRegex(String detailToParse) throws InvalidTelegramUsernameException {
        //allows uppercase, lowercase, numbers and underscore. Length must be atleast 5 characters
        String telegramRegex = "^[a-zA-Z0-9_]{5,}$";
        if (!detailToParse.matches(telegramRegex)) {
            throw new InvalidTelegramUsernameException();
        }
    }

    private void checkGithubUsernameRegex(String detailToParse) throws InvalidGithubUsernameException {
        //Github username may only contain alphanumeric characters or hyphens.
        //Github username cannot have multiple consecutive hyphens.
        //Github username cannot begin or end with a hyphen.
        //Maximum is 39 characters.
        String githubUsernameRegex = "^[a-z\\d](?:[a-z\\d]|-(?=[a-z\\d])){0,38}$";
        if (!detailToParse.matches(githubUsernameRegex)) {
            throw new InvalidGithubUsernameException();
        }
    }

    private void checkNameRegex(String detailToParse) throws InvalidNameException {
        //only letters and spaces allowed
        String nameRegex = "^[ A-Za-z]+$";
        if (!detailToParse.matches(nameRegex)) {
            throw new InvalidNameException();
        }
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
        default:
            throw new InvalidFlagException();
        }
        return indexToStore;
    }
}
