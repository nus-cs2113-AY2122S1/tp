package happybit.parser;
import happybit.command.ListHabitsCommand;
import happybit.command.ListGoalsCommand;
import happybit.command.HelpCommand;

public class InputParser {
    private static final String HELP = "help";
    private static final String LIST = "list";
    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String SET = "set";
    private static final String REMOVE = "remove";

    // Parses user input; base version
    public void parseInput(String input) {
        // Help command
        if (input.trim().equals(HELP)) {
            HelpCommand.runCommand();
        } else if (input.trim().equals(LIST)) {
            // check if trimmed input has anything behind list keyword
            //String trimmedInput = input.replaceAll("\\s+", " ");
            ListHabitsCommand.runCommand();
        }
    }
}
