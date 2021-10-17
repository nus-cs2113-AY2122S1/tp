package terminus.parser;

import terminus.command.BackCommand;
import terminus.command.Command;
import terminus.command.NotesCommand;
import terminus.command.ScheduleCommand;
import terminus.common.CommonFormat;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;

public class ModuleWorkspaceCommandParser extends CommandParser {

    private static final ModuleWorkspaceCommandParser PARSER = new ModuleWorkspaceCommandParser();

    /**
     * Initializes the commandMap. Adds some default commands to it.
     */
    public ModuleWorkspaceCommandParser() {
        super("");
    }

    public static ModuleWorkspaceCommandParser getInstance() {
        ModuleWorkspaceCommandParser parser = new ModuleWorkspaceCommandParser();
        parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
        parser.addCommand(CommonFormat.COMMAND_NOTE, new NotesCommand());
        parser.addCommand(CommonFormat.COMMAND_SCHEDULE, new ScheduleCommand());
        return parser;
    }

    @Override
    public Command parseCommand(String command) throws InvalidCommandException, InvalidArgumentException {
        Command cmd = super.parseCommand(command);
        if (!(cmd instanceof BackCommand)) {
            cmd.setModuleName(getWorkspace());
        }
        return cmd;
    }

    @Override
    public String getWorkspaceBanner(ModuleManager moduleManager) {
        return "Entering " + getWorkspace() + " workspace";
    }
}
