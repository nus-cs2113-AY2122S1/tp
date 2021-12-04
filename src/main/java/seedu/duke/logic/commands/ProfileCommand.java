package seedu.duke.logic.commands;

/**
 * Represents the command that when executed, shows the value of name, height and weight in the Profile.
 */
public class ProfileCommand extends Command {

    public ProfileCommand() {
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(super.profile.convertToString());
    }
}
