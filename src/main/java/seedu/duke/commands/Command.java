package seedu.duke.commands;

public class Command {

    public Command() {
    }

    /**
     * if a Command does not have a subclass this method will return an error
     * message through the CommandResult class
     *
     * @return a CommandResult type with the specific error message
     */
    public CommandResult execute() {

        /*
        This is thrown if ever an empty command is executed (pseudo error message)
        Will get overridden by methods within the separate commands
         */
        return new CommandResult("Sorry I did not quite understand, " +
                System.lineSeparator() + "try typing in help for brief user manual");
    }
}
