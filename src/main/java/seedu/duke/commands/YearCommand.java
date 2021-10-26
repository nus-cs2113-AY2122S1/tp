package seedu.duke.commands;

import seedu.duke.storage.Storage;

public class YearCommand extends Command {
    public static final String COMMAND_WORD = "year";
    public static String recordListDirectory;

    public YearCommand(String directoryOfRecordList) {
        this.recordListDirectory = directoryOfRecordList;
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        Storage budgetStorage = new Storage();
        budgetStorage.makeStorageTextFile(recordListDirectory);
        recordList.clearAll();
        recordList.storageDirectory = budgetStorage.loadStorage(recordList, recordListDirectory);
    }
}
