package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.ui.TextUi;

public class ListRecordsCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = ("Lists the budget, expenditures and loans recorded.\n"
            + "Parameters: m/MONTH c/CATEGORY\n"
            + "Note:\n"
            + " * If MONTH is not specified, records of all months will be listed.\n"
            + " * If CATEGORY is not specified, expenditures of all categories will be listed.");
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
    public void execute() {
        TextUi.showRecordsListView(allRecordList, month, isListAll, category);
    }
}
