package seedu.duke.storage.data.exercise.futurelist;

import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.storage.Storage;
import seedu.duke.storage.data.ListDecoder;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Decodes the upcoming exercises from future exercise list data file.
 */
public class FutureExerciseListDecoder extends ListDecoder {

    /**
     * Retrieves future exercise list from future_list.txt.
     *
     * @return The exercise list with data loaded from file
     * @throws FileNotFoundException If file is misplaced/missing
     */
    public static FutureExerciseList retrieveUpcomingListFromData(String filePath) throws FileNotFoundException {
        FutureExerciseList exercises = new FutureExerciseList();
        File file = new File(filePath);
        Scanner in = new Scanner(file);
        decodeUpcomingExercises(exercises, in);
        return exercises;
    }

    private static void decodeUpcomingExercises(FutureExerciseList exercises, Scanner in) {
        while (in.hasNext()) {
            try {
                decodeUpcomingExerciseDataFromString(exercises, in.nextLine());
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void decodeUpcomingExerciseDataFromString(FutureExerciseList exercises,
                                                             String line) throws InvalidDataException {
        try {
            final String[] exerciseDetails = line.split(Storage.FILE_TEXT_DELIMITER);
            final String name = exerciseDetails[1];
            final int calories = Integer.parseInt(exerciseDetails[2]);
            final LocalDate dateOfExercise = parseDate(exerciseDetails[3]);
            final Exercise exercise = new Exercise(name, calories, dateOfExercise);
            if (!exercise.isValid() || !isWithinNextYear(dateOfExercise)) {
                throw new InvalidDataException(Storage.FILENAME_LIST_FUTURE, line);
            }
            exercises.addItem(new Exercise(name, calories, dateOfExercise));
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException
                | DateTimeParseException e) {
            throw new InvalidDataException(Storage.FILENAME_LIST_FUTURE, line);
        }
    }
}
