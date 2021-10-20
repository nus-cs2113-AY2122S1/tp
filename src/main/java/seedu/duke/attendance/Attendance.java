package seedu.duke.attendance;

import seedu.duke.member.Member;
import seedu.duke.training.TrainingSchedule;

public class Attendance {

    protected Member member;
    protected TrainingSchedule schedule;

    /**
     * Constructor for any type of Attendance.
     * @param member          Name of member
     * @param schedule        Name of training event
     */
    public Attendance(Member member, TrainingSchedule schedule) {
        setMember(member);
        setSchedule(schedule);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setSchedule(TrainingSchedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Formats description of attendance to be displayed to user.
     *
     * @return Formatted string of an attendance
     */
    @Override
    public String toString() {
        return String.format("Name: %s | Training Name: %s | Present: [%s] ",
                member.getName(), schedule.getTrainingName(), member.getAttendance());
    }
}
