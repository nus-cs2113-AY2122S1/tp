package seedu.duke.command;

import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;

/**
 * Edits a TrainingSchedule located in TrainingList.
 */
public class EditTraining {

    /**
     * Edits a TrainingSchedule in TrainingList. TrainingSchedule is located by index.
     * @param trainings TrainingList containing all TrainingSchedules.
     * @param index Index of TrainingSchedule to change. Note that the actual index is index-1.
     * @param toChange TrainingSchedule containing details to be changed.
     */
    public EditTraining(TrainingList trainings, int index, TrainingSchedule toChange) {
        try {
            assert index >= 1;

            TrainingSchedule trainingToChange = trainings.getTrainingList().get(index - 1);

            if (!toChange.getTrainingName().equals("")) {
                trainingToChange.setTrainingName(toChange.getTrainingName());
            }

            if (!toChange.getTrainingTime().equals("")) {
                trainingToChange.setTrainingTime(toChange.getTrainingTime());
            }

            if (!toChange.getTrainingVenue().equals("")) {
                trainingToChange.setTrainingVenue(toChange.getTrainingVenue());
            }

            trainings.getTrainingList().set(index - 1, trainingToChange);
            //TODO: Print and Save

        } catch (AssertionError e) {
            System.out.println("Index to edit must be an integer >= 1");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unfortunately, the index you entered is invaid.");
        }
    }
}
