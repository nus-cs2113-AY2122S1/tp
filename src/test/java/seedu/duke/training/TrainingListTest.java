//@@author poppolette

package seedu.duke.training;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.command.EditTraining;
import seedu.duke.command.AddTraining;
import seedu.duke.command.DeleteTraining;


/**
 * Test cases for functionalities related to TrainingList. Namely:
 * AddTraining, DeleteTraining, FindTraining, EditTraining
 *
 * To Test:
 * Add 1 training - Should Pass
 * Add 1 training with missing fields - Should Fail
 * Add 1 training with duplicate fields - Should Fail
 * Delete 1 member - Should Pass
 * Delete 1 member via name - Should Pass
 * Delete 1 member, invalid index - Should Fail
 * Find training - Should Pass
 * Edit training - Should Pass
 * Edit training with no index at all - Should Fail
 * Edit training with invalid index - Should Fail
 * Edit training with alphabetical index - Should Fail
 * Edit training with existing name - Should Fail
 */
public class TrainingListTest {

    private TrainingList trainingList;

    private TrainingSchedule training1;
    private TrainingSchedule training2;
    private TrainingSchedule training3;
    private TrainingSchedule training4;

    @BeforeEach
    public void setUp()  {

        training1 = new TrainingSchedule("FRIDAY WEEKLY TRAINING 1", "MPSH1", "1 OCT 2021", 1);
        training2 = new TrainingSchedule("FRIDAY WEEKLY TRAINING 2", "MPSH1", "8 OCT 2021", 2);
        training3 = new TrainingSchedule("FRIDAY WEEKLY TRAINING 3", "MPSH1", "15 OCT 2021", 3);
        training4 = new TrainingSchedule("FRIDAY WEEKLY TRAINING 4", "MPSH1", "22 OCT 2021", 4);

        ArrayList<TrainingSchedule> inputTrainingList = new ArrayList<TrainingSchedule>();
        inputTrainingList.add(training1);
        inputTrainingList.add(training2);
        inputTrainingList.add(training3);
        inputTrainingList.add(training4);
        trainingList = new TrainingList(inputTrainingList);
    }

    @Test
    public void addOneValidTraining() {
        String details = "add /t /n TEST TRAINING 1 /a 15 DEC 2021 /v MPSH1";

        int sizeBeforeAdd = trainingList.getTrainingListSize();
        TrainingSchedule trainingToAdd = Parser.getTrainingDescription(details);
        trainingToAdd.setTrainingIndex(sizeBeforeAdd + 1);
        new AddTraining(trainingList, trainingToAdd);
        int sizeAfterAdd = trainingList.getTrainingListSize();
        assertEquals(sizeAfterAdd, sizeBeforeAdd + 1);
    }

    @Test
    public void addOneInvalidTraining() {
        String details = "add /t /n TEST TRAINING 1 /v MPSH1";

        int sizeBeforeAdd = trainingList.getTrainingListSize();
        TrainingSchedule trainingToAdd = Parser.getTrainingDescription(details);
        trainingToAdd.setTrainingIndex(sizeBeforeAdd + 1);
        new AddTraining(trainingList, trainingToAdd);
        int sizeAfterAdd = trainingList.getTrainingListSize();

        assertEquals(sizeBeforeAdd, sizeAfterAdd);
    }

    @Test
    public void addOneDuplicateTraining() {
        String details = "add /t /n FRIDAY WEEKLY TRAINING 1 /a 1 OCT 2021 /v MPSH1";

        int sizeBeforeAdd = trainingList.getTrainingListSize();
        TrainingSchedule trainingToAdd = Parser.getTrainingDescription(details);
        trainingToAdd.setTrainingIndex(sizeBeforeAdd + 1);
        new AddTraining(trainingList, trainingToAdd);
        int sizeAfterAdd = trainingList.getTrainingListSize();

        assertEquals(sizeBeforeAdd, sizeAfterAdd);
    }

    @Test
    public void deleteOneValidTrainingByIndex() {
        String details = "delete /t 1";
        int sizeBeforeDelete = trainingList.getTrainingListSize();
        Object parameter = Parser.getParameter(details);

        new DeleteTraining(trainingList, parameter);
        int sizeAfterDelete = trainingList.getTrainingListSize();

        assertEquals(sizeBeforeDelete, sizeAfterDelete + 1);
    }

    @Test
    public void deleteOneValidTrainingByName() {
        String details = "delete /t friday weekly training 1";
        int sizeBeforeDelete = trainingList.getTrainingListSize();
        Object parameter = Parser.getParameter(details);

        new DeleteTraining(trainingList, parameter);
        int sizeAfterDelete = trainingList.getTrainingListSize();

        assertEquals(sizeBeforeDelete, sizeAfterDelete + 1);
    }

    @Test
    public void deleteOneTrainingWithInvalidIndex() {
        try {
            String details = "delete /t -1";
            Object parameter = Parser.getParameter(details);
            new DeleteTraining(trainingList, parameter);
        } catch (AssertionError e) {
            assertEquals(trainingList.getTrainingListSize(), 4);
        }
    }

    @Test
    public void deleteOneTrainingWithNoIndex() {
        String details = "delete /t";
        int sizeBeforeDelete = trainingList.getTrainingListSize();
        Object parameter = Parser.getParameter(details);
        new DeleteTraining(trainingList, parameter);

        int sizeAfterDelete = trainingList.getTrainingListSize();

        assertEquals(sizeBeforeDelete, sizeAfterDelete);
    }

    @Test
    void deleteAllTrainings() {
        trainingList.deleteTrainingSchedule(4);
        trainingList.deleteTrainingSchedule(3);
        trainingList.deleteTrainingSchedule(2);
        trainingList.deleteTrainingSchedule(1);
        assertEquals(0, trainingList.getTrainingList().size());
    }

    @Test
    void findValidTraining() {
        String name = "Friday";
        TrainingList matchingTrainings = trainingList.findTraining(name);

        assertEquals(matchingTrainings.getTrainingListSize(), trainingList.getTrainingListSize());
    }

    @Test
    void findInvalidTraining() {
        String name = "Saturday";
        TrainingList matchingTrainings = trainingList.findTraining(name);

        assertEquals(0, matchingTrainings.getTrainingListSize());
    }

    @Test
    void editTrainingSchedule() {
        final String input = "edit /t 1 /a 30 Oct 2021 /v MPSH 3";
        int index = Parser.getIndex(input);
        TrainingSchedule editedTraining = Parser.getTrainingDescription(input);
        new EditTraining(trainingList, index, editedTraining);
        assertEquals(trainingList.getTrainingTime(1), "30 OCT 2021");
        assertEquals(trainingList.getTrainingVenue(1), "MPSH 3");
    }

    @Test
    void editTrainingWithNoIndex() {
        try {
            final String input = "edit /t";
            int index = Parser.getIndex(input);
            TrainingSchedule editedTraining = Parser.getTrainingDescription(input);
            new EditTraining(trainingList, index, editedTraining);
        } catch (NullPointerException e) {
            assertEquals(trainingList.getTrainingListSize(), 4);
        }
    }

    @Test
    void editTrainingWithInvalidIndex() {
        try {
            final String input = "edit /t -999";
            int index = Parser.getIndex(input);
            TrainingSchedule editedTraining = Parser.getTrainingDescription(input);
            new EditTraining(trainingList, index, editedTraining);
        } catch (AssertionError e) {
            assertEquals(trainingList.getTrainingListSize(), 4);
        }
    }

    @Test
    void editTrainingWithAlphabeticalIndex() {
        try {
            final String input = "edit /t a";
            int index = Parser.getIndex(input);
            TrainingSchedule editedTraining = Parser.getTrainingDescription(input);
            new EditTraining(trainingList, index, editedTraining);
        } catch (NumberFormatException e) {
            assertEquals(trainingList.getTrainingListSize(), 4);
        }
    }

    @Test
    void editTrainingWithDuplicateName() {
        final String input = "edit /t 1 /n FRIDAY WEEKLY TRAINING 2";
        int index = Parser.getIndex(input);
        TrainingSchedule editedTraining = Parser.getTrainingDescription(input);
        new EditTraining(trainingList, index, editedTraining);

        assertEquals(trainingList.getTrainingName(1), "FRIDAY WEEKLY TRAINING 1");
    }
}

//@@author