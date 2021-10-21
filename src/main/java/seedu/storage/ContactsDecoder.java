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

public class ContactsDecoder extends RegexParser {
    public ContactList readContacts(File contactFile, String contactFilePath) throws FileErrorException {
        ContactList updatedContactList = new ContactList();
        try {
            Scanner fileScanner = new Scanner(contactFile);
            int lineIndex = 1;
            while (fileScanner.hasNext()) {
                String contactText = fileScanner.nextLine();
                decodeContact(updatedContactList, contactText, lineIndex, contactFilePath);
                lineIndex++;
            }
        } catch (FileNotFoundException e) {
            throw new FileErrorException();
        }
        return updatedContactList;
    }

    public Contact readPersonalContact(File personalContactFile) throws FileErrorException {
        Contact personalContact = new Contact(null, null, null, null, null, null);
        try {
            Scanner fileScanner = new Scanner(personalContactFile);
            if (fileScanner.hasNext()) {
                String contactText = fileScanner.nextLine();
                personalContact = decodePersonalContact(contactText, personalContact);
            }
        } catch (FileNotFoundException e) {
            throw new FileErrorException();
        }
        return personalContact;
    }


    private Contact decodePersonalContact(String contactText, Contact contact) {
        String[] destructuredInputs = contactText.split(SEPARATOR);
        Contact personalContact = contact;
        try {
            String contactName = destructuredInputs[DetailType.NAME.getIndex()];
            String contactGithub = destructuredInputs[DetailType.GITHUB.getIndex()];
            String contactLinkedin = destructuredInputs[DetailType.LINKEDIN.getIndex()];
            String contactTelegram = destructuredInputs[DetailType.TELEGRAM.getIndex()];
            String contactTwitter = destructuredInputs[DetailType.TWITTER.getIndex()];
            String contactEmail = destructuredInputs[DetailType.EMAIL.getIndex()];
            personalContact = new Contact(contactName, contactGithub, contactLinkedin, contactTelegram,
                    contactTwitter, contactEmail);
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.corruptPersonalContactMessage();
        }
        return personalContact;
    }

    private void decodeContact(ContactList contactList, String contactText, int lineIndex, String contactFilePath) {
        // Add the decoded details into the contact list
        try {
            String[] compiledDetails = decodeDetails(contactText);
            String contactName = compiledDetails[DetailType.NAME.getIndex()];
            String contactGithub = compiledDetails[DetailType.GITHUB.getIndex()];
            String contactLinkedin = compiledDetails[DetailType.LINKEDIN.getIndex()];
            String contactTelegram = compiledDetails[DetailType.TELEGRAM.getIndex()];
            String contactTwitter = compiledDetails[DetailType.TWITTER.getIndex()];
            String contactEmail = compiledDetails[DetailType.EMAIL.getIndex()];
            Contact newContact = new Contact(contactName, contactGithub, contactLinkedin, contactTelegram,
                    contactTwitter, contactEmail);
            contactList.addContact(newContact);
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.corruptLineMessage(contactText);
        }
    }

        String[] destructuredInputs = contactText.split(SEPARATOR);
        String[] compiledDetails = new String[NUMBER_OF_FIELDS];
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            if (destructuredInputs[i].equals("null")) {
                compiledDetails[i] = null;
            } else {
                compiledDetails[i] = destructuredInputs[i];
            }
        }
        assert destructuredInputs.length == compiledDetails.length;
        return compiledDetails;
    }
}
