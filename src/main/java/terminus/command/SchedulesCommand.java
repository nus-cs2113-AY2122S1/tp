package terminus.command;

import terminus.parser.LinkCommandParser;

public class SchedulesCommand extends WorkspaceCommand {

    private String arguments;

    public SchedulesCommand() {
        super(LinkCommandParser.getInstance());
    }

    @Override
    public String getFormat() {
            return "schedules";
        }

    @Override
    public String getHelpMessage() {
            return "Move to schedules workspace.";
        }
}
