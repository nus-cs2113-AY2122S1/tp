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
        if (trainings.getTrainingListSize() > 0) {
            System.out.println("The following trainings matches your search \"" + query + "\"");
            for (TrainingSchedule training : trainings.getTrainingList()) {
                System.out.println(training.toString());
            }
        } else {
            System.out.println("Sorry, there is no training that matches your search \"" + query + "\"");
        }
    }

    public static void printCommandErrorMessage(String errorMessage, Keyword keyword) {
        System.out.println(errorMessage + " Please refer to the format for command below:");
        switch (keyword) {
        case ADD_MEMBER_KEYWORD:
            System.out.println("    add [/m </n MEMBER_NAME> </s STUDENT_NUMBER> </g GENDER> </p PHONE NUMBER>]");
            break;
        case EDIT_MEMBER_KEYWORD:
            System.out.println("    edit [/m <MEMBER_INDEX_NUMBER> </n MEMBER_NAME> </s STUDENT_NUMBER> </g GENDER> "
                    + "</p PHONE NUMBER>]");
            break;
        default:
            printHelp();
            break;
        }
    }

    public static void printAddMemberErrorMessage(String errorMessage) {
        System.out.println("Error when adding member:");
        System.out.println("    " + errorMessage);
    }

    public static void printEditMemberErrorMessage(String errorMessage) {
        System.out.println("Error when editing member:");
        System.out.println("    " + errorMessage);
    }

    public static void printDeleteMemberErrorMessage(String errorMessage) {
        System.out.println("Error when deleting member:");
        System.out.println("    " + errorMessage);
    }

    public static void printDeleteMemberErrorMessage(String errorMessage, MemberList members, String name) {
        System.out.println(errorMessage);
        if (members.getMemberListSize() > 0) {
            System.out.println("The following members that have similar name as your delete \"" + name + "\"");
            for (Member member : members.getMemberList()) {
                System.out.println(member.toString());
            }
        }
    }

    public static void printFindMemberErrorMessage(String errorMessage) {
        System.out.println("Error when finding member:");
        System.out.println("    " + errorMessage);
    }

    public static void printCreateMemberErrorMessage(String errorMessage) {
        System.out.println("Error when creating member:");
        System.out.println(errorMessage);
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

    public static void printDeletedTrainingMessage(TrainingSchedule training) {
        System.out.println("You have removed training entry: " + "\n" + training.toString());
    }

    public static void printEmptyMembersFile() {
        System.out.println("Members File is Empty!!!");
    }

    public static void printEmptyTrainingFile() {
        System.out.println("Training File is Empty!!!");
    }

    public static void printQuestionToList() {
        System.out.println("Please enter only a 'y' or 'n'.");
    }

    public static void printArrow() {
        System.out.print("=> ");
    }

    public static void printMissingTraining() {
        System.out.println("No such Training Name is in our attendance records.");
    }

    public static void printDeletedAttendanceMessage(Attendance attendance) {
        System.out.println("You have removed attendance entry: " + "\n" + attendance);
    }

    public static void printAddedTrainingMessage(TrainingSchedule training) {
        System.out.println("Added a Training entry:\n" + training);
    }

    public static void printAddedMemberMessage(Member member) {
        System.out.println("Added a Member: \n" + member);
    }

    public static void printAddedAttendanceMessage(Attendance attendance) {
        System.out.println("Added an Attendance entry: " + "\n" + attendance);
    }

    public static void printEditMessage(Member oldMember, Member newMember) {
        System.out.println("Edited member:\n" + oldMember);
        System.out.println("To become:\n" + newMember);
    }

    public static void printEditTrainingMessage(TrainingSchedule oldTraining, TrainingSchedule newTraining) {
        System.out.println("Edited Training:\n" + oldTraining.toString());
        System.out.println("To become:\n" + newTraining.toString());
    }

    public static void printExitMessage() {
        System.out.println("You have successfully exited the programme.");
        printSeparator();
    }

    public static void printNoCommasMessage() {
        System.out.println("Invalid input! Inputs should not contain any commas (i.e. ',')");
        printSeparator();
    }

    public static void printValidNumberNeeded() {
        System.out.println("Please key in a valid number!");
    }

    public static void printList(MemberList members) {
        if (members.getMemberListSize() == 0) {
            System.out.println("Member list is empty!");
        }
        for (Member member : members.getMemberList()) {
            System.out.println(member.toString());
        }
    }

    public static void printList(TrainingList trainings) {
        if (trainings.getTrainingListSize() == 0) {
            System.out.println("Training schedule list is empty!");
        }
        for (TrainingSchedule trainingEntries : trainings.getTrainingList()) {
            System.out.println(trainingEntries.toString());
        }
    }

    public static void printList(AttendanceList attendanceList) {
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
                + "    list [/att /t <TRAINING_NAME>] \n"
                + "    find [/m <MEMBER_NAME>]  \n"
                + "    find [/t <TRAINING_NAME>] \n"
                + "    bye -- exits the programme.");
    }
}
