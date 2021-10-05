package terminus.parser;

import terminus.command.*;
import terminus.command.zoomlink.AddLinkCommand;
import terminus.common.CommonFormat;
import terminus.content.Link;

public class LinkCommandParser extends CommandParser {

    public LinkCommandParser() {
        super(CommonFormat.COMMAND_SCHEDULE);
    }

    public static LinkCommandParser getInstance() {
        LinkCommandParser parser = new LinkCommandParser();
        parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
        parser.addCommand(CommonFormat.COMMAND_ADD, new AddLinkCommand());
        parser.addCommand(CommonFormat.COMMAND_VIEW, new ViewCommand<Class<Link>>(Link.class));
        parser.addCommand(CommonFormat.COMMAND_DELETE, new DeleteCommand<Class<Link>>(Link.class));
        return parser;
    }

}
