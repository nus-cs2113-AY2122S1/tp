package seedu.duke.command;

import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.Ui;

/**
 * Deletes a TrainingSchedule from the TrainingList.
 */
public class DeleteTraining {

    /**
     * Constructor. Deletes a TrainingSchedule from the TrainingList given its index.
     * @param trainings TrainingList to delete TrainingSchedule from.
     * @param index Index of the TrainingSchedule to delete. Note that the actual index is index-1.
     */
    public DeleteTraining(TrainingList trainings, int index) {
        try {
            assert index >= 1;
            TrainingSchedule toDelete = trainings.deleteTrainingSchedule(index);
            Ui.printDeletedTrainingMessage(toDelete);
            //Update save file
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index number");
        } catch (AssertionError e) {
            System.out.println("The index to delete must be an integer >= 1");
        }
    }
}
