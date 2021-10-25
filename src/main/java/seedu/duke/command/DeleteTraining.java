package seedu.duke.command;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.Ui;

public class DeleteTraining {

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
