package seedu.duke.command;

import seedu.duke.command.exception.InvalidAddTrainingException;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.Ui;

/**
 * Adds a TrainingSchedule to the TrainingList.
 */
public class AddTraining {


    String invalidTrainingNameErrorMessage = "Blank training name provided. Please input a proper training name.";
    String invalidTrainingVenueErrorMessage = "Blank training venue provided. Please input a proper training venue.";
    String invalidTrainingTimeErrorMessage = "Blank training time provided. Please input a proper training time.";

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
            boolean validTraining = verifyTrainingDetails(trainingSched);
            if (validTraining) {
                trainings.addTrainingSchedule(trainingSched);
                //Save Data
                Ui.printAddedTrainingMessage(trainingSched);
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
    public boolean verifyTrainingDetails(TrainingSchedule trainingSched) throws InvalidAddTrainingException {
        trainingName = trainingSched.getTrainingName();
        trainingVenue = trainingSched.getTrainingVenue();
        trainingTime = trainingSched.getTrainingTime();

        boolean validTrainingName = trainingName != null && !trainingName.trim().isEmpty();
        if (!validTrainingName) {
            throw new InvalidAddTrainingException(invalidTrainingNameErrorMessage);
        }

        boolean validTrainingVenue = trainingVenue != null && !trainingVenue.trim().isEmpty();
        if (!validTrainingVenue) {
            throw new InvalidAddTrainingException(invalidTrainingVenueErrorMessage);
        }

        boolean validTrainingTime = trainingTime != null && !trainingTime.trim().isEmpty();
        if (!validTrainingTime) {
            throw new InvalidAddTrainingException(invalidTrainingTimeErrorMessage);
        }

        return true;
    }
}
