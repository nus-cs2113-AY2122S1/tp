package seedu.foodorama.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;



/**
 * Utility class to set up logger with the required configurations for this project.
 *
 * @author Dniv-ra
 */
public class LoggerManager {
    private static final String LOGGER_PATH = "log/programLog.log";
    private static final File FILE = new File(LOGGER_PATH);
    public static final Logger LOGGER = Logger.getLogger(LoggerManager.class.getName());
    public static FileHandler fileHandler;

    /**
     * Creates the FileHandler object which is going to be used for all loggers in this project.
     * This method should only be called once at the start of the main method.
     *
     * @author Dniv-ra
     */
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

    /**
     * Sets up logger object by setting its level to ALL and attaching the common FileHandler.
     * This method is to be called for every new logger to be set up in each class.
     * @param logger Logger object to be set up
     *
     * @author Dniv-ra
     */
    public static void setupLogger(Logger logger) {
        if (fileHandler != null) {
            logger.setLevel(Level.ALL);
            logger.addHandler(fileHandler);
            logger.info("Logger setup successful");
        }
    }

}
