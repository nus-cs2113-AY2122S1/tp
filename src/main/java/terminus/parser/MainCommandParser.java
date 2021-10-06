package terminus.parser;

import terminus.command.NotesCommand;
import terminus.command.ScheduleCommand;
import terminus.common.CommonFormat;

public class MainCommandParser extends CommandParser {

    private static final MainCommandParser PARSER = new MainCommandParser();

    public MainCommandParser() {
        super("");
    }

    public static MainCommandParser getInstance() {
        MainCommandParser parser = PARSER;
        parser.addCommand(CommonFormat.COMMAND_NOTE, new NotesCommand());
        parser.addCommand(CommonFormat.COMMAND_SCHEDULE, new ScheduleCommand());
        return parser;
    }
}
