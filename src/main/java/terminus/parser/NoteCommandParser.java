package terminus.parser;

import terminus.command.BackCommand;
import terminus.command.TestCommand;


public class NoteCommandParser extends CommandParser {

    public NoteCommandParser() {
        super("notes");
    }

    public static NoteCommandParser getInstance() {
        NoteCommandParser parser = new NoteCommandParser();
        parser.addCommand("test", new TestCommand());
        parser.addCommand("back", new BackCommand());
        return parser;
    }
}
