package terminus.command;

import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.parser.LinkCommandParser;

public class LinkCommand extends WorkspaceCommand {

    private String arguments;

    public LinkCommand() {
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
