package terminus.parser;

import terminus.command.BackCommand;
import terminus.command.content.DeleteCommand;
import terminus.command.content.ViewCommand;
import terminus.command.content.link.AddLinkCommand;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.Link;
import terminus.module.ModuleManager;

/**
 * LinkCommandParser class to manage schedule-related commands.
 */
public class LinkCommandParser extends InnerModuleCommandParser {

    private static LinkCommandParser parser;

    private LinkCommandParser() {
        super(CommonFormat.COMMAND_SCHEDULE);
    }

    /**
     * Returns the command map for the schedule workspace.
     *
     * @return A LinkCommandParser object which contains the command map for the schedule workspace.
     */
    public static LinkCommandParser getInstance() {
        if (parser == null) {
            parser = new LinkCommandParser();
            parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
            parser.addCommand(CommonFormat.COMMAND_ADD, new AddLinkCommand());
            parser.addCommand(CommonFormat.COMMAND_VIEW, new ViewCommand(Link.class));
            parser.addCommand(CommonFormat.COMMAND_DELETE, new DeleteCommand(Link.class));
        }
        return parser;
    }

    /**
     * Returns the opening description of the workspace.
     *
     * @param moduleManager The current module containing the array list of all the links.
     * @return The string containing a description of the number of links in the workspace.
     */
    @Override
    public String getWorkspaceBanner(ModuleManager moduleManager) {
        return String.format(Messages.SCHEDULE_BANNER,
                moduleManager.getModule(getModuleName()).getContentManager(Link.class).getContents().size());
    }
}
