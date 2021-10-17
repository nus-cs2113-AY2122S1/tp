package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.RecommendWorkoutCommand;

public class RecommendWorkoutParser extends Parser {

    public RecommendWorkoutParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareRecommendWorkout(String commandArgs) {
        String workoutLevel = commandArgs.trim();

        switch (workoutLevel) {
        case "beginner":
            String beginnerDescription = "Biceps";
            String[] beginnerExercises = {"Dumbell Curl", "ggghs"};

            return new RecommendWorkoutCommand(beginnerDescription, beginnerExercises);
        case "intermediate":
            String intermediateDescription = "Shoulder";
            String[] intermediateExercises = {"Overhead Press", "jrbhgjrg"};
            return new RecommendWorkoutCommand(intermediateDescription, intermediateExercises);
        case "pro":
            String proDescription = "Push";
            String[] proExercises = {"Bench Press", "sghj"};
            return new RecommendWorkoutCommand(proDescription, proExercises);
        default:
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + RecommendWorkoutCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareRecommendWorkout(commandArgs);
    }
}
