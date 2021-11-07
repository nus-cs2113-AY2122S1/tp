package seedu.storage;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.exception.FileErrorException;

import java.io.FileWriter;
import java.io.IOException;

import static seedu.storage.Storage.SEPARATOR;

/**
 * This class facilitates the saving of the user's contact list to their local storage file.
 */
public class ContactsEncoder {
    //@@author lezongmun
    public void savePersonalContact(String contactFilePath, Contact personalContact) throws FileErrorException {
        try {
            FileWriter fileWriter = new FileWriter(contactFilePath);
            String encodedContact = encodeContact(personalContact);
            fileWriter.write(encodedContact);
            fileWriter.close();
        } catch (IOException e) {
            throw new FileErrorException();
        }
    }

    //@@author marcusbory
    /**
     * Saves a specified ContactList to a specified file path.
     * @param contactFilePath Destination for the contact list to be saved
     * @param contactList Contact list to be saved
     * @throws FileErrorException If there an error writing to the file destination
     */
    public void saveContacts(String contactFilePath, ContactList contactList) throws FileErrorException {
        try {
            FileWriter fileWriter = new FileWriter(contactFilePath);
            for (int i = 0; i < contactList.getListSize(); i++) {
                Contact currentContact = contactList.getContactAtIndex(i);
                String encodedContact = encodeContact(currentContact);
                fileWriter.write(encodedContact);
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new FileErrorException();
        }
    }

    /**
     * Returns a String containing the encoded details of a contact.
     * @param contact Contact in the contact list
     * @return String Returns a string containing encoded details
     */
    private String encodeContact(Contact contact) {
        String stringifiedContact = contact.getName() + SEPARATOR + contact.getGithub() + SEPARATOR
                + contact.getLinkedin() + SEPARATOR + contact.getTelegram() + SEPARATOR
                + contact.getTwitter() + SEPARATOR + contact.getEmail() + "\n";
        return stringifiedContact;
    }
}
