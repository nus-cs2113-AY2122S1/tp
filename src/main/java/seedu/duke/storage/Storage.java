package seedu.duke.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.DeadlineWorkout;
import seedu.duke.data.Workout;
import seedu.duke.data.WorkoutList;
import seedu.duke.exception.StorageException;
import seedu.duke.storage.models.ExerciseModel;
import seedu.duke.storage.models.WorkoutListModel;
import seedu.duke.storage.models.WorkoutModel;
import seedu.duke.data.Exercise;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

//@@author jonathanmui4

/**
 * To deal with loading tasks from the json file and saving tasks in the json file.
 */
public class Storage {
    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());
    private final String storagePath = "data/workouts.json";
    private final File file = new File(storagePath);
    private final String filePath = file.getAbsolutePath();
    private final WorkoutListModel workoutListModel = new WorkoutListModel();

    public Storage() throws GetJackDException {
        setupLogger(LOGGER);
        try {
            assert file != null;
            file.getParentFile().mkdirs();
            file.createNewFile();

            LOGGER.info("Generated Data Folder and storage JSON");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Data file can't be created. Exception: ", e);
            throw new GetJackDException("☹ OOPS!!! Data file can't be created.");
        }
    }

    /**
     * Loads data into WorkoutList class from JSON file.
     *
     * @param workoutList Manages workouts after loading data
     * @throws GetJackDException Exception is thrown when data cannot be loaded
     */
    public void loadData(WorkoutList workoutList) throws GetJackDException, StorageException {
        assert file.exists();
        if (file.length() == 0) {
            return;
        }
        assert file.length() > 0;

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
            ArrayList<WorkoutModel> workoutsStorageForm = convertFromJson(jsonString);

            for (WorkoutModel workoutModel : workoutsStorageForm) {
                if (workoutModel.getDeadline().equals("")) {
                    Workout workout = new Workout(workoutModel.getWorkoutName());
                    addWorkout(workoutList, workoutModel, workout);
                } else {
                    try {
                        DeadlineWorkout deadlineWorkout = new DeadlineWorkout(
                                workoutModel.getWorkoutName(),
                                LocalDate.parse(workoutModel.getDeadline())
                        );
                        addDeadlineWorkout(workoutList, workoutModel, deadlineWorkout);
                    } catch (DateTimeParseException e) {
                        throw new StorageException("☹ OOPS!!! Error reading file! Please " +
                                "ensure the deadline in data/workouts.json " +
                                "is in the format yyyy-mm-dd.");
                    }
                }
            }

            LOGGER.info("Successfully loaded data from JSON file.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Data file can't be loaded. Exception: ", e);
            throw new GetJackDException("☹ OOPS!!! Can't load data");
        }
    }

    private void addWorkout(
            WorkoutList workoutList,
            WorkoutModel workoutModel,
            Workout workout
    ) throws StorageException {
        assert workout != null;
        populateExercises(workoutModel, workout);
        workoutList.addWorkout(workout);
    }

    private void addDeadlineWorkout(
            WorkoutList workoutList,
            WorkoutModel workoutModel,
            DeadlineWorkout deadlineWorkout
    ) throws StorageException {
        assert deadlineWorkout != null;
        populateExercises(workoutModel, deadlineWorkout);
        workoutList.addWorkout(deadlineWorkout);
    }


    private void populateExercises(WorkoutModel workoutModel, Workout workout) throws StorageException {
        for (ExerciseModel exerciseModel : workoutModel.getExercises()) {
            Exercise exercise = readExercise(exerciseModel);
            workout.addExercise(exercise);
        }
    }

    /**
     * Saves data from WorkoutList into the JSON file.
     *
     * @param jsonString Workout List converted into a JSON string
     * @throws GetJackDException Exception is thrown when there is an error writing to JSON file
     */
    public void saveData(String jsonString) throws GetJackDException {
        assert file.exists();

        try {
            FileWriter fileWriter = new FileWriter(storagePath, false);
            fileWriter.write(jsonString);
            fileWriter.close();

            LOGGER.info("Successfully saved data into JSON file.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing data to save file. Exception: ", e);
            throw new GetJackDException("☹ OOPS!!! Error writing to file while data!");
        }
    }

    /**
     * Reads exercises as they are read from the JSON file and converts them to Exercise class.
     *
     * @param exerciseModel Storage model for Exercise class
     * @return Exercise to be stored in the Workout class
     */
    private Exercise readExercise(ExerciseModel exerciseModel) throws StorageException {
        try {
            int exerciseSets = Integer.parseInt(exerciseModel.getSets());
            int exerciseReps = Integer.parseInt(exerciseModel.getReps());
            Exercise exercise = new Exercise(exerciseModel.getDescription(), exerciseSets, exerciseReps);

            if (exerciseModel.getIsDone().equals("true")) {
                exercise.setDone();
            }

            return exercise;
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid values for sets and reps in data/workouts.json");
            throw new StorageException("☹ OOPS!!! Error reading file! Please " +
                    "ensure the sets and reps in data/workouts.json are numbers.");
        }

    }

    /**
     * Converts data read from JSON files and stored data into the attributes of the relevant storage model classes.
     *
     * @param jsonString jsonString read from JSON file
     * @return Workout List Model
     * @throws GetJackDException Exception is thrown when data from JSON file cannot be converted to storage model class
     */
    private ArrayList<WorkoutModel> convertFromJson(String jsonString) throws StorageException {
        try {
            JsonNode node = JsonUtil.parse(jsonString);
            WorkoutListModel.clearWorkoutListModel();
            assert workoutListModel.getWorkouts().isEmpty();
            WorkoutListModel workoutListModel = JsonUtil.fromJson(node, WorkoutListModel.class);

            return workoutListModel.getWorkouts();
        } catch (IOException e) {
            throw new StorageException("☹ OOPS!!! Error converting from JSON due to invalid JSON format. Check for " +
                    "proper closing brackets \"{ }\" and \"[ ]\" in data/workouts.json.");
        }
    }

    /**
     * Converts the workoutList into a JSON string.
     *
     * @param workoutList Workout List to convert
     * @return Converted JSON string
     * @throws GetJackDException Exception is thrown when workout List cannot be converted
     */
    public String convertToJson(WorkoutList workoutList) throws GetJackDException {
        WorkoutListModel.clearWorkoutListModel();
        assert workoutListModel.getWorkouts().isEmpty();
        workoutList.convertAllWorkoutsToStorageModel();
        JsonNode node = JsonUtil.toJson(workoutListModel);

        try {
            return JsonUtil.stringify(node, true);
        } catch (JsonProcessingException e) {
            throw new GetJackDException("Fail to convert to JSON node");
        }
    }
}
