package seedu.duke.command;

/**
 * Used to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Function which makes the condition to exit the program true.
     *
     * @return the boolean value to end the program
     */
    public static boolean isExit() {
        return isOver = true;
    }

    @Override
    public void executeUserCommand() {
    }
}
