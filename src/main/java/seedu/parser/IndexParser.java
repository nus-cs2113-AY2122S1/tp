package seedu.parser;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.exception.MissingArgException;


public class IndexParser {
    private static final int ISOLATE_COMD_WORD = 2;

    public static int getIndexFromInput(String userInput) throws NumberFormatException, MissingArgException {
        String[] destructuredInputs = userInput.split(" ", ISOLATE_COMD_WORD);
        if (destructuredInputs.length == 1) {
            throw new MissingArgException();
        }
        int index = Integer.parseInt(destructuredInputs[1].trim());
        return index;
    }

    public static Contact getContactFromIndex(int index, ContactList contactList) throws IndexOutOfBoundsException {
        Contact contact = contactList.getContactAtIndex(index);
        return contact;
    }
}
