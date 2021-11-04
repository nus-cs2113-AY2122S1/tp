package seedu.duke.command;

import static seedu.duke.storage.TrainingStorage.writeTrainingFile;

import java.io.File;
import seedu.duke.command.exception.InvalidAddTrainingException;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.Ui;

/**
 * Adds a TrainingSchedule to the TrainingList.
 */
public class AddTraining {

    String invalidTrainingDetailsErrorMessage = "Invalid training details provided. Please check and ensure all "
            + "fields are valid.\nSYNTAX: add /t /n <NAME> /a <TIME> /v <VENUE>";
    String duplicateTrainingNameErrorMessage = "Training name already exists in the list. Please input a different "
            + "training name.";

    private String trainingName;
    private String trainingVenue;
    private String trainingTime;

    /**
     * Constructor. Adds a TrainingSchedule object to the TrainingList (and saves it to hard disk).
     * @param trainings TrainingList containing all trainings.
     * @param trainingSched TrainingSchedule to be added to the TrainingList.
     */
    public AddTraining(TrainingList trainings, TrainingSchedule trainingSched) {
        try {
            boolean validTraining = verifyTrainingDetails(trainingSched, trainings);
            if (validTraining) {
                trainings.addTrainingSchedule(trainingSched);
                Ui.printAddedTrainingMessage(trainingSched);
                File trainingFile = new File("CCATrainings.csv");
                writeTrainingFile(trainingFile, trainings);
            }
        } catch (InvalidAddTrainingException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Verify training details. Ensure training name, venue and time is in the correct format else throw
     * exception
     *
     * @param trainingSched Attendance to be added to AttendanceList.
     * @return true if all parameters given are valid
     * @throws InvalidAddTrainingException If there is an error with any of the parameters given.
     */
    public boolean verifyTrainingDetails(TrainingSchedule trainingSched, TrainingList trainings)
            throws InvalidAddTrainingException {
        trainingName = trainingSched.getTrainingName();
        trainingVenue = trainingSched.getTrainingVenue();
        trainingTime = trainingSched.getTrainingTime();

        for (TrainingSchedule training : trainings.getTrainingList()) {
            if (trainingName.trim().equalsIgnoreCase(training.getTrainingName().trim().toLowerCase())) {
                throw new InvalidAddTrainingException(duplicateTrainingNameErrorMessage);
            }
        }

        boolean validTrainingName = trainingName != null && !trainingName.trim().isEmpty();
        if (!validTrainingName) {
            throw new InvalidAddTrainingException(invalidTrainingDetailsErrorMessage);
        }

        boolean validTrainingVenue = trainingVenue != null && !trainingVenue.trim().isEmpty();
        if (!validTrainingVenue) {
            throw new InvalidAddTrainingException(invalidTrainingDetailsErrorMessage);
        }

        boolean validTrainingTime = trainingTime != null && !trainingTime.trim().isEmpty();
        if (!validTrainingTime) {
            throw new InvalidAddTrainingException(invalidTrainingDetailsErrorMessage);
        }

        return true;
    }
}
