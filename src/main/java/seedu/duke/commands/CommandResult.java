package seedu.duke.commands;

public class CommandResult {

    public String feedbackToUser;

    /**
     * Sets the String feedback to User to indicate whether
     * command was executed successfully
     *
     * @param s message returned after execution of different Commands
     */
    public CommandResult(String s) {

        feedbackToUser = s;
    }
}