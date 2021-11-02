package seedu.duke.attendance;

public class Attendance {

    protected String memberName;
    protected String trainingName;
    /* 1 if present, 0 if absent */
    protected String attended;
    /* Index of attendance entry from the arraylist. */
    protected int index;

    /**
     * Constructor for any type of Attendance.
     * @param memberName          Name of member
     * @param trainingName        Name of training event
     * @param attended            attendance status
     */
    public Attendance(String memberName, String trainingName, String attended) {
        this.memberName = memberName;
        this.trainingName = trainingName;
        this.attended = attended;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getAttended() {
        return attended;
    }

    /**
     * Formats description of attendance to be displayed to user.
     *
     * @return Formatted string of an attendance
     */
    @Override
    public String toString() {
        return String.format("Name: %s | Training Name: %s | Present: [%s] ",
                memberName, trainingName, attended);
    }
}
