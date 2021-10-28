package taa.command;

//@@author leyondlee
import taa.Taa;
import taa.Ui;
import taa.exception.TaaException;
import taa.module.ModuleList;
import taa.storage.Storage;
import taa.util.Util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArchiveCommand extends Command {
    private static final String KEY_FILENAME = "f";
    private static final String[] ARCHIVE_ARGUMENT_KEYS = {KEY_FILENAME};

    private static final String ARCHIVE_FOLDER_NAME = "archive";
    private static final String ARCHIVE_FILENAME_FORMAT = "taa_archive_%s.json";
    private static final String ARCHIVE_FILENAME_DATETIME_FORMAT = "yyyyMMdd'T'HHmmss_SSS";

    private static final String MESSAGE_NO_DATA = "There is no data to archive.";

    private static final String MESSAGE_FORMAT_ARCHIVE_USAGE = "%s [%s/<FILENAME>]";
    private static final String MESSAGE_FORMAT_ARCHIVE_SAVED = "Archive saved:\n  %s";
    private static final String MESSAGE_FORMAT_CONFIRMATION_PROMPT = "Do you want to remove all data?\n"
        + "WARNING: This action cannot be reversed.\n"
        + "Type [%s] to continue: ";

    private static final String CONFIRM_VALUE = "yes";

    public ArchiveCommand(String argument) {
        super(argument, ARCHIVE_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (!argument.isEmpty() && !argumentMap.containsKey(KEY_FILENAME)) {
            throw new TaaException(getUsageMessage());
        }
    }

    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        if (moduleList.getSize() <= 0) {
            throw new TaaException(MESSAGE_NO_DATA);
        }

        String filename;
        if (argumentMap.containsKey(KEY_FILENAME)) {
            filename = argumentMap.get(KEY_FILENAME);

            if (Util.isFolder(filename)) {
                Path filePath = Paths.get(filename, getDefaultArchiveFilename());
                filename = filePath.toString();
            }
        } else {
            Path filePath = Paths.get(Taa.DATA_FOLDER, ARCHIVE_FOLDER_NAME, getDefaultArchiveFilename());
            filename = filePath.toString();
        }

        Storage archiveStorage = new Storage(filename);
        archiveStorage.save(moduleList);

        ui.printBorder();

        String savedMessage = String.format(MESSAGE_FORMAT_ARCHIVE_SAVED, Util.getAbsolutePath(filename))
            + "\n"
            + "\n"
            + String.format(MESSAGE_FORMAT_CONFIRMATION_PROMPT, CONFIRM_VALUE);
        ui.printMessage(savedMessage, false);

        String userInput = ui.getUserInput(PROMPT_WITHIN_COMMAND, true);
        String message;
        if (userInput.equals(CONFIRM_VALUE)) {
            moduleList.deleteAllModules();
            storage.save(moduleList);

            message = MESSAGE_DATA_REMOVED;
        } else {
            message = MESSAGE_ABORT;
        }

        ui.printMessage(String.format("\n%s", message), false);
        ui.printBorder();
    }

    private String getDefaultArchiveFilename() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ARCHIVE_FILENAME_DATETIME_FORMAT);
        String dateTimeStr = simpleDateFormat.format(new Date());

        return String.format(ARCHIVE_FILENAME_FORMAT, dateTimeStr);
    }

    @Override
    protected String getUsage() {
        return String.format(MESSAGE_FORMAT_ARCHIVE_USAGE, COMMAND_ARCHIVE, KEY_FILENAME);
    }
}
