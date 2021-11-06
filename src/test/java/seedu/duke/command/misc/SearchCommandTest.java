package seedu.duke.command.misc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.DeadlineWorkout;
import seedu.duke.data.Exercise;
import seedu.duke.data.Workout;
import seedu.duke.data.WorkoutList;
import seedu.duke.exception.GetJackDException;
import seedu.duke.exception.StorageException;
import seedu.duke.storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SearchCommandTest {
    private WorkoutList workoutList;
    private Storage storage;

    @BeforeEach
    public void setUp() throws GetJackDException, StorageException {
        createEmptyWorkout();
        storage = new Storage();
    }

    private void createEmptyWorkout() {
        Workout workout = new Workout("blah");
        workoutList = new WorkoutList();
        workoutList.addWorkout(workout);
    }

    private void createWorkoutWithExercise() {
        Exercise e = new Exercise("poi", 2, 4);
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(e);
        Workout w = new Workout("poia", exercises);
        workoutList.addWorkout(w);
    }

    private void createDeadlineWorkoutWithExercise() {
        Exercise e = new Exercise("poi", 2, 4);
        LocalDate date = LocalDate.now();
        DeadlineWorkout w = new DeadlineWorkout("poia", date);
        w.addExercise(e);
        workoutList.addWorkout(w);
    }

    @Test
    void executeUserCommand_emptyWorkoutList_commandResultMessageStringOnly() {
        SearchCommand c = new SearchCommand("nothing");
        CommandResult result = c.executeUserCommand(workoutList,storage);
        assert result.feedbackToUser != null;
        assert result.map == null;
        assert result.itemList == null;
    }

    @Test
    void executeUserCommand_keywordMatchesWorkoutAndExercise_commandResultMapOnly() {
        Command.workoutMode = 0;
        createWorkoutWithExercise();
        SearchCommand c = new SearchCommand("O");
        CommandResult result = c.executeUserCommand(workoutList, storage);
        assert result.feedbackToUser == null;
        assert result.map != null;
        assertEquals(2, result.map.size());
        assert result.itemList == null;
        assert !result.isTable;
    }

    @Test
    void executeUserCommand_keywordMatchesWorkoutDeadline_commandResultMapOnly() {
        Command.workoutMode = 0;
        createDeadlineWorkoutWithExercise();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String strDate = formatter.format(LocalDate.now());
        SearchCommand c = new SearchCommand(strDate);
        CommandResult result = c.executeUserCommand(workoutList, storage);
        assert result.feedbackToUser == null;
        assert result.map != null;
        assertEquals(1, result.map.size());
        assert result.itemList == null;
        assert !result.isTable;
    }

}