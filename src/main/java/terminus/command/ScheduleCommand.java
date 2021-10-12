package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.parser.LinkCommandParser;

/**
 * ScheduleCommand class to manage commands inside the Schedule workspace.
 */
public class ScheduleCommand extends WorkspaceCommand {

    public ScheduleCommand() {
        super(LinkCommandParser.getInstance());
    }

    /**
     * Returns the keyword for schedule-related commands.
     *
     * @return The string containing the keyword for schedule-related commands.
     */
    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_SCHEDULE;
    }

    /**
     * Returns the description for the command.
     *
     * @return The string containing the description for this command.
     */
    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_SCHEDULE;
    }

}
