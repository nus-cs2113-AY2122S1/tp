package seedu.duke.command;

import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.Ui;

/**
 * Adds a TrainingSchedule to the TrainingList.
 */
public class AddTraining {

    /**
     * Constructor. Adds a TrainingSchedule object to the TrainingList (and saves it to hard disk).
     * @param trainings TrainingList containing all trainings.
     * @param trainingSched TrainingSchedule to be added to the TrainingList.
     */
    public AddTraining(TrainingList trainings, TrainingSchedule trainingSched) {
        trainings.addTrainingSchedule(trainingSched);
        //Save Data
        Ui.printAddedTrainingMessage(trainingSched);
    }
}
