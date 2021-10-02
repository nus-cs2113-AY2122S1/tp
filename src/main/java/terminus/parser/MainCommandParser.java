package terminus.parser;

import terminus.command.NotesCommand;

public class MainCommandParser extends CommandParser {

    public MainCommandParser() {
        super("");
    }

    public static MainCommandParser getInstance () {
        MainCommandParser parser = new MainCommandParser();
        parser.commandMap.put("notes",new NotesCommand());
//        parser.commandMap.put("schedules");
        return parser;
    }
}
