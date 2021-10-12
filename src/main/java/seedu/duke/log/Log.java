package seedu.duke.log;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    private static final Level DEFAULT_CONSOLE_SEVERITY = Level.SEVERE;
    private static final Level DEFAULT_LOG_FILE_SEVERITY = Level.FINE;
    private static final String LOG_FILE_NAME = "out.log";
    private static final String IOEXCEPTION_MESSAGE = "Failed to initialise log file.";


    public static Logger getLogger(Class classToLog) {
        Logger logger = Logger.getLogger(classToLog.getName());
        logger.setLevel(DEFAULT_LOG_FILE_SEVERITY);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(DEFAULT_CONSOLE_SEVERITY);

        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_NAME);
            fileHandler.setLevel(DEFAULT_LOG_FILE_SEVERITY);
        } catch (IOException ioe) {
            logger.severe(IOEXCEPTION_MESSAGE);
        }
        return logger;
    }

}
