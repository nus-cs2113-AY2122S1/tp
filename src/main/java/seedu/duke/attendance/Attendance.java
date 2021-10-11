package seedu.duke.attendance;

import seedu.duke.member.Member;
import seedu.duke.training.TrainingSchedule;

public class Attendance {

    protected Member member;
    protected TrainingSchedule schedule;

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
        return String.format("Name: %s | Student Number: %s | Training Name: %s | Venue: %s | Time: %s",
                member.getName(), member.getStudentNumber(), schedule.getTrainingName(),
                schedule.getTrainingVenue(), schedule.getTrainingTime());
    }
}
