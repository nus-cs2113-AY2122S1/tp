package terminus.command;

import terminus.parser.CommandParser;

public class CommandResult {

    protected CommandParser additionalData;
    protected boolean isOk;
    protected boolean isExit;
    protected String errorMessage;

    public CommandResult(boolean isOk, boolean isExit, CommandParser additionalData, String errorMessage) {
        this.additionalData = additionalData;
        this.isOk = isOk;
        this.errorMessage = errorMessage;
        this.isExit = isExit;
    }

    public CommandResult(boolean isOk, boolean isExit) {
        this(isOk, isExit, null, null);
    }

    public CommandResult(boolean isOk, CommandParser additionalData) {
        this(isOk, false, additionalData, null);
    }

    public CommandResult(boolean isOk, String errorMessage) {
        this(isOk, false, null, errorMessage);
    }

    public CommandResult(boolean isOk) {
        this(isOk, false, null, null);
    }

    public CommandParser getAdditionalData() {
        return additionalData;
    }

    public boolean isOk() {
        return isOk;
    }

    public boolean isExit() {
        return isExit;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
