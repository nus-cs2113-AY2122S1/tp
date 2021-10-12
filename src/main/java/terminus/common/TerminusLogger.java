package terminus.common;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TerminusLogger {
    
    private static final Logger LOGGER = Logger.getLogger("TermiNUS");

    /**
     * Initializes TerminusLogger.
     * 
     * @throws IOException When 'terminus.log' is unable to be written to.
     */
    public static void initializeLogger() throws IOException {
        LogManager.getLogManager().reset();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.OFF);
        LOGGER.addHandler(consoleHandler);
        
        FileHandler fileHandler = new FileHandler("terminus.log");
        fileHandler.setLevel(Level.INFO);
        LOGGER.addHandler(fileHandler);
    }

    /**
     * Writes a debug message to the Logger.
     * Equivalent to Level.FINE.
     * 
     * @param message The message to write to Logger. 
     */
    public static void debug(String message) {
        LOGGER.fine(message);
    }

    /**
     * Writes a verbose debug message to the Logger.
     * Equivalent to Level.FINER.
     *
     * @param message The message to write to Logger. 
     */
    public static void verboseDebug(String message) {
        LOGGER.finer(message);
    }
    
    /**
     * Writes a very verbose debug message to the Logger.
     * Mainly used for printing stack traces and non-important strings.
     * Equivalent to Level.FINEST.
     *
     * @param message The message to write to Logger. 
     */
    public static void veryVerboseDebug(String message) {
        LOGGER.finest(message);
    }
    
    /**
     * Writes an information message to the Logger.
     *
     * @param message The message to write to Logger. 
     */
    public static void info(String message) {
        LOGGER.info(message);
    }

    /**
     * Writes a warning message to the Logger.
     *
     * @param message The message to write to Logger. 
     */
    public static void warning(String message) {
        LOGGER.warning(message);
    }

    /**
     * Writes a warning message with a Throwable to the Logger.
     *
     * @param message The message to write to Logger.
     * @param throwable The Throwable to tag to the log message.
     */
    public static void warning(String message, Throwable throwable) {
        LOGGER.log(Level.WARNING, message, throwable);
    }
    
    /**
     * Writes a severe message to the Logger.
     *
     * @param message The message to write to Logger. 
     */
    public static void severe(String message) {
        LOGGER.severe(message);
    }

    /**
     * Writes a warning message with a Throwable to the Logger.
     *
     * @param message The message to write to Logger.
     * @param throwable The Throwable to tag to the log message.
     */
    public static void severe(String message, Throwable throwable) {
        LOGGER.log(Level.SEVERE, message, throwable);
    }
    
}
