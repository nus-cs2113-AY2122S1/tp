package seedu.budgettracker.logic.commands;

import seedu.budgettracker.search.FindBudget;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public String keyword;

    public FindCommand(String commandParams) {
        this.keyword = commandParams;
    }

    public void execute(boolean isLoadingStorage) {
        FindBudget.findExpenditure(allRecordList, keyword);
    }
}
