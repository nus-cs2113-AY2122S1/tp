package seedu.duke.parser;

import seedu.duke.commands.SearchmapCommand;

import java.text.ParseException;

public class SearchmapCommandParser implements Parser<SearchmapCommand> {
    @Override
    public SearchmapCommand parse(String arguments) throws ParseException {
        String university = arguments.trim();
        if (university.length() == 0) {
            throw new ParseException("no description given", 1);
        }

        return new SearchmapCommand(university);
    }
}
