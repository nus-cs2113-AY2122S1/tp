package seedu.parser;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.exception.MissingArgException;


public class IndexParser {
    private static final int COMD_WORD_LENGTH = 1;
    private static final int INDEX_POSITION = 1;


    public static int getIndexFromInput(String userInput, String command)
            throws NumberFormatException, MissingArgException {
        String inputWithoutDetails = userInput;

        String[] destructuredInputs = inputWithoutDetails.split(" ");
        if (destructuredInputs.length == COMD_WORD_LENGTH) {
            throw new MissingArgException();
        }
        // throws NumberFormatExcept if not integer
        int index = Integer.parseInt(destructuredInputs[INDEX_POSITION].trim());
        return index;
    }

    public static Contact getContactFromIndex(int index, ContactList contactList) throws IndexOutOfBoundsException {
        Contact contact = contactList.getContactAtIndex(index);
        return contact;
    }
}
