package happybit.command;

import happybit.exception.HappyBitException;
import happybit.goal.GoalList;

import java.util.ArrayList;

public class ListGoalsCommand {
    public static void runCommand() throws HappyBitException {
        GoalList.listGoals();
    }
}
