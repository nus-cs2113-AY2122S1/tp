package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class ListRecordsCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_DESC = "Displays all records as a list with index numbers.";

    @Override
    public void execute() {
        TextUi.showRecordsListView(recordList);
    }
}
