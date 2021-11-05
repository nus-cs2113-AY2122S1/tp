package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.storage.ConverterToCsv;

import java.util.Hashtable;

public class CsvCommand extends Command {
    public static final String COMMAND_WORD = "csv";

    public void execute() {
        ConverterToCsv csvConversion = new ConverterToCsv();
        String directoryName = allRecordList.storageDirectory;

        directoryName = directoryName.split("\\.txt")[0] + ".csv";

        csvConversion.convertToCsvFile(allRecordList, directoryName);
    }
}
