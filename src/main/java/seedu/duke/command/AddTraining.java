package seedu.duke.command;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.Ui;

public class AddTraining {

    public AddTraining(TrainingList trainings, TrainingSchedule trainingSched) {
        trainings.addTrainingSchedule(trainingSched);
        //Save Data
        Ui.printAddedTrainingMessage(trainingSched);
    }
}
