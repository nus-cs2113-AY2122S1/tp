package seedu.duke.command;

import java.util.ArrayList;
import java.util.Map;

//@@author qqkoh

/**
 * Used to format the UI from the commands.
 */
public class CommandResult {

    // feedbackToUser is used when a single String message is needed for the command result.
    public final String feedbackToUser;

    // itemList is used when only a single list of a single type is needed for the command result.
    public final ArrayList<?> itemList;

    // map is used when a command result contains multiple lists, and each list is accompanied by a description
    public final Map<String, ArrayList<?>> map;

    // if isTable is true, the map will be shown to the user as a table, otherwise, the map will be shown as a list
    public final boolean isTable;

    /**
     * Constructor that is used when a single String message is needed for the command result.
     *
     * @param feedbackToUser message to be shown to the user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.itemList = null;
        this.map = null;
        this.isTable = false;
    }

    /**
     * Constructor that is used when a single String message and a single list is needed for the command reuslt.
     *
     * @param feedbackToUser message to be shown to the user
     * @param itemList       list to be shown to the user
     */
    public CommandResult(String feedbackToUser, ArrayList<?> itemList) {
        this.feedbackToUser = feedbackToUser;
        this.itemList = itemList;
        this.map = null;
        this.isTable = false;
    }

    /**
     * Constructor that is used when multiple lists must be shown to the user, each list with its accompanying message.
     *
     * @param map     contains message as the key, accompanying list as the value
     * @param isTable true when map is to be shown to the user as a table, false otherwise
     */
    public CommandResult(Map<String, ArrayList<?>> map, boolean isTable) {
        this.feedbackToUser = null;
        this.itemList = null;
        this.map = map;
        this.isTable = isTable;
    }

}
