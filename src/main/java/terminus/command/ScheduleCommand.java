package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.parser.LinkCommandParser;

public class ScheduleCommand extends WorkspaceCommand {

    public ScheduleCommand() {
        super(LinkCommandParser.getInstance());
    }

    @Override
    public String getFormat() {
        return CommonFormat.COMMAND_SCHEDULE;
    }

    @Override
    public String getHelpMessage() {
        return Messages.MESSAGE_COMMAND_SCHEDULE;
    }
}
