//@@author marcusbory
package seedu.storage;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.contact.DetailType;
import seedu.exception.FileErrorException;
import seedu.exception.InvalidEmailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.InvalidGithubUsernameException;
import seedu.exception.InvalidLinkedinUsernameException;
import seedu.exception.InvalidNameException;
import seedu.exception.InvalidTelegramUsernameException;
import seedu.exception.InvalidTwitterUsernameException;
import seedu.parser.AddPersonalContactParser;
import seedu.parser.RegexParser;
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

    //@@author lezongmun
    public Contact readPersonalContact(File personalContactFile)
            throws FileErrorException {
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

    //@author lezongmun
    private Contact decodePersonalContact(String contactText, Contact contact) {
        Contact personalContact = contact;
        try {
            String[] compiledDetails = decodeDetails(contactText);
            String contactName = compiledDetails[DetailType.NAME.getIndex()];
            String contactGithub = compiledDetails[DetailType.GITHUB.getIndex()];
            String contactLinkedin = compiledDetails[DetailType.LINKEDIN.getIndex()];
            String contactTelegram = compiledDetails[DetailType.TELEGRAM.getIndex()];
            String contactTwitter = compiledDetails[DetailType.TWITTER.getIndex()];
            String contactEmail = compiledDetails[DetailType.EMAIL.getIndex()];
            personalContact = new Contact(contactName, contactGithub, contactLinkedin, contactTelegram,
                    contactTwitter, contactEmail);
        } catch (IndexOutOfBoundsException e) {
            ExceptionTextUi.corruptPersonalContactMessage();
            AddPersonalContactParser addPersonalContactParser = new AddPersonalContactParser();
            addPersonalContactParser.recallPersonalDetails();
            return addPersonalContactParser.getPersonalContact();
        } catch (InvalidFlagException e) {
            assert false; // Control should not arrive here since flags are predetermined by FLAG_SEQUENCE
            ExceptionTextUi.invalidFlagMessage();
        } catch (InvalidGithubUsernameException | InvalidNameException | InvalidTelegramUsernameException
                | InvalidLinkedinUsernameException | InvalidTwitterUsernameException | InvalidEmailException e) {
            ExceptionTextUi.invalidPersonalContactFileMessage();
            AddPersonalContactParser addPersonalContactParser = new AddPersonalContactParser();
            addPersonalContactParser.recallPersonalDetails();
            return addPersonalContactParser.getPersonalContact();
        }
        return personalContact;
    }

    //@@author marcusbory
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
            ExceptionTextUi.corruptLineMessage(contactText, lineIndex, contactFilePath);
        } catch (InvalidFlagException e) {
            assert false; // Control should not arrive here since flags are predetermined by FLAG_SEQUENCE
            ExceptionTextUi.invalidFlagMessage();
        } catch (InvalidGithubUsernameException | InvalidNameException | InvalidTelegramUsernameException
                | InvalidLinkedinUsernameException | InvalidTwitterUsernameException | InvalidEmailException e) {
            handleInvalidNames(e, lineIndex, contactFilePath);
        }
    }

    private void handleInvalidNames(Exception e, int lineIndex, String contactFilePath) {
        ExceptionTextUi.invalidLoadedLineMessage(lineIndex, contactFilePath);
        if (e instanceof InvalidNameException) {
            ExceptionTextUi.invalidNameInput();
        } else if (e instanceof InvalidGithubUsernameException) {
            ExceptionTextUi.invalidGithubUsernameInput();
        } else if (e instanceof InvalidEmailException) {
            ExceptionTextUi.invalidEmailInput();
        } else if (e instanceof InvalidTelegramUsernameException) {
            ExceptionTextUi.invalidTelegramUsernameInput();
        } else if (e instanceof InvalidTwitterUsernameException) {
            ExceptionTextUi.invalidTelegramUsernameInput();
        } else if (e instanceof InvalidLinkedinUsernameException) {
            ExceptionTextUi.invalidLinkedinUsernameInput();
        }
    }

    private String[] decodeDetails(String contactText) throws IndexOutOfBoundsException,
            InvalidGithubUsernameException, InvalidNameException, InvalidFlagException,
            InvalidTelegramUsernameException, InvalidTwitterUsernameException,
            InvalidLinkedinUsernameException, InvalidEmailException {
        String[] destructuredInputs = contactText.split(SEPARATOR);
        String[] compiledDetails = new String[NUMBER_OF_FIELDS];
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            if (destructuredInputs[i].equals("null")) {
                compiledDetails[i] = null;
            } else {
                checkRegex(FLAG_SEQUENCE[i], destructuredInputs[i]);
                compiledDetails[i] = destructuredInputs[i];
            }
        }
        assert destructuredInputs.length == compiledDetails.length;
        return compiledDetails;
    }
}
