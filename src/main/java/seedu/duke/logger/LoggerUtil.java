package seedu.duke.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {
    public static ConsoleHandler ch;
    public static FileHandler fh;
    private static final String LOGGER_PATH = "log/programLog.log";
    private static final File FILE = new File(LOGGER_PATH);
    private static final Logger LOGGER = Logger.getLogger(LoggerUtil.class.getName());

    public static void loggerUtilSetup() {
        LogManager.getLogManager().reset();
        try {
            assert FILE != null;
            FILE.getParentFile().mkdirs();
            fh = new FileHandler(LOGGER_PATH);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.FINE);
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE,"Error in FilHandler", ioe);
        }
    }

    public static void setupLogger(Logger logger) {
        assert FILE.exists();
        if (fh != null) {
            logger.setLevel(Level.ALL);
            logger.addHandler(fh);
            logger.info("Logger setup successful");
        }
    }

}