package seedu.cardli.parser.system;

import seedu.cardli.parser.CommandArgumentParser;

public class FindCardsParser implements CommandArgumentParser {

    public FindCardsParser() {
    }

    @Override
    public String[] parseArguments(String arguments) {
        String[] searchTerms = new String[1];
        searchTerms[0] = arguments.trim();
        return searchTerms;
    }
}
