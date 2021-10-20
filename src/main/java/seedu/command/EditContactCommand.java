package seedu.command;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.exception.InvalidFlagException;
import seedu.ui.TextUi;
import seedu.ui.ExceptionTextUi;
import seedu.ui.UserInputTextUi;

import java.util.ArrayList;

public class EditContactCommand extends Command {
    public static final int NAME_INDEX = 0;
    public static final int GITHUB_INDEX = 1;
    public static final int LINKEDIN_INDEX = 2;
    public static final int TELEGRAM_INDEX = 3;
    public static final int TWITTER_INDEX = 4;
    public static final int EMAIL_INDEX = 5;
    String[] contactDetails;
    int contactIndex;

    public EditContactCommand(String[] contactDetails, int contactIndex) {
        this.contactDetails = contactDetails;
        this.contactIndex = contactIndex;
    }

    public void execute() {
        try {
            Contact preEditContact = duplicateContact(contactList.getContactAtIndex(contactIndex));
            Contact postEditContact = editTempContact(contactDetails,preEditContact);
            if (duplicateCatcher(postEditContact, contactList, contactIndex)) {
                TextUi.ignoreEditContact();
            } else {
                contactList.editContact(contactDetails, contactIndex);
                TextUi.editContactMessage(postEditContact);
            }
        } catch (IndexOutOfBoundsException | InvalidFlagException e) {
            ExceptionTextUi.invalidIndexMessage();
        }
    }
}
