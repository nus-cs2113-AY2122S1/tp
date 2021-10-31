package terminus.parser;

import terminus.command.BackCommand;
import terminus.command.Command;
import terminus.command.content.NotesCommand;
import terminus.command.content.QuestionCommand;
import terminus.command.content.ScheduleCommand;
import terminus.common.CommonFormat;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;

public class ModuleWorkspaceCommandParser extends CommandParser {

    private static ModuleWorkspaceCommandParser parser;

    /**
     * Initializes the commandMap. Adds some default commands to it.
     */
    private ModuleWorkspaceCommandParser() {
        super("");
    }

    public static ModuleWorkspaceCommandParser getInstance() {
        if (parser == null) {
            parser = new ModuleWorkspaceCommandParser();
            parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
            parser.addCommand(CommonFormat.COMMAND_NOTE, new NotesCommand());
            parser.addCommand(CommonFormat.COMMAND_SCHEDULE, new ScheduleCommand());
            parser.addCommand(CommonFormat.COMMAND_QUESTION, new QuestionCommand());
        }
        return parser;
    }

    @Override
    public Command parseCommand(String command) throws InvalidCommandException, InvalidArgumentException {
        Command cmd = super.parseCommand(command);
        if (!(cmd instanceof BackCommand)) {
            cmd.setModuleName(getWorkspace().toUpperCase());
        }
        return cmd;
    }

    @Override
    public String getWorkspaceBanner(ModuleManager moduleManager) {
        return "Entering " + getWorkspace() + " workspace";
    }
}
