package terminus.parser;

import terminus.command.GoCommand;
import terminus.command.ModuleCommand;
import terminus.common.Messages;
import terminus.module.ModuleManager;
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
        return parser;
    }

    @Override
    public String getWorkspaceBanner(ModuleManager moduleManager) {
        return Messages.MAIN_BANNER;
    }
}
