package terminus.parser;

import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import terminus.command.ExitCommand;
import terminus.command.Command;
import terminus.command.HelpCommand;
import terminus.common.TerminusLogger;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;

public abstract class CommandParser {

    private static final String SPACE_DELIMITER = "\\s+";
    protected String workspace;
    protected final HashMap<String, Command> commandMap;

    /**
     * Initializes the commandMap.
     * Adds some default commands to it.
     *
     * @param workspace The name of the workspace.
     */
    public CommandParser(String workspace) {
        this.commandMap = new HashMap<>();
        this.workspace = workspace;
        addCommand("exit", new ExitCommand());
        addCommand("help", new HelpCommand(this));
    }


    /**
     * Parses the command and its arguments.
     *
     * @param command The user input command.
     * @return The Command object to be executed.
     * @throws InvalidCommandException when there is no command or empty command.
     * @throws InvalidArgumentException when arguments could not be parsed.
     */

    public Command parseCommand(String command)
            throws InvalidCommandException, InvalidArgumentException {
        String[] commandLine = command.strip().split(SPACE_DELIMITER, 2);
        TerminusLogger.info("Parsing Command: " + command);
        assert commandLine.length <= 2 && commandLine.length > 0;

        String cmdName = commandLine[0];
        Command currentCommand = commandMap.get(cmdName.strip().toLowerCase(Locale.ROOT));
        if (currentCommand == null) {
            throw new InvalidCommandException("Command not found! Type 'help' for a list of commands.");
        }
        String cmdData = null;
        if (commandLine.length > 1) {
            cmdData = commandLine[1];
        }
        TerminusLogger.info("Parsing arguments.");
        currentCommand.parseArguments(cmdData);
        return currentCommand;
    }

    public Set<String> getCommandList() {
        return commandMap.keySet();
    }

    public abstract String getWorkspaceBanner(NusModule module);

    /**
     * Returns the list of items in the help menu.
     *
     * @return Array of strings contain the help messages.
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

    /**
     * Adds a command to the commandMap.
     *
     * @param cmdName The name of the command.
     * @param command The actual command object.
     */
    protected void addCommand(String cmdName, Command command) {
        commandMap.put(cmdName, command);
    }

    /**
     * Returns the name of the current workspace.
     *
     * @return The name of the workspace.
     */
    public String getWorkspace() {
        return workspace;
    }
}