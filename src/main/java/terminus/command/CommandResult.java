package terminus.command;

import terminus.parser.CommandParser;

public class CommandResult  {
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
