package seedu.duke.training;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertTrue(trainingList.getTrainingList().size() == 0);
    }

}
