package terminus.parser;

import terminus.command.TestCommand;


public class NoteCommandParser extends CommandParser{

    public NoteCommandParser() {
        super("notes");
    }

    public static NoteCommandParser getInstance() {
        NoteCommandParser parser = new NoteCommandParser();
        parser.commandMap.put("test",new TestCommand());
        return parser;
    }
}
