package terminus.parser;

import terminus.command.BackCommand;
import terminus.command.module.AddModuleCommand;
import terminus.command.module.DeleteModuleCommand;
import terminus.command.module.ViewModuleCommand;
import terminus.common.CommonFormat;
import terminus.module.ModuleManager;

public class ModuleCommandParser extends CommandParser {

    private static final ModuleCommandParser MODULE_PARSER = new ModuleCommandParser();

    /**
     * Initializes the commandMap. Adds some default commands to it.
     */
    public ModuleCommandParser() {
        super("module");
    }

    public static ModuleCommandParser getInstance() {
        ModuleCommandParser parser = MODULE_PARSER;
        parser.addCommand("add", new AddModuleCommand());
        parser.addCommand("view", new ViewModuleCommand());
        parser.addCommand("delete", new DeleteModuleCommand());
        parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
        return parser;
    }

    @Override
    public String getWorkspaceBanner(ModuleManager moduleManager) {
        return String.format("You have %d modules", moduleManager.getAllModules().length);
    }
}
