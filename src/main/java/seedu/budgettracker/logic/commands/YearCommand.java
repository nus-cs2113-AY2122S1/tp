package seedu.budgettracker.logic.commands;

import seedu.budgettracker.storage.Storage;

public class YearCommand extends Command {
    public static final String COMMAND_WORD = "year";
    private final String recordListDirectory;
    private final int year;

    public YearCommand(String directoryOfRecordList, int year) {
        this.recordListDirectory = directoryOfRecordList;
        this.year = year;
    }

    @Override
    public void execute() {
        Storage budgetStorage = new Storage();
        budgetStorage.makeStorageTextFile(recordListDirectory);
        allRecordList.clearAll();
        allRecordList.setYear(year);
        allRecordList.storageDirectory = budgetStorage.loadStorage(allRecordList, recordListDirectory);
    }
}
