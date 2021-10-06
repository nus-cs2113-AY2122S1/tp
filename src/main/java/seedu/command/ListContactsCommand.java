package seedu.command;

import seedu.ui.TextUi;

public class ListContactsCommand extends Command {
    @Override
    public void execute() {
        int listSize = contactList.getListSize();
        if (listSize == 0) {
            TextUi.viewContactsEmptyListMessage();
        } else {
            listAllContacts(listSize);
        }
    }

    private void listAllContacts(int listSize) {
        TextUi.viewContactsListMessage(listSize);
        for (int index = 0; index < listSize; index++) {
            TextUi.printContactWithIndex(index, getContactName(index));
        }
        TextUi.printBottomLine();
    }

    private String getContactName(int index) {
        return contactList.getContactAtIndex(index).getName();
    }
}
