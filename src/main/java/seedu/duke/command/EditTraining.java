package seedu.duke.command;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.Ui;

public class EditTraining {
    public EditTraining(TrainingList trainings, int index, TrainingSchedule newTraining) {
        try {
            assert index >= 1;

            TrainingSchedule trainingToChange = trainings.getTrainingList().get(index-1);

            if (!newTraining.getTrainingName().equals("")) {
                trainingToChange.setTrainingName(newTraining.getTrainingName());
            }

            if (!newTraining.getTrainingTime().equals("")) {
                trainingToChange.setTrainingTime(newTraining.getTrainingTime());
            }

            if (!newTraining.getTrainingVenue().equals("")) {
                trainingToChange.setTrainingVenue(newTraining.getTrainingVenue());
            }

            trainings.getTrainingList().set(index-1, trainingToChange);
            //TODO: Print and Save

        } catch (AssertionError e) {
            System.out.println("Index to edit must be an integer >= 1");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unfortunately, the index you entered is invaid.");
        }
    }
}
