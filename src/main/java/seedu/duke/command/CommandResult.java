package seedu.duke.command;

import java.util.ArrayList;
import java.util.Map;

public class CommandResult {

    public final String feedbackToUser;
    public final ArrayList itemList;
    public final Map<String, ArrayList> map;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.itemList = null;
        this.map = null;
    }

    public CommandResult(String feedbackToUser, ArrayList itemList) {
        this.feedbackToUser = feedbackToUser;
        this.itemList = itemList;
        this.map = null;
    }

    // Search command
    public CommandResult(Map<String, ArrayList> map) {
        this.feedbackToUser = null;
        this.itemList = null;
        this.map = map;
    }

}
