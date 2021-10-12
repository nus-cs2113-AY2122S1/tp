import gordon.exception.GordonException;
import gordon.util.Parser;
import gordon.kitchen.Cookbook;
import gordon.util.Storage;

import java.util.logging.Logger;
import java.util.logging.Level;

public class Gordon {
    Parser parser;
    Cookbook mainCookbook;
    Storage storage;
    static Logger logger;

    public Gordon() {
        parser = new Parser();
        mainCookbook = new Cookbook();
        storage = new Storage(mainCookbook);
        logger = Logger.getLogger(GordonException.loggerName);
        logger.setLevel(Level.SEVERE);
    }

    public void run() {
        assert (parser != null && mainCookbook != null);
        logger.log(Level.INFO, "Processing start.");
        parser.parseMaster(mainCookbook, storage);
        logger.log(Level.INFO, "Processing end.");
    }

    public static void main(String[] args) {
        new Gordon().run();
    }
}
