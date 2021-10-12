package seedu.duke.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

public class LoggerManager {
    private static final String LOGGER_PATH = "log/programLog.log";
    private static final File FILE = new File(LOGGER_PATH);
    public static final Logger LOGGER = Logger.getLogger(LoggerManager.class.getName());
    public static FileHandler fileHandler;

    public static void loggerFileSetup() {
        LogManager.getLogManager().reset();
        try {
            FILE.getParentFile().mkdirs();
            fileHandler = new FileHandler(LOGGER_PATH);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, "Error in FilHandler", ioe);
        }
    }

    public static void setupLogger(Logger logger) {
        if (fileHandler != null) {
            logger.setLevel(Level.ALL);
            logger.addHandler(fileHandler);
            logger.info("Logger setup successful");
        }
    }

}
