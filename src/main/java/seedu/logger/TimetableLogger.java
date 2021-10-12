package seedu.logger;

import java.io.IOException;
import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TimetableLogger {

    private static final Logger logger = Logger.getLogger(TimetableLogger.class.getName());
    private static final String LOG_PATH = "./logs/logs.log";
    private static final String FILE_EXCEPTION = "Logging file not found";

    /**
     * Creates a timetable logger that logs changes to a file.
     */
    public TimetableLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        setupConsoleHandler();
        try {
            setupFileHandler();
        } catch (IOException e) {
            e.printStackTrace();
            logger.severe(FILE_EXCEPTION);
        }
    }

    /**
     * Logs a message.
     * @param level Level of log
     * @see Level
     * @param message message to be logged
     */
    public void log(Level level, String message) {
        logger.log(level,message);
    }

    private void setupConsoleHandler() {
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logger.addHandler(ch);
    }

    private void setupFileHandler() throws IOException {

        File file = new File(LOG_PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        FileHandler fh = new FileHandler(LOG_PATH, true);
        fh.setLevel(Level.FINE);
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
    }


}
