package terminus.parser;

import terminus.command.BackCommand;
import terminus.command.module.AddModuleCommand;
import terminus.command.module.DeleteModuleCommand;
import terminus.command.module.ViewModuleCommand;
import terminus.common.CommonFormat;
import terminus.module.ModuleManager;

public class ModuleCommandParser extends CommandParser {

    private static ModuleCommandParser parser;

    /**
     * Initializes the commandMap. Adds some default commands to it.
     */
    public ModuleCommandParser() {
        super(CommonFormat.COMMAND_MODULE);
    }

    public static ModuleCommandParser getInstance() {
        if (parser == null) {
            parser = new ModuleCommandParser();
            parser.addCommand(CommonFormat.COMMAND_ADD, new AddModuleCommand());
            parser.addCommand(CommonFormat.COMMAND_VIEW, new ViewModuleCommand());
            parser.addCommand(CommonFormat.COMMAND_DELETE, new DeleteModuleCommand());
            parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
        }
        return parser;
    }

    @Override
    public String getWorkspaceBanner(ModuleManager moduleManager) {
        return String.format("You have %d modules", moduleManager.getAllModules().length);
    }
}
