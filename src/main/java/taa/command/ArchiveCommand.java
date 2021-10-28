package taa.command;

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

    private static final String MESSAGE_FORMAT_ARCHIVE_USAGE = "%s [%s/<FILENAME>]";
    private static final String MESSAGE_FORMAT_ARCHIVE_SAVED = "Archive saved:\n  %s";

    public ArchiveCommand(String argument) {
        super(argument, ARCHIVE_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        // Nothing to check
    }

    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
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

        ui.printMessage(String.format(MESSAGE_FORMAT_ARCHIVE_SAVED, Util.getAbsolutePath(filename)));
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
