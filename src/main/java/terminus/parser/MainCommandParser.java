package terminus.parser;

import terminus.command.GoCommand;
import terminus.command.ModuleCommand;
import terminus.common.Messages;
import terminus.module.NusModule;

public class MainCommandParser extends CommandParser {

    private static final MainCommandParser PARSER = new MainCommandParser();

    public MainCommandParser() {
        super("");
    }

    public static MainCommandParser getInstance() {
        MainCommandParser parser = PARSER;
        parser.addCommand("module", new ModuleCommand());
        parser.addCommand("go", new GoCommand());
//        parser.addCommand(CommonFormat.COMMAND_NOTE, new NotesCommand());
//        parser.addCommand(CommonFormat.COMMAND_SCHEDULE, new ScheduleCommand());
        return parser;
    }

    @Override
    public String getWorkspaceBanner(NusModule module) {
        return Messages.MAIN_BANNER;
    }
}
