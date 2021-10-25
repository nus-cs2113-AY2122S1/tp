package seedu.duke.training;

import java.util.ArrayList;

public class TrainingList {

    private final ArrayList<TrainingSchedule>  trainingList;

    public TrainingList() {
        trainingList = new ArrayList<TrainingSchedule>();
    }

    public TrainingList(ArrayList<TrainingSchedule> inputTrainingList) {
        this.trainingList = inputTrainingList;
    }

    public ArrayList<TrainingSchedule> getTrainingList() {
        return trainingList;
    }

    public int getTrainingListSize() { //added by xy
        return trainingList.size();
    }

    public void addTrainingSchedule(TrainingSchedule schedule) {
        trainingList.add(schedule);
    }

    public String getTrainingName(int index) {
        assert (index - 1) >= 0;
        return trainingList.get(index - 1).getTrainingName();
    }

    public String getTrainingTime(int index) {
        assert (index - 1) >= 0;
        return trainingList.get(index - 1).getTrainingTime();
    }

    public String getTrainingVenue(int index) {
        assert (index - 1) >= 0;
        return trainingList.get(index - 1).getTrainingVenue();
    }

    /**
     * Deletes training schedule based on index input by user. Index must be valid.
     *
     * @param trainingNum Index of the TrainingSchedule user wishes to delete
     * @return TrainingSchedule to be deleted
     * @throws IndexOutOfBoundsException Thrown when an invalid index is input to be deleted
     */
    public TrainingSchedule deleteTrainingSchedule(int trainingNum) throws IndexOutOfBoundsException {
        try {
            int indexToDelete = trainingNum - 1;
            TrainingSchedule training = trainingList.get(indexToDelete);
            trainingList.remove(indexToDelete);
            return training;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }
}
