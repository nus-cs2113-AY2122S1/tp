package terminus.parser;

import terminus.command.NotesCommand;
import terminus.command.SchedulesCommand;

public class MainCommandParser extends CommandParser {

    private static final MainCommandParser PARSER = new MainCommandParser();

    public MainCommandParser() {
        super("");
    }

    public static MainCommandParser getInstance() {
        MainCommandParser parser = PARSER;
        parser.addCommand("notes", new NotesCommand());
        parser.addCommand("schedules", new SchedulesCommand());
        return parser;
    }
}
