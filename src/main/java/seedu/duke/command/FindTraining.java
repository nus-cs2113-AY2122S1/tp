package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.training.TrainingList;

/**
 * Finds TrainingSchedule(s) from TrainingList given a String query.
 */
public class FindTraining {

    /**
     * Given a String query, find and print all TrainingSchedules with names containing the query.
     * @param trainings TrainingList containing all TrainingSchedules
     * @param name query to match against all TrainingName
     */
    public FindTraining(TrainingList trainings, String name) {
        if (name.trim().equals("")) {
            System.out.println("Name should not be empty!");
            return;
        }

        TrainingList matchingTrainings = trainings.findTraining(name);

        Ui.printMatchingTrainingList(matchingTrainings, name);
    }
}
