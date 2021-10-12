package seedu.duke.log;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    private static final Level DEFAULT_CONSOLE_SEVERITY = Level.SEVERE;
    private static final Level DEFAULT_LOG_FILE_SEVERITY = Level.FINE;
    private static final String LOG_FILE_NAME = "log.txt";
    private static final String LOG_FORMAT = "[%1$tF %1$tT] [%2$-7s]%n%3$s %n";
    private static final String IOEXCEPTION_MESSAGE = "Failed to initialise log file.";

    private static final int METHOD_STACKTRACE_POSITION = 3;

    private static HashMap<String, Logger> loggers = new HashMap<>();

    public static FileHandler getFileHandler() throws IOException {
        FileHandler fileHandler = new FileHandler(LOG_FILE_NAME, true);
        fileHandler.setLevel(DEFAULT_LOG_FILE_SEVERITY);
        return fileHandler;
    }

    private static Logger getLogger() {
        StackTraceElement classToLog = Thread.currentThread().getStackTrace()[METHOD_STACKTRACE_POSITION];
        String className = classToLog.getClassName();
        if (loggers.containsKey(className)) {
            return loggers.get(className);
        } else {
            Logger logger = Logger.getLogger(className);
            logger.setUseParentHandlers(false);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(DEFAULT_CONSOLE_SEVERITY);

            consoleHandler.setFormatter(getLogFormat());

            logger.addHandler(consoleHandler);

            loggers.put(className, logger);
            return logger;
        }
    }

    private static SimpleFormatter getLogFormat() {
        return new SimpleFormatter() {
            private static final String format = LOG_FORMAT;

            @Override
            public synchronized String format(
                LogRecord logRecord) {
                return String.format(format, new Date(logRecord.getMillis()),
                    logRecord.getLoggerName(), logRecord.getMessage());
            }
        };
    }

    public static void fine(String message) {
        Logger logger = getLogger();
        try {
            FileHandler fileHandler = getFileHandler();
            fileHandler.setFormatter(getLogFormat());
            logger.addHandler(fileHandler);
            logger.fine(message);
            fileHandler.close();
        } catch (IOException ioe) {
            logger.severe(IOEXCEPTION_MESSAGE);
        }
    }

    public static void finer(String message) {
        Logger logger = getLogger();
        try {
            FileHandler fileHandler = getFileHandler();
            fileHandler.setFormatter(getLogFormat());
            logger.addHandler(fileHandler);
            logger.finer(message);
            fileHandler.close();
        } catch (IOException ioe) {
            logger.severe(IOEXCEPTION_MESSAGE);
        }
    }

    public static void finest(String message) {
        Logger logger = getLogger();
        try {
            FileHandler fileHandler = getFileHandler();
            fileHandler.setFormatter(getLogFormat());
            logger.addHandler(fileHandler);
            logger.finest(message);
            fileHandler.close();
        } catch (IOException ioe) {
            logger.severe(IOEXCEPTION_MESSAGE);
        }
    }

    public static void info(String message) {
        Logger logger = getLogger();
        try {
            FileHandler fileHandler = getFileHandler();
            fileHandler.setFormatter(getLogFormat());
            logger.addHandler(fileHandler);
            logger.info(message);
            fileHandler.close();
        } catch (IOException ioe) {
            logger.severe(IOEXCEPTION_MESSAGE);
        }
    }

    public static void severe(String message) {
        Logger logger = getLogger();
        try {
            FileHandler fileHandler = getFileHandler();
            fileHandler.setFormatter(getLogFormat());
            logger.addHandler(fileHandler);
            logger.severe(message);
            fileHandler.close();
        } catch (IOException ioe) {
            logger.severe(IOEXCEPTION_MESSAGE);
        }
    }

    public static void warning(String message) {
        Logger logger = getLogger();
        try {
            FileHandler fileHandler = getFileHandler();
            fileHandler.setFormatter(getLogFormat());
            logger.addHandler(fileHandler);
            logger.warning(message);
            fileHandler.close();
        } catch (IOException ioe) {
            logger.severe(IOEXCEPTION_MESSAGE);
        }
    }
}
