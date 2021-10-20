package seedu.duke;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.attendance.Attendance;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.training.TrainingSchedule;
import seedu.duke.training.TrainingList;
import seedu.duke.attendance.AttendanceList;

public class Parser {

    public static boolean hasListMemberKeyword(String arg) {
        return arg.trim().matches("^list /m");
    }

    public static boolean hasListTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("list /t");
    }

    public static boolean hasListAttendanceKeyword(String arg) {
        return arg.trim().toLowerCase().contains("list /att");
    }

    public static boolean hasAddMemberKeyword(String arg) {
        return arg.trim().toLowerCase().contains("add /m");
    }

    public static boolean hasAddTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("add /t");
    }

    public static boolean hasAddAttendanceKeyword(String arg) {
        return arg.trim().toLowerCase().contains("add /att");
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

    public static boolean hasEditTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().contains("edit /t");
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
        } else if (hasAddAttendanceKeyword(query)) {
            keyword = Keyword.ADD_ATTENDANCE_KEYWORD;
        } else if (hasListMemberKeyword(query)) {
            keyword = Keyword.LIST_MEMBER_KEYWORD;
        } else if (hasListTrainingKeyword(query)) {
            keyword = Keyword.LIST_TRAINING_KEYWORD;
        } else if (hasListAttendanceKeyword(query)) {
            keyword = Keyword.LIST_ATTENDANCE_KEYWORD;
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
        } else if (hasEditTrainingKeyword(query)) {
            keyword = Keyword.EDIT_TRAINING_KEYWORD;
        } else if (query.trim().equals("--help")) {
            keyword = Keyword.HELP_KEYWORD;
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
            case "/n":
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

    /**
     * Creates Member class by input given by user
     *
     * @param query user raw data input.
     * @return Member according to user input.
     */
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
            case "/n":
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

        return new Member(name,studentNumber,gender,phoneNumber);
    }

    /**
     * Edit member by input given by user
     *
     * @param query user raw data input.
     * @return Edited member according to user input.
     */
    public static ArrayList<Member> editMemberDetails(MemberList members, String query) {
        String regex = "(\\/[a-z])+";

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(query);

        String[] words = query.trim().split(regex);

        Member editedMember = new Member();
        Member oldMember = new Member();
        String name = "";
        String studentNumber = "";
        char gender = ' ';
        int phoneNumber = 0;
        int memberNumber = 0;

        int wordIndex = 1;
        while (matcher.find()) {
            switch (matcher.group()){
            case "/m":
                memberNumber = Integer.parseInt(words[wordIndex].trim());
                oldMember = new Member(members.getMember(memberNumber));
                editedMember = members.getMember(memberNumber);
                break;
            case "/n":
                name = words[wordIndex].trim();
                editedMember.setName(name);
                break;
            case "/s":
                studentNumber = words[wordIndex].trim();
                editedMember.setStudentNumber(studentNumber);
                break;
            case "/g":
                gender = words[wordIndex].trim().charAt(0);
                editedMember.setGender(gender);
                break;
            case "/p":
                phoneNumber = Integer.parseInt(words[wordIndex].trim());
                editedMember.setPhoneNumber(phoneNumber);
                break;
            }
            wordIndex++;
        }
        ArrayList<Member> oldMemberAndEditedMember = new ArrayList<Member>();
        oldMemberAndEditedMember.add(oldMember);
        oldMemberAndEditedMember.add(editedMember);

        return oldMemberAndEditedMember;
    }

    public static Attendance getAttendanceDetails(String query) {
        String regex = "(\\/[a-z])+";

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(query);

        String[] words = query.trim().split(regex);

        String memberName = "";
        String studentNumber = "";
        char gender = ' ';
        int phoneNumber = 0;

        String trainingName = "";
        String venue = "";
        String time = "";

        int wordIndex = 1;
        while (matcher.find()) {
            switch (matcher.group()) {
            case "/m":
                memberName = words[wordIndex].trim();
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
            case "/n":
                trainingName = words[wordIndex].trim();
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
        Member member = new Member(memberName, studentNumber,gender,phoneNumber);
        TrainingSchedule training = new TrainingSchedule(trainingName, venue, time);
        return new Attendance(member, training);
    }
  
    /**
     * Creates Member class by input given by user
     *
     * @param query user raw data input.
     * @return Member according to user input.
     */
    public static Integer getMemberIndex(String query) {
        String regex = "(\\/[a-z])+";
        String[] words = query.trim().split(regex);
        int memberNumber = Integer.parseInt(words[1].trim());
        return memberNumber;
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

    /**
     * Function edits an existing member and shows the change to user
     *
     * @param members MemberList which contains list of members
     * @param query user input
     */
    public static void editMember(MemberList members, String query) {
        ArrayList<Member> oldMemberAndNewMember = editMemberDetails(members, query);
        Member oldMember = oldMemberAndNewMember.get(0);
        Member newMember = oldMemberAndNewMember.get(1);
        System.out.println("Edited member: " + oldMember);
        System.out.println("To become:  " + newMember);
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
        System.out.println("Added a Training entry:\n" + training);
    }

    /* *
     * Creates an Attendance Entry to put into AttendanceList
     *
     * @param attendanceList AttendanceList containing all Attendance entries
     * @param query User input command to parse
     */
    public static void makeAttendanceEntry(AttendanceList attendanceList, String query) {
        Attendance attendance = getAttendanceDetails(query);
        attendanceList.addAttendance(attendance);
        System.out.println("Added an Attendance entry: " + attendance);
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
     * Function deletes a member from the MemberList class.
     *
     * @param members MemberList which contains list of members
     * @param query user input
     */
    public static void deleteMember(MemberList members, String query) {
        try {
            int memberNumber = getMemberIndex(query);
            Member member = members.deleteMember(memberNumber);
            System.out.println("The following member have been deleted\n"+member.toString());
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("There is no such member number...");
        } catch (NumberFormatException e){
            System.out.println("Please input a proper number...");
        }
    }

    public static void wrongInputTypeMessage() {
        System.out.println("Wrong input. Please key in --help for some help on how to use the programme.");
    }

    public static void deleteTraining(TrainingList trainings, String query) {
        int trainingIndex = -1;

        String regex = "(\\/[a-z])+";

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(query);

        String[] words = query.trim().split(regex);

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

    public static void editTraining(TrainingList trainings, String query) {
        int index = -1;
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
                index = Integer.parseInt(words[wordIndex].trim());
                break;
            case "/n":
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

        if (index != -1) {
            int listIndex = index - 1;
            TrainingSchedule trainingToChange = trainings.getTrainingList().get(listIndex);
            if (!name.equals("")) {
                trainingToChange.setTrainingName(name);
            }
            if (!time.equals("")) {
                trainingToChange.setTrainingTime(time);
            }
            if (!venue.equals("")) {
                trainingToChange.setTrainingVenue(venue);
            }

            trainings.getTrainingList().set(listIndex,trainingToChange);
        }

    }

    public static void deleteAttendance(AttendanceList attendanceList, String query) {
        int attendanceIndex = -1;

        String regex = "(\\/[a-z])+";

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(query);

        String[] words = query.trim().split(regex);

        int wordIndex = 1;
        while (matcher.find()) {
            if (matcher.group().equals("/t")) {
                attendanceIndex = Integer.parseInt(words[wordIndex].trim());
            }
            wordIndex++;
        }

        if (attendanceIndex != -1) {
            Attendance toDelete = attendanceList.deleteAttendance(attendanceIndex);
            System.out.println("You have deleted: \n" + toDelete.toString());
        }

    }
}

