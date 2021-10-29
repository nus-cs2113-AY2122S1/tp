package terminus.command;

import terminus.parser.CommandParser;
import terminus.storage.StorageActionEnum;
import terminus.storage.StorageTypeEnum;

public class CommandResult {

    protected boolean hasChange;
    protected CommandParser newCommandParser;
    protected String[] message;
    protected boolean isExit;

    protected StorageActionEnum storageAction;
    protected StorageTypeEnum storageType;
    protected String module;

    protected String deletedItemName;
    
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
        this.hasChange = false;
    }

    public CommandResult(String module, StorageActionEnum action, StorageTypeEnum type, String... message) {
        this(message);
        this.storageAction = action;
        this.storageType = type;
        this.module = module;
        this.hasChange = true;
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

    /**
     * Returns the information to alter data in file storage.
     *
     * @return True if it requires file storage or else false.
     */
    public boolean hasChange(){
        return hasChange;
    }

    public StorageActionEnum getStorageAction() {
        return storageAction;
    }

    public StorageTypeEnum getStorageType() {
        return storageType;
    }

    public String getModule() {
        return module;
    }

    public String getDeletedItemName() {
        return deletedItemName;
    }

    public void setDeletedItemName(String deletedItemName) {
        this.deletedItemName = deletedItemName;
    }
}
