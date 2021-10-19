package terminus.parser;

import terminus.command.GoCommand;
import terminus.command.TimetableCommand;
import terminus.command.module.ModuleCommand;
import terminus.common.Messages;
import terminus.module.ModuleManager;

public class MainCommandParser extends CommandParser {

    private static final MainCommandParser PARSER = new MainCommandParser();

    public MainCommandParser() {
        super("");
    }

    public static MainCommandParser getInstance() {
        MainCommandParser parser = PARSER;
        parser.addCommand("module", new ModuleCommand());
        parser.addCommand("go", new GoCommand());
        parser.addCommand("timetable", new TimetableCommand());
        return parser;
    }

    @Override
    public String getWorkspaceBanner(ModuleManager moduleManager) {
        return Messages.MAIN_BANNER;
    }
}
