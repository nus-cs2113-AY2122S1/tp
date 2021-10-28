package taa.logger;

//@@author leyondlee
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaaLogger {
    private static final String LOG_FILENAME = "taa.log";

    private static final String MESSAGE_UNABLE_TO_OPEN = "Unable to open log file - %s";

    public static final TaaLogger LOGGER = new TaaLogger();

    private final Logger logger;
    private boolean isInit;

    private TaaLogger() {
        this.logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        this.isInit = false;
    }

    private void init() {
        if (isInit) {
            return;
        }

        isInit = true;

        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler(LOG_FILENAME);
        } catch (IOException e) {
            fileHandler = null;
        }

        if (fileHandler == null) {
            logger.warning(String.format(MESSAGE_UNABLE_TO_OPEN, LOG_FILENAME));
        } else {
            logger.setUseParentHandlers(false);

            fileHandler.setFormatter(new TaaLoggerFormatter());
            logger.addHandler(fileHandler);
        }
    }

    public static void setEnabled(boolean isEnabled) {
        Logger logger = LOGGER.logger;

        if (isEnabled) {
            logger.setLevel(Level.INFO);
        } else {
            logger.setLevel(Level.OFF);
        }
    }

    public void logInfo(String message) {
        if (logger.getLevel() == Level.OFF) {
            return;
        }

        init();
        logger.log(Level.INFO, message);
    }

    public void logWarning(String message) {
        if (logger.getLevel() == Level.OFF) {
            return;
        }

        init();
        logger.log(Level.WARNING, message);
    }

    public void logSevere(String message) {
        if (logger.getLevel() == Level.OFF) {
            return;
        }

        init();
        logger.log(Level.SEVERE, message);
    }
}
