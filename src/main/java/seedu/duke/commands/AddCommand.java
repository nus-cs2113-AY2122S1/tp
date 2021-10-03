package seedu.duke.commands;

public class AddCommand extends Command {
    protected static String EventDescription;

    public AddCommand() {
    }

    public CommandResult execute() {
        //add task with the specifcs here
        // if completed properly return this commandResult
        return new CommandResult("AddCommand");
        //else return new COmmandResult("Task unable to be added, you forgot the date!");
    }
}
