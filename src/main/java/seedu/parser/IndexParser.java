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
        //split user input into 2 strings: command word string and index string
        String[] destructuredInputs = userInput.split(" ", COMD_INDEX_LENGTH);
        if (destructuredInputs.length <= COMD_WORD_LENGTH) {
            throw new MissingArgException();
        }
        assert destructuredInputs.length == 2;
        // split index string into words
        String[] indexSplit = destructuredInputs[INDEX_POSITION].trim().split(" ");
        // takes only the first word/ element as given user input, and throws NumberFormatExcept if it is not integer
        return Integer.parseInt(indexSplit[SIGNIFICANT_INDEX_POSITION].trim());
    }

    public static Contact getContactFromIndex(int index, ContactList contactList) throws IndexOutOfBoundsException {
        return contactList.getContactAtIndex(index);
    }
}
