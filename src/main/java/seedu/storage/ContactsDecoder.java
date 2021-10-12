package seedu.storage;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.contact.DetailType;
import seedu.exception.FileErrorException;
import seedu.ui.ExceptionTextUi;

import static seedu.storage.Storage.SEPARATOR;
import static seedu.parser.ContactParser.NUMBER_OF_FIELDS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContactsDecoder {
    public static ContactList readContacts(File contactFile) throws FileErrorException {
        ContactList updatedContactList = new ContactList();
        try {
            Scanner fileScanner = new Scanner(contactFile);
            while (fileScanner.hasNext()) {
                String contactText = fileScanner.nextLine();
                decodeContact(updatedContactList, contactText);
            }
        } catch (FileNotFoundException e) {
            throw new FileErrorException();
        }
        return updatedContactList;
    }


    private static void decodeContact(ContactList contactList, String contactText) {
        String[] destructuredInputs = contactText.split(SEPARATOR);
        String[] compiledDetails = new String[NUMBER_OF_FIELDS];
        assert destructuredInputs.length > 0;
        decodeDetails(compiledDetails, destructuredInputs);
        // Add the decoded details into the contact list
        try {
            String contactName = destructuredInputs[DetailType.NAME.getIndex()];
            String contactGithub = destructuredInputs[DetailType.GITHUB.getIndex()];
            String contactLinkedin = destructuredInputs[DetailType.LINKEDIN.getIndex()];
            String contactTelegram = destructuredInputs[DetailType.TELEGRAM.getIndex()];
            String contactTwitter = destructuredInputs[DetailType.TWITTER.getIndex()];
            String contactEmail = destructuredInputs[DetailType.EMAIL.getIndex()];
            Contact newContact = new Contact(contactName, contactGithub, contactLinkedin, contactTelegram,
                    contactTwitter, contactEmail);
            contactList.addContact(newContact);
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.corruptLineMessage(contactText);
        }
    }

    private static void decodeDetails(String[] compiledDetails, String[] destructuredInputs) {
        if (compiledDetails.length != destructuredInputs.length) {
            return;
        }
        assert compiledDetails.length == NUMBER_OF_FIELDS;
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            compiledDetails[i] = destructuredInputs[i];
        }
    }
}
