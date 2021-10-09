import gordon.exception.GordonException;
import gordon.util.Parser;
import gordon.kitchen.Cookbook;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Gordon {
    Parser parser;
    Cookbook mainCookbook;
    static Logger logger;

    public Gordon() {
        parser = new Parser();
        mainCookbook = new Cookbook();
        logger = Logger.getLogger(GordonException.loggerName);
    }

    public void run() {
        assert (parser != null && mainCookbook != null);
        logger.log(Level.INFO, "Processing start.");
        parser.parseMaster(mainCookbook);
        logger.log(Level.INFO, "Processing end.");
    }

    public static void main(String[] args) {
        new Gordon().run();
    }
}
