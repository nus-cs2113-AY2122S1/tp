package seedu.duke.commands;

public class Command {

    public Command() {
    }

    public CommandResult execute() {

        /*
        This is thrown if ever an empty command is executed (pseudo error message)
        Will get overridden by methods within the separate commands
         */
        return new CommandResult("Sorry I did not quite understand, "
                + System.lineSeparator()
                + "try typing in help for brief user manual");
    }
}
