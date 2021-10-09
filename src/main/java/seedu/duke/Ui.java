package seedu.duke;

import seedu.duke.attendance.Attendance;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;

public class Ui {

    public static void printGoodbyeMessage() {
        System.out.println("Bye!");
    }

    public static void printList(MemberList members) {
        int display = 1;
        for (Member member : members.getMemberList()) {
            System.out.println("[" + display + "] " + member.toString());
            display++;
        }
    }

    public static void printList(TrainingList trainings) {
        int display = 1;
        for (TrainingSchedule trainingEntries : trainings.getTrainingList()) {
            System.out.println("[" + display + "] " + trainingEntries.toString());
            display++;
        }
    }

    public static void printList(AttendanceList attendanceList) {
        int display = 1;
        for (Attendance attendance : attendanceList.getAttendanceList()) {
            System.out.println("[" + display + "] " + attendance.toString());
            display++;
        }
    }
}
