package expiryeliminator.common;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogsCenter {
    //@@author bernardboey-reused
    // Referenced from
    // https://github.com/se-edu/addressbook-level3/blob/master/src/main/java/seedu/address/commons/core/LogsCenter.java
    private static final int MAX_FILE_COUNT = 5;
    private static final int MAX_FILE_SIZE_IN_BYTES = (int) (Math.pow(2, 20) * 5); // 5MB
    private static final String LOG_FILE = "expiryeliminator.log";

    /**
     * Returns a logger for the given class.
     *
     * @param clazz Class to create a logger for.
     * @return Logger for the given class.
     */
    public static <T> Logger getLogger(Class<T> clazz) {
        final Logger logger = Logger.getLogger(clazz.getName());
        // Prevent logs from being shown in the console.
        logger.setUseParentHandlers(false);
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE, MAX_FILE_SIZE_IN_BYTES, MAX_FILE_COUNT, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.FINER);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.warning("Error adding file handler for logger.");
        }
        return logger;
    }
    //@@author
}
