package seedu.duke.commands;

import seedu.duke.search.FindBudget;
import seedu.duke.ui.TextUi;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public String keyword;

    public FindCommand(String commandParams) {
        this.keyword = commandParams;
    }

    public void execute(boolean isLoadingStorage) {
        FindBudget.findExpenditure(recordList, keyword);
    }
}
