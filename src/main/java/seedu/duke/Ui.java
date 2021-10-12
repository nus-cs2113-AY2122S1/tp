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
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("What would you like to do?");
    }

    public static void printWrongInputMessage() {
        System.out.println("Wrong commands have been input. To get help on the overview and guide of how to use the "
                + "programme, please take a look at our user guide at https://github.com/AY2122S1-CS2113T-F12-4/tp");
    }

    public static void printMatchingTrainingList(TrainingList trainings, String query) {
        // version 2.0
    }

    public static void printMatchingMemberList(MemberList members, String query) {
        // version 2.0
    }

    public static void printDeletedMemberMessage(Member member) {
        System.out.println("You have removed member: " + "\n" + member);
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
}
