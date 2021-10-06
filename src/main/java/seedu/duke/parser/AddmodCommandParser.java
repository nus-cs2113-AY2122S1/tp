package seedu.duke.parser;

import seedu.duke.commands.AddmodCommand;

import java.text.ParseException;

public class AddmodCommandParser implements Parser<AddmodCommand> {

    @Override
    public AddmodCommand parse(String arguments) throws ParseException {
        String module = arguments.trim();
        if (module.length() == 0) {
            throw new ParseException("no module give", 1);
        }
        return new AddmodCommand(module);
    }
}
