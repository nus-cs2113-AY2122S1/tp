package seedu.duke.command;

import java.util.ArrayList;
import java.util.Map;

public class CommandResult {

    public final String feedbackToUser;
    public final ArrayList itemList;
    public final Map<String, ArrayList> map;
    public final boolean isTable;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.itemList = null;
        this.map = null;
        this.isTable = false;
    }

    public CommandResult(String feedbackToUser, ArrayList itemList) {
        this.feedbackToUser = feedbackToUser;
        this.itemList = itemList;
        this.map = null;
        this.isTable = false;
    }

    // Search, Recommend
    public CommandResult(Map<String, ArrayList> map, boolean isTable) {
        this.feedbackToUser = null;
        this.itemList = null;
        this.map = map;
        this.isTable = isTable;
    }

}
