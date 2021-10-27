package seedu.duke.commands;

import seedu.duke.data.records.Category;
import seedu.duke.ui.TextUi;

public class ListRecordsCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_DESC = "Displays all records as a list with index numbers.";
    public static final String MESSAGE_USAGE = "list m/MONTH";
    public static int month;
    public static boolean isListAll = false;
    public static Category category = Category.ALL;

    public ListRecordsCommand(int listOption, Category category) {
        this.month = listOption;
        this.isListAll = false;
        this.category = category;
    }

    public ListRecordsCommand(Category category) {
        isListAll = true;
        this.category = category;
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        TextUi.showRecordsListView(allRecordList, month, isListAll, category);
    }
}
