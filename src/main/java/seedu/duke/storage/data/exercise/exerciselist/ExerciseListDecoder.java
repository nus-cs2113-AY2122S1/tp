package seedu.duke.storage.data.exercise.exerciselist;

import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.storage.Storage;
import seedu.duke.storage.data.ListDecoder;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Decodes the exercise list from storage.
 */
public class ExerciseListDecoder extends ListDecoder {

    /**
     * Retrieves exercise list or upcoming list from data.
     *
     * @return The exercise list with data loaded from file
     * @throws FileNotFoundException If file is misplaced/missing
     */
    public static ExerciseList retrieveExerciseListFromData(String filePath) throws FileNotFoundException {
        ExerciseList exercises = new ExerciseList();
        File file = new File(filePath);
        Scanner in = new Scanner(file);
        decodeExercises(exercises, in);
        return exercises;
    }

    private static void decodeExercises(ExerciseList exercises, Scanner in) {
        while (in.hasNext()) {
            try {
                decodeExerciseDataFromString(exercises, in.nextLine());
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void decodeExerciseDataFromString(ExerciseList exercises, String line) throws InvalidDataException {
        try {
            final String[] exerciseDetails = line.split(Storage.FILE_TEXT_DELIMITER);
            final String name = exerciseDetails[1];
            final int calories = Integer.parseInt(exerciseDetails[2]);
            final LocalDate dateOfExercise = parseDate(exerciseDetails[3]);
            final Exercise exercise = new Exercise(name, calories, dateOfExercise);
            if (!exercise.isValid() || !isWithinPastTenYears(dateOfExercise)) {
                throw new InvalidDataException(Storage.FILENAME_LIST_EXERCISE, line);
            }
            exercises.addItem(exercise);
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new InvalidDataException(Storage.FILENAME_LIST_EXERCISE, line);
        }
    }
}
