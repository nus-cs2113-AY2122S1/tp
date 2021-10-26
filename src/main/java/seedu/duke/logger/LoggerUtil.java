package seedu.duke.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//@@author IshaaanVyas

/**
 * Utility class to set up logger with the required configurations for this project.
 */
public class LoggerUtil {
    public static final Logger LOGGER = Logger.getLogger(LoggerUtil.class.getName());
    private static final String LOGGER_PATH = "log/programLog.log";
    private static final File FILE = new File(LOGGER_PATH);
    public static FileHandler fh;
    private static ConsoleHandler ch;

    /**
     * Creates the FileHandler object which is going to be used for all loggers in this project.
     * This method should only be called once at the start of the main GetJackd method.
     */
    public static void loggerUtilSetup() {
        LogManager.getLogManager().reset();
        ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGGER.addHandler(ch);
        try {
            FILE.getParentFile().mkdirs();
            fh = new FileHandler(LOGGER_PATH);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.FINE);
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, "Error in initializing FilHandler");
        }
    }

    /**
     * Sets up logger object by setting its level to ALL and attaching the common FileHandler.
     * This method is to be called for every new logger to be set up in each class.
     *
     * @param logger Logger object to be set up
     */
    public static void setupLogger(Logger logger) {
        if (fh != null) {
            logger.setLevel(Level.ALL);
            logger.addHandler(fh);
            logger.info("Logger setup successful");
        }
    }

}