package seedu.duke.attendance;

import seedu.duke.member.Member;
import seedu.duke.training.TrainingSchedule;

public class Attendance {

    protected String memberName;
    protected String trainingName;
    protected String attended;

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
