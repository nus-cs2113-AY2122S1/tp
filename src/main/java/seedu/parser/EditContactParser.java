package seedu.parser;

import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;

import java.util.Arrays;

public class EditContactParser extends ContactParser {
    @Override
    public String[] parseContactDetails(String userInput) throws InvalidFlagException {
        String[] contactDetails = new String[NUMBER_OF_DETAILS];
        Arrays.fill(contactDetails, null);
        String[] destructuredInputs = userInput.split(DETAIL_SEPARATOR);
        for (int i = CONTACT_PARAMS_START_INDEX; i < destructuredInputs.length; i++) {
            parseDetail(contactDetails, destructuredInputs[i]);
        }
        return contactDetails;
    }

    private void parseDetail(String[] contactDetails, String detail) throws InvalidFlagException {
        String[] destructuredDetails = detail.split(" ", NUMBER_OF_EDIT_DETAILS);
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
}
