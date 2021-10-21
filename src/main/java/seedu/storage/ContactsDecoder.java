package seedu.storage;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.contact.DetailType;
import seedu.contact.PersonalContact;
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

    public static PersonalContact readPersonalContact(File personalContactFile) throws FileErrorException {
        PersonalContact personalContact = new PersonalContact(null, null, null, null, null, null);
        try {
            Scanner fileScanner = new Scanner(personalContactFile);
            if (fileScanner.hasNext()) {
                String contactText = fileScanner.nextLine();
                personalContact = decodePersonalContact(personalContact, contactText);
            }
        } catch (FileNotFoundException e) {
            throw new FileErrorException();
        }
        return personalContact;
    }


    private static PersonalContact decodePersonalContact(PersonalContact contact, String contactText) {
        PersonalContact personalContact = contact;
        try {
            String[] compiledDetails = decodeDetails(contactText);
            String contactName = compiledDetails[DetailType.NAME.getIndex()];
            String contactGithub = compiledDetails[DetailType.GITHUB.getIndex()];
            String contactLinkedin = compiledDetails[DetailType.LINKEDIN.getIndex()];
            String contactTelegram = compiledDetails[DetailType.TELEGRAM.getIndex()];
            String contactTwitter = compiledDetails[DetailType.TWITTER.getIndex()];
            String contactEmail = compiledDetails[DetailType.EMAIL.getIndex()];
            personalContact = new PersonalContact(contactName, contactGithub, contactLinkedin, contactTelegram,
                    contactTwitter, contactEmail);
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.corruptLineMessage(contactText);
        }
        return personalContact;
    }

    private static void decodeContact(ContactList contactList, String contactText) {
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

    private static String[] decodeDetails(String contactText) throws IndexOutOfBoundsException {
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
