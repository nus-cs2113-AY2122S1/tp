package seedu.duke;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.training.TrainingList;

public class Parser {

    public static boolean hasListMemberKeyword(String arg) {
        return arg.trim().matches("^list /m");
    }

    public static boolean hasListTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("list /t");
    }

    public static boolean hasAddMemberKeyword(String arg) {
        return arg.trim().toLowerCase().contains("add /m");
    }

    public static boolean hasAddTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("add /t");
    }

    public static boolean hasDeleteMemberKeyword(String arg) {
        return arg.trim().toLowerCase().contains("delete /m");
    }

    public static boolean hasDeleteTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("delete /t");
    }
    public static boolean hasMemberKeyword(String arg) {
        return arg.trim().toLowerCase().contains("delete /m");
    }

    public static boolean hasTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("delete /m");
    }

    public static boolean hasFindMemberKeyword(String arg) {
        return arg.trim().toLowerCase().contains("find /m");
    }

    public static boolean hasFindTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("find /t");
    }


    /**
     * Returns the required value for keyword which is the first word keyed in by user.
     *
     * @param query user raw data input.
     * @return keyword value from Keyword enum class.
     */
    public static Keyword getKeywordStatus(String query) {
        Keyword keyword;
        if (hasMemberKeyword(query)) {
            keyword = Keyword.MEMBER_ENTRY;
        } else if (hasTrainingKeyword(query)) {
            keyword = Keyword.TRAINING_SCHEDULE_ENTRY;
        } else if (hasAddMemberKeyword(query)) {
            keyword = Keyword.ADD_MEMBER_KEYWORD;
        } else if (hasAddTrainingKeyword(query)) {
            keyword = Keyword.ADD_TRAINING_KEYWORD;
        } else if (hasListMemberKeyword(query)) {
            keyword = Keyword.LIST_MEMBER_KEYWORD;
        } else if (hasListTrainingKeyword(query)) {
            keyword = Keyword.LIST_TRAINING_KEYWORD;
        } else if (query.trim().equals("bye")) {
            keyword = Keyword.EXIT_KEYWORD;
        } else if (hasDeleteMemberKeyword(query)) {
            keyword = Keyword.DELETE_MEMBER_KEYWORD;
        } else if (hasDeleteTrainingKeyword(query)) {
            keyword = Keyword.DELETE_TRAINING_KEYWORD;
        } else if (hasFindMemberKeyword(query)) {
            keyword = Keyword.FIND_MEMBER_KEYWORD;
        } else if (hasFindTrainingKeyword(query)) {
            keyword = Keyword.FIND_TRAINING_KEYWORD;
        } else {
            keyword = Keyword.NO_KEYWORD;
        }
        return keyword;
    }

    /**
     * Returns the description of the task only, without the date or the keyword.
     *
     * @param query user raw data input.
     * @return description of task.
     */
    public static TrainingSchedule getTrainingDescription(String query) {
        System.out.println(query);
        String name = "";
        String venue = "";
        String time = "";

        String regex = "(\\/[a-z])+";

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(query);

        String[] words = query.trim().split(regex);

        int wordIndex = 1;
        while (matcher.find()) {
            switch (matcher.group()){
            case "/t":
                name = words[wordIndex].trim();
                break;
            case "/a":
                time = words[wordIndex].trim();
                break;
            case "/v":
                venue = words[wordIndex].trim();
                break;
            }

            wordIndex++;
        }

        return new TrainingSchedule(name, venue, time);
    }


    public static Member getMemberDetails(String query) {
        String regex = "(\\/[a-z])+";

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(query);

        String[] words = query.trim().split(regex);

        String name = "";
        String studentNumber = "";
        char gender = ' ';
        int phoneNumber = 0;

        int wordIndex = 1;
        while (matcher.find()) {
            switch (matcher.group()){
            case "/m":
                name = words[wordIndex].trim();
                break;
            case "/s":
                studentNumber = words[wordIndex].trim();
                break;
            case "/g":
                gender = words[wordIndex].trim().charAt(0);
                break;
            case "/p":
                phoneNumber = Integer.parseInt(words[wordIndex].trim());
                break;
            }
            wordIndex++;
        }

        return new Member(name, studentNumber,gender,phoneNumber);
    }


    /**
     * Function creates a new member to be input in MemberList Class.
     *
     * @param members MemberList which contains list of members
     * @param query user input
     */
    public static void makeMemberEntry(MemberList members, String query) {
        Member member = getMemberDetails(query);
        members.addMember(member);
        System.out.println("Added a Member: " + member);
    }

     /* *
     * Creates a TrainingSchedule to put into TrainingList
     *
     * @param trainings TrainingList containing all TrainingSchedule
     * @param query User input command to parse
     */
    public static void makeTrainingEntry(TrainingList trainings, String query) {
        TrainingSchedule training = getTrainingDescription(query);
        trainings.addTrainingSchedule(training);
        System.out.println("Added a Training entry: " + training);
    }

    /**
     * Function finds tasks with descriptions matching the user's query and adds them to a new ArrayList. If no matching
     * words are found, the user will be notified.
     *
     * @param trainings ArrayList of tasks
     * @param query user input
     */
    public static void findInTraining(TrainingList trainings, String query) {
        //Leave for v2.0
    }

    /**
     * Function finds tasks with descriptions matching the user's query and adds them to a new ArrayList. If no matching
     * words are found, the user will be notified.
     *
     * @param members ArrayList of tasks
     * @param query user input
     */
    public static void findInMembers(MemberList members, String query) {
        //Leave for v2.0
    }

    /**
     * Function deletes an item from the ArrayList task.
     *
     * @param members ArrayList of tasks
     * @param query user input
     */
    public static void deleteMember(MemberList members, String query) {
        //Settled by Teck Hwee. Overwrite in Merge Conflict.
    }

    public static void wrongInputTypeMessage() {
        //Leave for later
    }

    public static void deleteTraining(TrainingList trainings, String entry) {
        int trainingIndex = -1;

        String regex = "(\\/[a-z])+";

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(entry);

        String[] words = entry.trim().split(regex);

        int wordIndex = 1;
        while (matcher.find()) {
            if (matcher.group().equals("/t")) {
                trainingIndex = Integer.parseInt(words[wordIndex].trim());
            }
            wordIndex++;
        }

        if (trainingIndex != -1) {
            TrainingSchedule toDelete = trainings.deleteTrainingSchedule(trainingIndex);
            System.out.println("You have deleted: \n" + toDelete.toString());
        }

    }
}

