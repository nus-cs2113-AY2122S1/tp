package seplanner.log;

import seplanner.constants.Constants;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    private static final Logger LOGGER = Logger.getLogger(Constants.LOGGER_NAME);
    private static final String FILE_PATH = "log/Logs.log";
    private static final File FILE = new File(FILE_PATH);

    public static void setupLogger() {
        LogManager.getLogManager().reset();
        try {
            FILE.getParentFile().mkdirs();
            FileHandler fileHandler = new FileHandler("log/Logs.log");
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            LOGGER.addHandler(fileHandler);
        } catch (java.io.IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to setup LOGGER");
        }
    }
}
