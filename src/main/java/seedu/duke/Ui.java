package seedu.duke;

import seedu.duke.attendance.Attendance;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.member.*;
import seedu.duke.training.*;

public class Ui {

    public static void printGoodbyeMessage() {
        System.out.println("Bye!");
    }

    public static void printList(MemberList members) {
        System.out.println("Members test");
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
    public static void printWrongInputMessage() {
        System.out.println("Wrong input. Please key in --help for some help on how to use the programme.");
    }
    public static void printHelp() {
        System.out.println("usage: " + "\n" +
                "    add [/m </n MEMBER_NAME> </s STUDENT_NUMBER> </g GENDER> </p PHONE NUMBER>] \n" +
                "    add [/t </n TRAINING_NAME> </a TRAINING_TIME> </v TRAINING_VENUE>] \n" +
                "    add [/att </m MEMBER_NAME> </n TRAINING_NAME> </d 1_OR_0>] \n" +
                "    delete [/m <MEMBER_INDEX_NUMBER>] \n" +
                "    delete [/t <TRAINING_INDEX_NUMBER>] \n" +
                "    delete [/att <ATTENDANCE_INDEX_NUMBER>] \n" +
                "    edit [/m <MEMBER_INDEX_NUMBER> </n MEMBER_NAME> </s STUDENT_NUMBER> </g GENDER> </p PHONE "
                + "NUMBER>]\n " +
                "    edit [/t <TRAINING_INDEX_NUMBER> </n TRAINING_NAME> </a TRAINING_TIME> </v TRAINING_VENUE>] \n" +
                "    list [/m] [/t] [/att]");
    }
}
