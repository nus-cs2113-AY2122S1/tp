package terminus.command;

import terminus.parser.CommandParser;

public class CommandResult {

    protected CommandParser additionalData;
    protected boolean isOk;
    protected boolean isExit;

    public CommandResult(boolean isOk) {
        this(isOk, false);
    }

    public CommandResult(boolean isOk, boolean isExit) {
        this(isOk, isExit, null);
    }

    public CommandResult(boolean isOk, CommandParser additionalData) {
        this(isOk, false, additionalData);
    }

    public CommandResult(boolean isOk, String errorMessage) {
        this(isOk, false, null);
    }

    public CommandResult(boolean isOk, boolean isExit, CommandParser additionalData) {
        this.additionalData = additionalData;
        this.isOk = isOk;
        this.isExit = isExit;
    }

    /**
     * Returns the CommandParser that is required to switch workspaces.
     * If additionalData will be null.
     *
     * @return The CommandParser object for the workspace or else null.
     */
    public CommandParser getAdditionalData() {
        return additionalData;
    }

    /**
     * Returns the result of the command execution.
     *
     * @return True if successful or else false.
     */
    public boolean isOk() {
        return isOk;
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
