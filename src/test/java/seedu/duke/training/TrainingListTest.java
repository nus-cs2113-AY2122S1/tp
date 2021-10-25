package seedu.duke.training;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.command.EditTraining;
import seedu.duke.command.AddTraining;
import seedu.duke.command.DeleteTraining;

public class TrainingListTest {

    private TrainingList trainingList;

    private TrainingSchedule training1;
    private TrainingSchedule training2;
    private TrainingSchedule training3;
    private TrainingSchedule training4;

    @BeforeEach
    public void setUp() throws Exception {

        training1 = new TrainingSchedule("October Friday Weekly Training 1", "MPSH1", "1 Oct 2021");
        training2 = new TrainingSchedule("October Friday Weekly Training 2", "MPSH1", "8 Oct 2021");
        training3 = new TrainingSchedule("October Friday Weekly Training 3", "MPSH1", "15 Oct 2021");
        training4 = new TrainingSchedule("October Friday Weekly Training 4", "MPSH1", "22 Oct 2021");

        ArrayList<TrainingSchedule> inputTrainingList = new ArrayList<TrainingSchedule>();
        inputTrainingList.add(training1);
        inputTrainingList.add(training2);
        inputTrainingList.add(training3);
        inputTrainingList.add(training4);
        trainingList = new TrainingList(inputTrainingList);
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
    void getTrainingDetails() {
        final String input = "add /t /n October Friday Weekly Training 1 /a 1 Oct 2021 /v MPSH 1";
        final String expectedOutput = "Training Name: October Friday Weekly Training 1 | Venue: MPSH 1 | Time: 1 Oct "
                + "2021";
        TrainingSchedule training = Parser.getTrainingDescription(input);
        assertEquals(expectedOutput, training.toString());
    }

    @Test
    void deleteOneTrainingDetail() {
        trainingList.deleteTrainingSchedule(1);
        assertEquals(3, trainingList.getTrainingList().size());
    }

    @Test
    void addTrainingSchedule() {
        final String input = "add /t /n October Friday Weekly Training 5 /a 29 Oct 2021 /v MPSH1";
        trainingList.addTrainingSchedule(Parser.getTrainingDescription(input));
        assertEquals(trainingList.getTrainingName(5), "October Friday Weekly Training 5");
        assertEquals(trainingList.getTrainingTime(5), "29 Oct 2021");
        assertEquals(trainingList.getTrainingVenue(5), "MPSH1");
    }

    @Test
    void editTrainingSchedule() {
        final String input = "edit /t 1 /a 30 Oct 2021 /v MPSH 3";
        int index = Parser.getIndex(input);
        TrainingSchedule editedTraining = Parser.getTrainingDescription(input);
        new EditTraining(trainingList, index, editedTraining);
        //Parser.editTraining(trainingList, input);
        assertEquals(trainingList.getTrainingTime(1), "30 Oct 2021");
        assertEquals(trainingList.getTrainingVenue(1), "MPSH 3");
    }

}
