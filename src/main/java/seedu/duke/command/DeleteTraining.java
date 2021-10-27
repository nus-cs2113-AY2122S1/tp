package seedu.duke.command;

import java.util.*;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.Ui;

/**
 * Deletes a TrainingSchedule from the TrainingList.
 */
public class DeleteTraining {

    /**
     * Constructor. Converts user query into either Integer or String and deletes a TrainingSchedule based on query.
     * @param trainings TrainingList of TrainingSchedules.
     * @param parameter Query input by user. Should either resolve to an Integer or String.
     */
    public DeleteTraining(TrainingList trainings, Object parameter) {
        if (parameter instanceof Integer) {
            deleteTrainingByIndex(trainings, (int) parameter);
        } else if (parameter instanceof String) {
            deleteTrainingByString(trainings, (String) parameter);
        } else {
            System.out.println("Error processing input. Please input either a String or an Integer");
        }
    }

    /**
     * Deletes a TrainingSchedule from the TrainingList given its index.
     *
     * @param trainings TrainingList to delete TrainingSchedule from.
     * @param index     Index of the TrainingSchedule to delete. Note that the actual index is index-1.
     */
    public void deleteTrainingByIndex(TrainingList trainings, int index) {
        try {
            assert index >= 1;
            TrainingSchedule toDelete = trainings.deleteTrainingSchedule(index);
            trainings.updateIndex();
            Ui.printDeletedTrainingMessage(toDelete);
            //Update save file
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index number");
        } catch (AssertionError e) {
            System.out.println("The index to delete must be an integer >= 1");
        }
    }

    /**
     * Deletes a TrainingSchedule from the TrainingList given the String name.
     * If multiple matches are found, take in user input which should be an integer representing index of
     * TrainingSchedule to delete.
     *
     * @param trainings TrainingList of all TrainingSchedules
     * @param name String name to match against trainings
     */
    public void deleteTrainingByString(TrainingList trainings, String name) {
        TrainingList candidateList = trainings.findTraining(name);

        if (candidateList.getTrainingListSize() == 0) {
            System.out.println("Could not find any matching training names for \"" + name + "\"");
        }
        else if (candidateList.getTrainingListSize() == 1) {
            deleteTrainingByIndex(trainings, candidateList.getTrainingList().get(0).getTrainingIndex());
        }
        else if (candidateList.getTrainingListSize() > 1) {
            System.out.println("Below are the possible matches. Please key in the INDEX NUMBER ONLY to delete");
            for (TrainingSchedule training : candidateList.getTrainingList()) {
                System.out.println(training.toString());
            }

            Scanner userInput = new Scanner(System.in);
            String input = "";
            System.out.print("=> ");
            input = userInput.nextLine();

            try {
                int indexToDelete = Integer.parseInt(input);
                System.out.println("Trying to delete index " + indexToDelete);
                deleteTrainingByIndex(trainings, indexToDelete);
            } catch (NumberFormatException e) {
                System.out.println("Please key in a number only. Returning to main search.");
            }
        }

    }
}
