package seedu.duke.parser;

import seedu.duke.commands.AdduniCommand;

import java.text.ParseException;

public class AdduniCommandParser implements Parser<AdduniCommand> {

    @Override
    public AdduniCommand parse(String arguments) throws ParseException {
        String university = arguments.trim();
        if (university.length() == 0) {
            throw new ParseException("no university given", 1);
        }
        return new AdduniCommand(university);
    }
}
