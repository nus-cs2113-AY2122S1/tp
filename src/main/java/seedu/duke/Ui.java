package seedu.duke;

import java.util.ArrayList;

public class Ui {
    private static final String LINE_SEPARATOR = ("_____________________________________________________");

    public static void printSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    public static void printMatchingTrainingList(ArrayList<TrainingSchedule> trainings, String query) {
        int count = 0;
        if (trainings.size() == 0) {
            System.out.println("I'm sorry, there is no training entry containing your find query.");
        } else {
            System.out.println("Here is your list of training entries containing " + "' "
                    + Parser.getQueryDescription(query) + "'" + ":");
            for (TrainingSchedule training : trainings) {
                if (trainings.get(count) != null) {
                    count++;
                    System.out.println(count + ". " + training);
                }
            }
        }
    }

    public static void printMatchingMemberList(ArrayList<Member> members, String query) {
        int count = 0;
        if (members.size() == 0) {
            System.out.println("I'm sorry, there are no members containing your find query.");
        } else {
            System.out.println("Here is your list of members containing " + "' "
                    + Parser.getQueryDescription(query) + "'" + ":");
            for (Member member : members) {
                if (members.get(count) != null) {
                    count++;
                    System.out.println(count + ". " + member);
                }
            }
        }
    }

    public static void printMemberList(ArrayList<Member> members) {
        int count = 0;
        if (members.size() == 0) {
            System.out.println("Well... your list of members is looking very scarce...");
        } else {
            System.out.println("Here is your list of members:");
            for (Member member : members) {
                if (members.get(count) != null) {
                    count++;
                    System.out.println(count + ". " + member);
                }
            }
        }
    }
    public static void printTrainingList(ArrayList<TrainingSchedule> trainings) {
        int count = 0;
        if (trainings.size() == 0) {
            System.out.println("Well... your list of members is looking very scarce...");
        } else {
            System.out.println("Here is your list of members:");
            for (TrainingSchedule training : trainings) {
                if (trainings.get(count) != null) {
                    count++;
                    System.out.println(count + ". " + training);
                }
            }
        }
    }

    public static void printDeletedMemberMessage(Member member) {
        System.out.println("You have removed member: " + "\n" + member);
    }

    public static void printDeletedTrainingMessage(TrainingSchedule training) {
        System.out.println("You have removed training entry: " + "\n" + training);
    }

    public static void printExitMessage() {
        System.out.println("You have successfully exited the programme.");
        System.out.println(LINE_SEPARATOR);
    }
}
