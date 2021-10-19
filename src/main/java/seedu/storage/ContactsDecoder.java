package seedu.storage;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.contact.DetailType;
import seedu.exception.FileErrorException;
import seedu.ui.ExceptionTextUi;

import static seedu.storage.Storage.SEPARATOR;

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

    public static Contact readPersonalContact(File personalContactFile) throws FileErrorException {
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

    public void getPersonalContactDetails() {

    }

    private static Contact decodePersonalContact(String contactText, Contact contact) {
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
            ExceptionTextUi.corruptLineMessage(contactText);
        }
        return personalContact;
    }

    private static void decodeContact(ContactList contactList, String contactText) {
        String[] destructuredInputs = contactText.split(SEPARATOR);
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
