package seedu.duke;


public class FalseCommand extends Command {

    public final String feedback;

    /**
     * Constructor of the FalseCommand class
     *
     * @param feedback needs to show to user.
     */
    public FalseCommand(String feedback) {
        this.feedback = feedback;
    }

    /**
     * Executes the False command.
     *
     * @return the result of false command includes the message shown to user.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(feedback);
    }

}
