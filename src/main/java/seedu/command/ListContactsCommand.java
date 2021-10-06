package seedu.command;

import seedu.ui.TextUi;

public class ListContactsCommand extends Command {
    @Override
    public void execute() {
        int listSize = contactList.getListSize();

        TextUi.viewContactsListMessage(listSize);
        for (int i = 0; i < listSize; i++) {
            System.out.println(i + ". " + contactList.getContactAtIndex(i).getName());
        }
        TextUi.printBottomLine();
    }
}
