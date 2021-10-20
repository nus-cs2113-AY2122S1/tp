package terminus.parser;

import terminus.command.GoCommand;
import terminus.command.module.ModuleCommand;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.module.ModuleManager;

public class MainCommandParser extends CommandParser {

    private static MainCommandParser parser;

    public MainCommandParser() {
        super("");
    }

    public static MainCommandParser getInstance() {
        if (parser == null) {
            parser = new MainCommandParser();
            parser.addCommand(CommonFormat.COMMAND_MODULE, new ModuleCommand());
            parser.addCommand(CommonFormat.COMMAND_GO, new GoCommand());
        }
        return parser;
    }

    @Override
    public String getWorkspaceBanner(ModuleManager moduleManager) {
        return Messages.MAIN_BANNER;
    }
}
