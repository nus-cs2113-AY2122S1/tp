package seedu.cardli.parser.deck;

import seedu.cardli.parser.CommandArgumentParser;
import seedu.cardli.parser.Parser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCardParser implements CommandArgumentParser {

    private Logger logger;

    public DeleteCardParser() {
        this.logger = Logger.getLogger(Parser.class.getName());
        logger.setLevel(Level.WARNING);
    }

    @Override
    public String[] parseArguments(String arguments) {
        String[] parameters = new String[1];
        String deckName = arguments;
        parameters[0] = deckName;
        return parameters;
    }
}
