package happybit.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class HaBitLogger {

    private final Logger logger = Logger.getLogger(HaBitLogger.class.getName());

    // Testing logger methods for now, will implement in v2.0
    public void main(String[] args) {

        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logger.addHandler(ch);

        logger.info("Testing");
        logger.warning("ERROR");
        logger.log(Level.SEVERE, "System down");

    }
}
