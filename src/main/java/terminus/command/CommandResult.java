package terminus.command;

import terminus.parser.CommandParser;

public class CommandResult {

    protected CommandParser newCommandParser;
    protected String[] message;
    protected boolean isExit;
    
    public CommandResult(String... message) {
        this(false, null, message);
    }
    
    public CommandResult(CommandParser parser) {
        this(false, parser);
    }
    
    public CommandResult(boolean isExit) {
        this(isExit, null);
    }

    public CommandResult(boolean isExit, CommandParser newCommandParser, String... message) {
        this.message = message;
        this.newCommandParser = newCommandParser;
        this.isExit = isExit;
    }

    /**
     * Returns the CommandParser that is required to switch workspaces.
     * If additionalData will be null.
     *
     * @return The CommandParser object for the workspace or else null.
     */
    public CommandParser getNewCommandParser() {
        return newCommandParser;
    }

    /**
     * Returns the message that the command wishes to output.
     * 
     * @return The message that the command wishes to output.
     */
    public String[] getMessage() {
        return message;
    }

    /**
     * Returns the result of the command execution.
     *
     * @return True if successful or else false.
     */
    @Deprecated
    public boolean isOk() {
        return true;
    }

    /**
     * Returns the result to exit or not.
     *
     * @return True if 'exit' command is sent.
     */
    public boolean isExit() {
        return isExit;
    }

}
