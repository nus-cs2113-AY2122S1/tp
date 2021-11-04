package seedu.duke.command;

import static seedu.duke.storage.TrainingStorage.writeTrainingFile;

import seedu.duke.Ui;
import seedu.duke.command.exception.InvalidAddTrainingException;


import java.io.File;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;

/**
 * Edits a TrainingSchedule located in TrainingList.
 */
public class EditTraining {
    String duplicateTrainingNameErrorMessage = "Training name already exists in the list. Please input a different "
            + "training name.";

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
            final String oldName = trainingToChange.getTrainingName();
            final String oldVenue = trainingToChange.getTrainingVenue();
            final String oldTime = trainingToChange.getTrainingTime();
            final int oldIndex = trainingToChange.getTrainingIndex();

            if (!toChange.getTrainingName().equals("")) {
                if (verifyTrainingDetails(toChange.getTrainingName(), trainings)) {
                    trainingToChange.setTrainingName(toChange.getTrainingName().toUpperCase());
                }
            }

            if (!toChange.getTrainingTime().equals("")) {
                trainingToChange.setTrainingTime(toChange.getTrainingTime().toUpperCase());
            }

            if (!toChange.getTrainingVenue().equals("")) {
                trainingToChange.setTrainingVenue(toChange.getTrainingVenue().toUpperCase());
            }

            trainings.getTrainingList().set(index - 1, trainingToChange);
            File trainingFile = new File("CCATrainings.csv");
            Ui.printEditTrainingMessage(new TrainingSchedule(oldName, oldVenue, oldTime, oldIndex), trainingToChange);
            writeTrainingFile(trainingFile, trainings);

        } catch (AssertionError e) {
            System.out.println("Index to edit must be an integer >= 1");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unfortunately, the index you entered is invaid.");
        } catch (InvalidAddTrainingException e) {
            System.out.println(e.getMessage());
        }
    }

    boolean verifyTrainingDetails(String name, TrainingList trainings) throws InvalidAddTrainingException {
        for (TrainingSchedule training : trainings.getTrainingList()) {
            if (name.trim().equalsIgnoreCase(training.getTrainingName().trim())) {
                throw new InvalidAddTrainingException(duplicateTrainingNameErrorMessage);
            }
        }

        return true;
    }
}
