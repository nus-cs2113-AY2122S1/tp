package seedu.duke.storage;

import java.util.logging.Logger;

/**
 * Utilities for the various Storages to inherit.
 */
public abstract class StorageUtils {

    protected static final Logger logger = Logger.getLogger(StorageUtils.class.getName());

    protected String filePath;
    protected String fileName;

    protected String getFileName(String path) {
        return path.split("/")[2];
    }
}
