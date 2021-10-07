package seedu.parser;

import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;
import seedu.exception.MissingArgException;
import seedu.exception.MissingDetailException;

import java.util.Arrays;

public class EditContactParser extends ContactParser {
    public static final String BUFFER = " ";

    @Override
    public String[] parseContactDetails(String userInput)
            throws InvalidFlagException, MissingDetailException, MissingArgException {
        String[] inputDetails = userInput.split(" ", NUMBER_OF_EDIT_DETAILS);
        if (inputDetails.length < 3 || inputDetails[2].trim().equalsIgnoreCase("")) {
            //if arguments are missing e.g. edit 2
            throw new MissingArgException();
        }
        String[] contactDetails = new String[NUMBER_OF_DETAILS]; //initialise null array of strings
        //buffer is used to ensure first flag can be read
        String[] destructuredInputs = (BUFFER + inputDetails[USER_INFO_INDEX]).split(DETAIL_SEPARATOR);
        for (int i = CONTACT_PARAMS_START_INDEX; i < destructuredInputs.length; i++) {
            parseDetail(contactDetails, destructuredInputs[i]);
        }
        return contactDetails;
    }

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
            throws InvalidFlagException, MissingDetailException {
        String[] destructuredDetails = detail.split(" ", NUMBER_OF_EDIT_DETAILS);
        //for commands that specify a flag, but do not specify any argument for that flag
        //IndexOutOfBoundsException should not be thrown as the first if case will be true
        if (destructuredDetails.length == 1 || destructuredDetails[1].trim().equalsIgnoreCase("")) {
            throw new MissingDetailException();
        }
        int indexToStore;
        switch (destructuredDetails[FLAG_INDEX_IN_DETAILS]) {
        case NAME_FLAG:
            indexToStore = DetailType.NAME.getIndex();
            break;
        case GITHUB_FLAG:
            indexToStore = DetailType.GITHUB.getIndex();
            break;
        default:
            throw new InvalidFlagException();
        }
        contactDetails[indexToStore] = destructuredDetails[DETAIL_INDEX_IN_DETAILS];
    }

    public int getIndex(String input) throws MissingArgException {
        String[] destructuredInputs = input.split(" ", NUMBER_OF_EDIT_DETAILS);
        if (destructuredInputs.length < 3) {
            //if arguments are missing e.g. edit 2
            throw new MissingArgException();
        }
        return Integer.parseInt(destructuredInputs[EDIT_USER_INDEX]);
    }
}
