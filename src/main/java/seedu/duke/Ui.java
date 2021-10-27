package seedu.duke;

import seedu.duke.attendance.Attendance;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;

public class Ui {

    private static final String LINE_SEPARATOR = ("_____________________________________________________");

    public static void printSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    public static void printGreeting() {

        String logo = " ,-----. ,-----.  ,---.      ,--.   ,--.  ,---.  ,--.  ,--.  ,---.   ,----.   ,------.,------.\n"
                + "'  .--./'  .--./ /  O  \\     |   `.'   | /  O  \\ |  ,'.|  | /  O  \\ '  .-./   |  .---'|  .--. '\n"
                + "|  |    |  |    |  .-.  |    |  |'.'|  ||  .-.  ||  |' '  ||  .-.  ||  | .---.|  `--, |  '--'.' \n"
                + "'  '--'\\'  '--'\\|  | |  |    |  |   |  ||  | |  ||  | `   ||  | |  |'  '--'  ||  `---.|  |\\  \\"
                + " \n"
                + " `-----' `-----'`--' `--'    `--'   `--'`--' `--'`--'  `--'`--' `--' `------' `------'`--' '--' ";
        System.out.println("Welcome to");
        System.out.println(logo);
        System.out.println("What would you like to do?");
    }

    public static void printWrongInputMessage() {
        System.out.println("Wrong input. Please key in --help for some help on how to use the programme. \n"
                + "If you would like to exit the programme, type 'bye'");
    }

    public static void printListAllMessage() {
        System.out.println("===== Would you like to list the full attendance sheet? (y / n) =====");
    }

    public static void printMatchingTrainingList(TrainingList trainings, String query) {
        // version 2.0
    }

    public static void printMatchingMemberList(MemberList members, String name) {
        if (members.getMemberListSize() > 0) {
            System.out.println("The following members matches your search \"" + name + "\"");
            for (Member member : members.getMemberList()) {
                System.out.println(member.toString());
            }
        } else {
            System.out.println("Sorry there is no members that matches your search \"" + name + "\"");
        }
    }

    public static void printDeletedMemberMessage(Member member) {
        System.out.println("You have removed member: " + "\n" + member);
    }

    public static void printDeleteMemberErrorMessage(String errorMessage, MemberList members, String name) {
        System.out.println(errorMessage);
        if (members.getMemberListSize() > 0) {
            System.out.println("The following members that have similar name as your delete \"" + name + "\"");
            for (Member member : members.getMemberList()) {
                System.out.println(member.toString());
            }
        } else {
            System.out.println("Sorry there is no members that have similar name as your delete \"" + name + "\"");
        }
    }

    public static void printDeletedTrainingMessage(TrainingSchedule training) {
        System.out.println("You have removed training entry: " + "\n" + training.toString());
    }

    public static void printDeletedAttendanceMessage(Attendance attendance) {
        System.out.println("You have removed attendance entry: " + "\n" + attendance);
    }

    public static void printAddedTrainingMessage(TrainingSchedule training) {
        System.out.println("Added a Training entry:\n" + training);
    }

    public static void printAddedMemberMessage(Member member) {
        System.out.println("Added a Member: " + member);
    }

    public static void printAddedAttendanceMessage(Attendance attendance) {
        System.out.println("Added an Attendance entry: " + "\n" + attendance);
    }

    public static void printEditMessage(Member oldMember, Member newMember) {
        System.out.println("Edited member: " + oldMember);
        System.out.println("To become:  " + newMember);
    }

    public static void printExitMessage() {
        System.out.println("You have successfully exited the programme.");
        printSeparator();
    }

    public static void printList(MemberList members) {
        if (members.getMemberListSize() == 0) {
            System.out.println("Member list is empty!");
        }
        int display = 1;
        for (Member member : members.getMemberList()) {
            System.out.println("[" + display + "] " + member.toString());
            display++;
        }
    }

    public static void printList(TrainingList trainings) {
        if (trainings.getTrainingListSize() == 0) {
            System.out.println("Training schedule list is empty!");
        }
        int display = 1;
        for (TrainingSchedule trainingEntries : trainings.getTrainingList()) {
            System.out.println("[" + display + "] " + trainingEntries.toString());
            display++;
        }
    }

    public static void printList(AttendanceList attendanceList) {
        if (attendanceList.getAttendanceListSize() == 0) {
            System.out.println("Attendance list is empty!");
        }
        int display = 1;
        for (Attendance attendance : attendanceList.getAttendanceList()) {
            System.out.println("[" + display + "] " + attendance.toString());
            display++;
        }
    }

    public static void printHelp() {
        System.out.println("usage: " + "\n"
                + "    add [/m </n MEMBER_NAME> </s STUDENT_NUMBER> </g GENDER> </p PHONE NUMBER>] \n"
                + "    add [/t </n TRAINING_NAME> </a TRAINING_TIME> </v TRAINING_VENUE>] \n"
                + "    add [/att </m MEMBER_NAME> </n TRAINING_NAME> </d 1_OR_0>] \n"
                + "    delete [/m <MEMBER_INDEX_NUMBER>] \n"
                + "    delete [/t <TRAINING_INDEX_NUMBER>] \n"
                + "    delete [/att /t <TRAINING_NAME> /i <ATTENDANCE_INDEX_TO_DELETE>] \n"
                + "    edit [/m <MEMBER_INDEX_NUMBER> </n MEMBER_NAME> </s STUDENT_NUMBER> </g GENDER> </p PHONE "
                + "NUMBER>]\n"
                + "    edit [/t <TRAINING_INDEX_NUMBER> </n TRAINING_NAME> </a TRAINING_TIME> </v TRAINING_VENUE>] \n"
                + "    list [/m] [/t] \n"
                + "    list [/att /t <TRAINING_NAME> [/d <1_OR_0>]] \n"
                + "    bye -- exits the programme.");
    }
}
