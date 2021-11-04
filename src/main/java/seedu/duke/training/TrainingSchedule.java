package seedu.duke.training;

public class TrainingSchedule {

    protected String trainingName;
    protected String trainingVenue;
    protected String trainingTime;
    protected int trainingIndex;

    Boolean completed = false;

    /**
     * Constructor for any type of TrainingSchedule.
     * @param name          Name of event
     * @param venue         Venue of event
     * @param time          Time of event
     * @param index         Index of event in TrainingList
     */
    public TrainingSchedule(String name, String venue, String time, int index) {
        setTrainingName(name);
        setTrainingVenue(venue);
        setTrainingTime(time);
        setTrainingIndex(index);
    }

    public TrainingSchedule(String name, String venue, String time) {
        setTrainingName(name);
        setTrainingVenue(venue);
        setTrainingTime(time);
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public void setTrainingVenue(String trainingVenue) {
        this.trainingVenue = trainingVenue;
    }

    public void setTrainingTime(String trainingTime) {
        this.trainingTime = trainingTime;
    }

    public void setTrainingIndex(int trainingIndex) {
        this.trainingIndex = trainingIndex;
    }

    public void setTrainingComplete() {
        this.completed = true;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public String getTrainingVenue() {
        return trainingVenue;
    }

    public String getTrainingTime() {
        return trainingTime;
    }

    public int getTrainingIndex() {
        return trainingIndex;
    }

    public Boolean getTrainingStatus() {
        return completed;
    }

    /**
     * Formats description of training schedule to user.
     *
     * @return Formatted string of training schedule
     */
    @Override
    public String toString() {
        return String.format("[%d] Training Name: %s | Venue: %s | Time: %s", this.trainingIndex, this.trainingName,
                this.trainingVenue, this.trainingTime);
    }
}