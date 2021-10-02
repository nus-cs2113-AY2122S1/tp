package terminus.parser;

import java.util.HashMap;
import java.util.Locale;
import terminus.command.Command;

public class CommandParser {
    protected String workspace;
    protected final HashMap <String, Command> commandMap;
    public CommandParser (String workspace) {
        commandMap = new HashMap<String, Command>();
        this.workspace = workspace;
    }
    public Command parseCommand(String command) {
        String [] commandLine = command.strip().split(" ",2);
        String cmdName = commandLine[0];
        String cmdData = null;
        if (commandLine.length > 1) {
            cmdData = commandLine[1];
        }
        Command currentCommand = commandMap.get(cmdName.strip().toLowerCase(Locale.ROOT));
        if (currentCommand == null) {
            return null;
        }
        currentCommand.parseArguments(cmdData);
        return currentCommand;
    }

    public String getWorkspace() {
        return workspace;
    }
}