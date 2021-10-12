package seedu.parser;

import seedu.exception.ForbiddenDetailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.MissingArgException;
import seedu.exception.MissingDetailException;

public class EditContactParser extends ContactParser {
    public static final String BUFFER = " ";

    public String[] parseContactDetails(String userInput)
            throws InvalidFlagException, MissingDetailException, MissingArgException, ForbiddenDetailException {
        String[] inputDetails = userInput.split(" ", NUMBER_OF_EDIT_ARGS);
        if (inputDetails.length < NUMBER_OF_EDIT_ARGS) {
            //if arguments are missing e.g. edit 2
            throw new MissingArgException();
        }
        String[] contactDetails = new String[NUMBER_OF_FIELDS]; //initialise null array of strings
        //buffer is used to ensure first flag can be read
        String[] destructuredInputs = (BUFFER + inputDetails[USER_INFO_INDEX]).split(DETAIL_SEPARATOR);
        for (int i = CONTACT_PARAMS_START_INDEX; i < destructuredInputs.length; i++) {
            parseDetail(contactDetails, destructuredInputs[i]);
        }
        return contactDetails;
    }

    public int getIndex(String input) throws MissingArgException {
        String[] destructuredInputs = input.split(" ", NUMBER_OF_EDIT_ARGS);
        if (destructuredInputs.length < NUMBER_OF_EDIT_ARGS) {
            //if arguments are missing e.g. edit 2
            throw new MissingArgException();
        }
        assert (destructuredInputs.length == NUMBER_OF_EDIT_ARGS);
        return Integer.parseInt(destructuredInputs[EDIT_USER_INDEX]);
    }
}
