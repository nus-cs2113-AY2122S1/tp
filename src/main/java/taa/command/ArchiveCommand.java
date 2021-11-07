package taa.command;

//@@author leyondlee

import taa.Taa;
import taa.Ui;
import taa.exception.TaaException;
import taa.teachingclass.ClassList;
import taa.storage.Storage;
import taa.util.Util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArchiveCommand extends Command {
    private static final String ARCHIVE_FOLDER_NAME = "archive";
    private static final String ARCHIVE_FILENAME_FORMAT = "taa_archive_%s.json";
    private static final String ARCHIVE_FILENAME_DATETIME_FORMAT = "yyyyMMdd'T'HHmmss_SSS";

    private static final String MESSAGE_NO_DATA = "There is no data to archive.";

    private static final String MESSAGE_FORMAT_ARCHIVE_SAVED = "Archive saved:\n  %s";
    private static final String MESSAGE_FORMAT_CONFIRMATION_PROMPT = "Do you want to remove all data?\n"
            + "WARNING: This action cannot be reversed.\n"
            + "Type [%s] to continue: ";

    private static final String CONFIRM_VALUE = "yes";

    public ArchiveCommand(String argument) {
        super(argument);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }
    }

    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        if (classList.getSize() <= 0) {
            throw new TaaException(MESSAGE_NO_DATA);
        }

        Path filePath = Paths.get(Taa.DATA_FOLDER, ARCHIVE_FOLDER_NAME, getDefaultArchiveFilename());
        String filename = filePath.toString();

        Storage archiveStorage = new Storage(filename);
        archiveStorage.save(classList);

        ui.printBorder();

        String savedMessage = String.format(MESSAGE_FORMAT_ARCHIVE_SAVED, Util.getAbsolutePath(filename));
        ui.printMessage(savedMessage, false);

        promptUserToRemoveData(classList, ui, storage);

        ui.printBorder();
    }

    private String getDefaultArchiveFilename() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ARCHIVE_FILENAME_DATETIME_FORMAT);
        String dateTimeStr = simpleDateFormat.format(new Date());

        return String.format(ARCHIVE_FILENAME_FORMAT, dateTimeStr);
    }

    private void promptUserToRemoveData(ClassList classList, Ui ui, Storage storage) throws TaaException {
        String promptMessage = "\n\n" + String.format(MESSAGE_FORMAT_CONFIRMATION_PROMPT, CONFIRM_VALUE);
        ui.printMessage(promptMessage, false);
        String userInput = ui.getUserInput(PROMPT_WITHIN_COMMAND, true);
        String message;
        if (userInput != null && userInput.equals(CONFIRM_VALUE)) {
            classList.deleteAllClasses();
            storage.save(classList);

            message = MESSAGE_DATA_REMOVED;
        } else {
            message = MESSAGE_ABORT;
        }
        ui.printMessage(String.format("\n%s", message), false);
    }

    @Override
    protected String getUsage() {
        return String.format(MESSAGE_FORMAT_GENERIC_USAGE, COMMAND_ARCHIVE);
    }
}
