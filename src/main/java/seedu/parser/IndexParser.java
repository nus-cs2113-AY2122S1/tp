package seedu.parser;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.exception.MissingArgException;


public class IndexParser {
    private static final int COMD_WORD_LENGTH = 1;
    private static final int INDEX_POSITION = 1;
    private static final int SIGNIFICANT_INDEX_POSITION = 0;
    private static final int COMD_INDEX_LENGTH = 2;


    public static int getIndexFromInput(String userInput, String command)
            throws NumberFormatException, MissingArgException {
        String[] destructuredInputs = userInput.split(" ", COMD_INDEX_LENGTH);
        if (destructuredInputs.length <= COMD_WORD_LENGTH) {
            throw new MissingArgException();
        }
        String[] indexSplit = destructuredInputs[INDEX_POSITION].trim().split(" ");
        // throws NumberFormatExcept if not integer
        return Integer.parseInt(indexSplit[SIGNIFICANT_INDEX_POSITION].trim());

    }

    public static Contact getContactFromIndex(int index, ContactList contactList) throws IndexOutOfBoundsException {
        return contactList.getContactAtIndex(index);
    }
}
