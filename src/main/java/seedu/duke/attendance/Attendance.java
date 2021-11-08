//@@author izdiyadfrhn

package seedu.duke.attendance;

public class Attendance {

    protected String memberName;
    protected String trainingName;
    protected String attended;
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

    public Attendance(Attendance attendance) {
        setMemberName(attendance.memberName);
        setTrainingName(attendance.trainingName);
        setAttended(attendance.attended);
        setIndex(attendance.index);
    }

    public void setMemberName(String name) {
        this.memberName = name;
    }

    public void setTrainingName(String name) {
        this.trainingName = name;
    }

    public void setAttended(String name) {
        this.attended = name;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public int getIndex() {
        return index;
    }

    /**
     * Formats description of attendance to be displayed to user.
     *
     * @return Formatted string of an attendance
     */
    @Override
    public String toString() {
        return String.format("[%d] Name: %s | Training Name: %s | Present: [%s] ", this.index,
                this.memberName, this.trainingName, this.attended);
    }
}