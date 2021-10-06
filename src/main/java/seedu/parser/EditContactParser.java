package seedu.parser;

import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;

import java.util.Arrays;

public class EditContactParser extends ContactParser {
    public static final String BUFFER = " ";

    @Override
    public String[] parseContactDetails(String userInput) throws InvalidFlagException {
        String[] contactDetails = new String[NUMBER_OF_DETAILS];
        Arrays.fill(contactDetails, null);
        String[] destructuredInputs = (BUFFER + userInput).split(DETAIL_SEPARATOR);
        for (int i = CONTACT_PARAMS_START_INDEX; i < destructuredInputs.length; i++) {
            parseDetail(contactDetails, destructuredInputs[i]);
        }
        return contactDetails;
    }
}
