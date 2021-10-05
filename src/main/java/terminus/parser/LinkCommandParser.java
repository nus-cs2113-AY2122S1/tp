package terminus.parser;

import terminus.command.*;
import terminus.command.zoomlink.AddLinkCommand;
import terminus.command.zoomlink.ViewLinkCommand;
import terminus.common.CommonFormat;
import terminus.content.Link;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

public class LinkCommandParser {
    private static final String SPACE_DELIMITER = "\\s+";
    protected String workspace;
    protected final HashMap<String, Command> commandMap;

    public LinkCommandParser(String workspace) {
        this.commandMap = new HashMap<>();
        this.workspace = workspace;
        addCommand("exit", new ExitCommand());
        addCommand("help", new HelpCommand(this));
    }

    public Command parseCommand(String command) throws InvalidCommandException, InvalidArgumentException {
        String[] commandLine = command.strip().split(SPACE_DELIMITER, 2);
        String cmdName = commandLine[0];
        Command currentCommand = commandMap.get(cmdName.strip().toLowerCase(Locale.ROOT));
        if (currentCommand == null) {
            throw new InvalidCommandException("Command not found! Type 'help' for a list of commands.");
        }

        String cmdData = null;
        if (commandLine.length > 1) {
            cmdData = commandLine[1];
        }
        currentCommand.parseArguments(cmdData);
        return currentCommand;
    }

    public Set<String> getCommandList() {
        return commandMap.keySet();
    }

    /**
     * Get the help menu for the current workspace.
     *
     * @return Array of strings contain the help messages
     */
    public String[] getHelpMenu() {
        return commandMap.entrySet()
                .stream()
                .map((i) -> String.format("%s : %s\nFormat: %s\n",
                        i.getKey(),
                        i.getValue().getHelpMessage(),
                        i.getValue().getFormat()))
                .toArray(String[]::new);
    }

    protected void addCommand(String cmdName, Command command) {
        commandMap.put(cmdName, command);
    }

    public String getWorkspace() {
        return workspace;
    }

    public LinkCommandParser() {
        super(CommonFormat.COMMAND_SCHEDULE);
    }

    public static LinkCommandParser getInstance() {
        LinkCommandParser parser = new LinkCommandParser();
        parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
        parser.addCommand(CommonFormat.COMMAND_ADD, new AddLinkCommand());
        parser.addCommand(CommonFormat.COMMAND_VIEW, new ViewLinkCommand<Class<Link>>(Link.class));
        parser.addCommand(CommonFormat.COMMAND_DELETE, new DeleteCommand<Class<Link>>(Link.class));
        return parser;
    }

}
