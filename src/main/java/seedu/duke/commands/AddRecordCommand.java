package seedu.duke.commands;

/**
 * Not used right now.
 */
public class AddRecordCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_DESC = "Adds a record to the budget list";

    private final String recordType;
    private final String recordParameters;

    public AddRecordCommand(String recordType, String recordParameters) {
        this.recordType = recordType;
        this.recordParameters = recordParameters;
    }

    public void execute(boolean isLoadingStorage) {

    }
}
