package expiryeliminator.commands;

//@@author bernardboey-reused
// Reused from
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/commands/IncorrectCommand.java
// with minor modifications

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public String execute() {
        return feedbackToUser;
    }

}
//@@author
