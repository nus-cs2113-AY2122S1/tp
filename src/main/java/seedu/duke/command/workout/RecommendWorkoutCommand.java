package seedu.duke.command.workout;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

/**
 * To recommend workouts with exercises of a difficulty level provided by the user.
 */
public class RecommendWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "recommend";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Provides a given set of workouts with exercises.\n"
            + "Format: recommend [Workout level]\n"
            + "Parameters:\n"
            + "\tWorkout level - difficulty of workout [beginner/intermediate/pro]\n"
            + "Example: " + COMMAND_WORD + " beginner";
    private static final Logger LOGGER = Logger.getLogger(RecommendWorkoutCommand.class.getName());
    private final String workoutLevel;

    /**
     * Instantiates object and sets parameters for recommending workouts based on workout difficulty.
     *
     * @param workoutLevel is the difficulty of the workouts
     */
    public RecommendWorkoutCommand(String workoutLevel) {
        assert workoutLevel != null;
        this.workoutLevel = workoutLevel;
        setupLogger(LOGGER);
    }

    /**
     * Executes recommend command to display the workouts of the given difficulty.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) {
        LOGGER.info("Showing the recommended " + workoutLevel + " Workouts");
        return new CommandResult(getRecommendedWorkouts(workoutLevel), true);
    }

    /**
     * Gets users recommend workouts based on the difficulty provided.
     *
     * @param workoutLevel is the difficulty of the workouts
     */
    private Map<String, ArrayList> getRecommendedWorkouts(String workoutLevel) {
        switch (workoutLevel) {
        case "beginner":
            return getBeginnerWorkout();
        case "intermediate":
            return getIntermediateWorkout();
        case "pro":
            return getProWorkout();
        default:
            return Collections.emptyMap();
        }
    }

    private ArrayList<String> getBeginnerArmWorkout() {
        ArrayList<String> armExercises = new ArrayList<>();
        armExercises.add("Normal push-ups");
        armExercises.add("Inclined push-ups");
        armExercises.add("Bench dips");
        armExercises.add("Bear crawl");

        return armExercises;
    }

    private ArrayList<String> getBeginnerAbWorkout() {
        ArrayList<String> abExercises = new ArrayList<>();
        abExercises.add("Sit ups");
        abExercises.add("Plank");
        return abExercises;
    }

    private Map<String, ArrayList> getBeginnerWorkout() {
        Map<String, ArrayList> map = new HashMap<>();
        ArrayList<String> armExercises = getBeginnerArmWorkout();
        map.put("Arm", armExercises);

        ArrayList<String> absExercises = getBeginnerAbWorkout();
        map.put("Abs", absExercises);

        return map;
    }

    private ArrayList<String> getIntermediateShoulderWorkout() {
        ArrayList<String> shoulderExercises = new ArrayList<>();
        shoulderExercises.add("Pike pushup");
        shoulderExercises.add("Supported hand stand");
        return shoulderExercises;
    }

    private ArrayList<String> getIntermediateGlutesWorkout() {
        ArrayList<String> glutesExercises = new ArrayList<>();
        glutesExercises.add("Kick backs");
        return glutesExercises;
    }

    private Map<String, ArrayList> getIntermediateWorkout() {
        Map<String, ArrayList> map = new HashMap<>();

        ArrayList<String> shoulderExercises = getIntermediateShoulderWorkout();
        map.put("Shoulders", shoulderExercises);

        ArrayList<String> glutesExercises = getIntermediateGlutesWorkout();
        map.put("Glutes", glutesExercises);

        return map;
    }

    private ArrayList<String> getProPushWorkout() {
        ArrayList<String> pushExercises = new ArrayList<>();
        pushExercises.add("Wide push ups");
        return pushExercises;
    }

    private ArrayList<String> getProPullWorkout() {
        ArrayList<String> pullExercises = new ArrayList<>();
        pullExercises.add("Pull-ups");
        return pullExercises;
    }

    private ArrayList<String> getProLegWorkout() {
        ArrayList<String> legExercises = new ArrayList<>();
        legExercises.add("Squats");
        legExercises.add("Lunges");
        legExercises.add("Explosive Jumps");
        return legExercises;
    }

    private Map<String, ArrayList> getProWorkout() {
        Map<String, ArrayList> map = new HashMap<>();

        ArrayList<String> pushExercises = getProPushWorkout();
        map.put("Push Workout", pushExercises);

        ArrayList<String> pullExercises = getProPullWorkout();
        map.put("Pull Workout", pullExercises);

        ArrayList<String> legExercises = getProLegWorkout();
        map.put("Leg Workout", legExercises);

        return map;
    }
}
