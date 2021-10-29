package seedu.duke.log;

import seedu.duke.constants.Constants;

import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

public class Log {
    private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    public static void setupLogger() {
        LogManager.getLogManager().reset();

        try {
            FileHandler fileHandler = new FileHandler("data/Logs.log");
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
        } catch (java.io.IOException e) {
            logger.log(Level.SEVERE, "Failed to setup logger");
        }
    }
}
