package seedu.duke.command.workout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.WorkoutList;
import seedu.duke.exception.StorageException;
import seedu.duke.storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateWorkoutCommandTest {
    private WorkoutList workoutList;
    private Storage storage;

    @BeforeEach
    public void setUp() throws GetJackDException, StorageException {
        createEmptyWorkoutList();
        storage = new Storage();
    }

    private void createEmptyWorkoutList() {
        workoutList = new WorkoutList();
    }

    @Test
    void executeUserCommand_validDataAddToEmptyWorkoutList_exerciseAdded() {
        try {
            int initialSize = workoutList.getAllWorkouts().size();
            CreateWorkoutCommand c = new CreateWorkoutCommand("workout", LocalDate.parse("2021-12-12"));
            c.executeUserCommand(workoutList, storage);
            assertEquals(initialSize + 1, workoutList.getAllWorkouts().size());
        } catch (GetJackDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createWorkoutCommand_InvalidWorkoutDeadline_expectDateTimeParseExceptionThrown() {
        assertThrows(DateTimeParseException.class,
            () -> new CreateWorkoutCommand("Test", LocalDate.parse("abc"))
        );
    }

    @Test
    void createWorkoutCommand_InvalidWorkoutName_expectAssertionError() {
        assertThrows(AssertionError.class,
            () -> new CreateWorkoutCommand("")
        );
    }
}
