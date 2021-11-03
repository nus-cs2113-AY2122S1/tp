package seedu.duke.command.workout;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.Exercise;
import seedu.duke.data.Workout;
import seedu.duke.data.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

//@@author KishorKumar11

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
    public RecommendWorkoutCommand(String workoutLevel) throws GetJackDException {
        assert workoutLevel != null;
        if (!workoutLevel.equals("beginner") & !workoutLevel.equals("intermediate")
                & !workoutLevel.equals("pro")) {
            throw new GetJackDException("Invalid command parameter provided!");
        }
        this.workoutLevel = workoutLevel;
        setupLogger(LOGGER);
    }

    /**
     * Executes recommend command to display the workouts of the given difficulty.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     * @return all the information to be displayed to the user
     * @throws GetJackDException if an error occurs within the storage
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        LOGGER.info("Showing the recommended " + workoutLevel + " Workouts");
        Map<String, ArrayList<?>> recommendedWorkouts = getRecommendedWorkouts(workoutLevel);

        // Loops through each exercise inside each workout to add it to the respective exerciseList
        for (Map.Entry<String, ArrayList<?>> m : recommendedWorkouts.entrySet()) {
            ArrayList<?> list = m.getValue();
            ArrayList<Exercise> exerciseList = new ArrayList<>();
            assert !list.isEmpty();
            for (Object o : list) {
                assert o instanceof Exercise;
                exerciseList.add((Exercise) o);
            }
            // Adds the recommended workout to the workoutList
            String recommendedWorkoutName = workoutLevel + " " + m.getKey();
            Workout recommendedWorkout = new Workout(recommendedWorkoutName, exerciseList);
            workouts.addWorkout(recommendedWorkout);

            String jsonString = storage.convertToJson(workouts);
            storage.saveData(jsonString);
        }
        return new CommandResult(getRecommendedWorkouts(workoutLevel), true);
    }

    //@@author qqkoh

    /**
     * Gets users recommend workouts based on the difficulty provided.
     *
     * @param workoutLevel is the difficulty of the workouts
     */
    private Map<String, ArrayList<?>> getRecommendedWorkouts(String workoutLevel) {
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

    private ArrayList<Exercise> getBeginnerArmWorkout() {
        ArrayList<Exercise> armExercises = new ArrayList<>();
        armExercises.add(new Exercise("Push Ups", 3, 10));
        armExercises.add(new Exercise("Inclined Push Ups", 3, 5));
        armExercises.add(new Exercise("Bench Dips", 4, 12));
        armExercises.add(new Exercise("Bear Crawl", 4, 10));

        return armExercises;
    }

    private ArrayList<Exercise> getBeginnerAbWorkout() {
        ArrayList<Exercise> abExercises = new ArrayList<>();
        abExercises.add(new Exercise("Sit Ups", 3, 10));
        abExercises.add(new Exercise("Plank", 2, 1));
        abExercises.add(new Exercise("Mountain Climbers", 3, 15));

        return abExercises;
    }

    /**
     * To create a map of list of beginner exercises for arms and abs.
     *
     * @return map of the lists of exercises under a certain muscle group.
     */
    private Map<String, ArrayList<?>> getBeginnerWorkout() {
        Map<String, ArrayList<?>> map = new HashMap<>();
        ArrayList<Exercise> armExercises = getBeginnerArmWorkout();
        map.put("Arm", armExercises);

        ArrayList<Exercise> absExercises = getBeginnerAbWorkout();
        map.put("Abs", absExercises);

        return map;
    }

    private ArrayList<Exercise> getIntermediateShoulderWorkout() {
        ArrayList<Exercise> shoulderExercises = new ArrayList<>();
        shoulderExercises.add(new Exercise("Pike Pushups", 4, 8));
        shoulderExercises.add(new Exercise("Supported Hand Stand", 2, 1));
        shoulderExercises.add(new Exercise("Plank Up", 3, 12));

        return shoulderExercises;
    }

    private ArrayList<Exercise> getIntermediateGlutesWorkout() {
        ArrayList<Exercise> glutesExercises = new ArrayList<>();
        glutesExercises.add(new Exercise("Kick Backs", 3, 15));
        glutesExercises.add(new Exercise("Reverse Leg Lift", 4, 12));
        glutesExercises.add(new Exercise("Bridges", 2, 20));

        return glutesExercises;
    }

    /**
     * Creates map of list of intermediate exercises for shoulders and glutes.
     *
     * @return map of the lists of exercises under a certain muscle group.
     */
    private Map<String, ArrayList<?>> getIntermediateWorkout() {
        Map<String, ArrayList<?>> map = new HashMap<>();

        ArrayList<Exercise> shoulderExercises = getIntermediateShoulderWorkout();
        map.put("Shoulders", shoulderExercises);

        ArrayList<Exercise> glutesExercises = getIntermediateGlutesWorkout();
        map.put("Glutes", glutesExercises);

        return map;
    }

    private ArrayList<Exercise> getProPushWorkout() {
        ArrayList<Exercise> pushExercises = new ArrayList<>();
        pushExercises.add(new Exercise("Wide Arm Push Ups", 5, 15));
        pushExercises.add(new Exercise("Diamond Push Ups", 5, 12));
        pushExercises.add(new Exercise("Archer Push Ups", 5, 10));

        return pushExercises;
    }

    private ArrayList<Exercise> getProPullWorkout() {
        ArrayList<Exercise> pullExercises = new ArrayList<>();
        pullExercises.add(new Exercise("Pull Ups", 3, 6));
        pullExercises.add(new Exercise("Kneeling Extension", 3, 20));
        pullExercises.add(new Exercise("Reverse Snow Angel", 4, 12));

        return pullExercises;
    }

    private ArrayList<Exercise> getProLegWorkout() {
        ArrayList<Exercise> legExercises = new ArrayList<>();
        legExercises.add(new Exercise("Squats", 5, 12));
        legExercises.add(new Exercise("Lunges", 3, 10));
        legExercises.add(new Exercise("Explosive Jumps", 3, 15));

        return legExercises;
    }

    /**
     * Creates map of list of pro exercises for push(chest and triceps), pull(back and biceps) and legs.
     *
     * @return map of the lists of exercises under a certain muscle group.
     */
    private Map<String, ArrayList<?>> getProWorkout() {
        Map<String, ArrayList<?>> map = new HashMap<>();

        ArrayList<Exercise> pushExercises = getProPushWorkout();
        map.put("Push Workout", pushExercises);

        ArrayList<Exercise> pullExercises = getProPullWorkout();
        map.put("Pull Workout", pullExercises);

        ArrayList<Exercise> legExercises = getProLegWorkout();
        map.put("Leg Workout", legExercises);

        return map;
    }

}
