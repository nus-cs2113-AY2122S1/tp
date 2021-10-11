package happybit.parser;

import happybit.command.ListGoalsCommand;
import happybit.command.HelpCommand;
import happybit.command.RemoveCommand;
import happybit.exception.HappyBitException;
import happybit.goal.Goal;
import happybit.goal.GoalList;

public class InputParser {
    private static final String HELP = "help";
    private static final String LIST = "list";
    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String SET = "set";
    private static final String REMOVE = "remove";

    GoalList tempGoalList = new GoalList();

    // Parses user input; base version
    public void parseInput(String input) {
        try {
            // Help command
            if (input.trim().equals(HELP)) {
                HelpCommand.runCommand();
            } else if (input.trim().equals(LIST)) {
                // list goals
                // todo TEST ADDING and PRINTING
                ListGoalsCommand.runCommand();
            }
        } catch (HappyBitException e) {
            System.out.println(e.getMessage());
        }
    }
}
