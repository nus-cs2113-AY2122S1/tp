package terminus.common;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TerminusLogger {
    
    private static final Logger LOGGER = Logger.getLogger("TermiNUS");
    
    public static void initializeLogger() throws IOException {
        LogManager.getLogManager().reset();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.OFF);
        LOGGER.addHandler(consoleHandler);
        
        FileHandler fileHandler = new FileHandler("terminus.log");
        fileHandler.setLevel(Level.INFO);
        LOGGER.addHandler(fileHandler);
    }
    
    public static void debug(String message) {
        LOGGER.fine(message);
    }
    
    public static void verboseDebug(String message) {
        LOGGER.finer(message);
    }

    public static void veryVerboseDebug(String message) {
        LOGGER.finest(message);
    }
    
    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void warning(String message) {
        LOGGER.warning(message);
    }
    
    public static void warning(String message, Throwable throwable) {
        LOGGER.log(Level.WARNING, message, throwable);
    }
    
    public static void severe(String message) {
        LOGGER.severe(message);
    }
    
    public static void severe(String message, Throwable throwable) {
        LOGGER.log(Level.SEVERE, message, throwable);
    }
    
}
