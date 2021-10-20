package seedu.duke;

import static seedu.duke.MemberStorage.writeMemberFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import seedu.duke.attendance.Attendance;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.member.exception.InvalidMemberException;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;

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

    public static boolean hasDeleteAttendanceKeyword(String arg) {
        return arg.trim().toLowerCase().contains("delete /att");
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

    public static boolean hasEditMemberKeyword(String arg) {
        return arg.trim().toLowerCase().contains("edit /m");
    }

    public static boolean hasExitKeyword(String arg) {
        return arg.trim().toLowerCase().contains("bye");
    }


    /**
     * Returns the required value for keyword which is the first word keyed in by user.
     *
     * @param query user raw data input.
     * @return keyword value from Keyword enum class.
     */
    public static Keyword getKeywordStatus(String query) {
        Keyword keyword;
        if (hasAddMemberKeyword(query)) {
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
        } else if (hasDeleteMemberKeyword(query)) {
            keyword = Keyword.DELETE_MEMBER_KEYWORD;
        } else if (hasDeleteTrainingKeyword(query)) {
            keyword = Keyword.DELETE_TRAINING_KEYWORD;
        } else if (hasDeleteAttendanceKeyword(query)) {
            keyword = Keyword.DELETE_ATTENDANCE_KEYWORD;
        } else if (hasFindMemberKeyword(query)) {
            keyword = Keyword.FIND_MEMBER_KEYWORD;
        } else if (hasFindTrainingKeyword(query)) {
            keyword = Keyword.FIND_TRAINING_KEYWORD;
        } else if (hasEditTrainingKeyword(query)) {
            keyword = Keyword.EDIT_TRAINING_KEYWORD;
        } else if (hasEditMemberKeyword(query)) {
            keyword = Keyword.EDIT_MEMBER_KEYWORD;
        } else if (hasExitKeyword(query)) {
            keyword = Keyword.EXIT_KEYWORD;
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
            switch (matcher.group()) {
            case "/n":
                name = words[wordIndex].trim();
                break;
            case "/a":
                time = words[wordIndex].trim();
                break;
            case "/v":
                venue = words[wordIndex].trim();
                break;
            default:
                break;
            }

            wordIndex++;
        }

        return new TrainingSchedule(name, venue, time);
    }

    /**
     * Creates Member class by input given by user.
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
            switch (matcher.group()) {
            case "/n":
                name = words[wordIndex].trim();
                assert name != "" : "Name should not be empty";
                break;
            case "/s":
                studentNumber = words[wordIndex].trim();
                assert studentNumber != "" : "Student Number should not be empty";
                break;
            case "/g":
                gender = words[wordIndex].trim().charAt(0);
                break;
            case "/p":
                phoneNumber = Integer.parseInt(words[wordIndex].trim());
                break;
            default:
                break;
            }
            wordIndex++;
        }

        return new Member(name, studentNumber, gender, phoneNumber);
    }

    /**
     * Edit member by input given by user.
     *
     * @param query user raw data input.
     * @return Edited member according to user input.
     */
    public static ArrayList<Member> editMemberDetails(MemberList members, String query) throws InvalidMemberException,
            NumberFormatException {
        try {
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
                switch (matcher.group()) {
                case "/m":
                    memberNumber = Integer.parseInt(words[wordIndex].trim());
                    oldMember = new Member(members.getMember(memberNumber));
                    editedMember = members.getMember(memberNumber);
                    break;
                case "/n":
                    name = words[wordIndex].trim();
                    assert name != "" : "Name should not be empty";
                    editedMember.setName(name);
                    break;
                case "/s":
                    studentNumber = words[wordIndex].trim();
                    assert studentNumber != "" : "Student Number should not be empty";
                    editedMember.setStudentNumber(studentNumber);
                    break;
                case "/g":
                    gender = words[wordIndex].trim().charAt(0);
                    assert gender != ' ' : "Gender should not be empty. Put M for male, F for female";
                    editedMember.setGender(gender);
                    break;
                case "/p":
                    phoneNumber = Integer.parseInt(words[wordIndex].trim());
                    assert phoneNumber != 0 : "Phone Number should not be empty";
                    editedMember.setPhoneNumber(phoneNumber);
                    break;
                default:
                    break;
                }
                wordIndex++;
            }
            ArrayList<Member> oldMemberAndEditedMember = new ArrayList<Member>();
            oldMemberAndEditedMember.add(oldMember);
            oldMemberAndEditedMember.add(editedMember);

            File dukeMemberFile = new File("dukeMembers.csv");
            writeMemberFile(dukeMemberFile, members);

            return oldMemberAndEditedMember;
        } catch (InvalidMemberException e) {
            throw new InvalidMemberException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    /**
     * Creates Attendance object from input given by user.
     *
     * @param query user raw data input.
     * @return Attendance according to user input.
     */
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
        String presentOrAbsent = "";

        int wordIndex = 1;
        while (matcher.find()) {
            switch (matcher.group()) {
            case "/m":
                memberName = words[wordIndex].trim();
                break;
            case "/n":
                trainingName = words[wordIndex].trim();
                break;
            case "/d":
                presentOrAbsent = words[wordIndex].trim();
                break;
            default:
                break;
            }
            wordIndex++;
        }
        Member member = new Member(memberName, studentNumber, gender, phoneNumber, presentOrAbsent);
        TrainingSchedule training = new TrainingSchedule(trainingName, venue, time);
        return new Attendance(member, training);
    }

    /**
     * Creates Member class by input given by user.
     *
     * @param query user raw data input.
     * @return Member according to user input.
     */
    public static Integer getMemberIndex(String query) throws NumberFormatException {
        try {
            String regex = "(\\/[a-z])+";
            String[] words = query.trim().split(regex);
            int memberNumber = Integer.parseInt(words[1].trim());
            assert memberNumber > 0 : "Member index cannot less than 0";
            return memberNumber;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }

    }

    /**
     * Function creates a new member to be input in MemberList Class.
     *
     * @param members MemberList which contains list of members
     * @param query   user input
     */
    public static void makeMemberEntry(MemberList members, String query) {
        Member member = getMemberDetails(query);
        members.addMember(member);
        File dukeMemberFile = new File("dukeMembers.csv");
        writeMemberFile(dukeMemberFile, members);
        Ui.printAddedMemberMessage(member);
    }

    /**
     * Function edits an existing member and shows the change to user.
     *
     * @param members MemberList which contains list of members
     * @param query   user input
     */
    public static void editMember(MemberList members, String query) {
        try {
            ArrayList<Member> oldMemberAndNewMember = editMemberDetails(members, query);
            Member oldMember = oldMemberAndNewMember.get(0);
            Member newMember = oldMemberAndNewMember.get(1);
            Ui.printEditMessage(oldMember, newMember);
        } catch (InvalidMemberException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid number for member index.");
        }
    }

    /**
     * Creates a TrainingSchedule to put into TrainingList.
     *
     * @param trainings TrainingList containing all TrainingSchedule
     * @param query     User input command to parse
     */
    public static void makeTrainingEntry(TrainingList trainings, String query) {
        TrainingSchedule training = getTrainingDescription(query);
        trainings.addTrainingSchedule(training);
        Ui.printAddedTrainingMessage(training);
    }

    /**
     * Creates an Attendance Entry to put into AttendanceList.
     *
     * @param attendanceList AttendanceList containing all Attendance entries
     * @param query          User input command to parse
     */
    public static void makeAttendanceEntry(AttendanceList attendanceList, String query) {
        Attendance attendance = getAttendanceDetails(query);
        assert attendance != null : "attendance should not be empty";
        attendanceList.addAttendance(attendance);
        Ui.printAddedAttendanceMessage(attendance);
    }

    /**
     * Function finds tasks with descriptions matching the user's query and adds them to a new ArrayList. If no matching
     * words are found, the user will be notified.
     *
     * @param trainings ArrayList of tasks
     * @param query     user input
     */
    public static void findInTraining(TrainingList trainings, String query) {
        //Leave for v2.0
    }

    /**
     * Function finds tasks with descriptions matching the user's query and adds them to a new ArrayList. If no matching
     * words are found, the user will be notified.
     *
     * @param members ArrayList of tasks
     * @param query   user input
     */
    public static void findInMembers(MemberList members, String query) {
        //Leave for v2.0
    }

    /**
     * Function deletes a member from the MemberList class.
     *
     * @param members MemberList which contains list of members
     * @param query   user input
     */
    public static void deleteMember(MemberList members, String query) {
        try {
            int memberNumber = getMemberIndex(query);
            Member member = members.deleteMember(memberNumber);
            File dukeMemberFile = new File("dukeMembers.csv");
            writeMemberFile(dukeMemberFile, members);
            Ui.printDeletedMemberMessage(member);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("There is no such member number...");
        } catch (NumberFormatException e) {
            System.out.println("Please input a proper number...");
        }
    }

    public static void wrongInputTypeMessage() {
        Ui.printWrongInputMessage();
    }

    /**
     * Removes an entry from a TrainingList based on input index.
     *
     * @param trainings TrainingList containing all recorded TrainingSchedules.
     * @param query     String input that contains the integer index of the entry to remove.
     */
    public static void deleteTraining(TrainingList trainings, String query) {
        try {

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
                Ui.printDeletedTrainingMessage(toDelete);
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("There is no such training number...");
        } catch (NumberFormatException e) {
            System.out.println("Please input a proper number...");
        }

    }

    /**
     * Edits an entry from a TrainingList based on input index. /t INDEX is compulsory, /n, /a and /v are optional
     * fields.
     *
     * @param trainings TrainingList containing all TrainingSchedules recorded.
     * @param query     String input of TrainingSchedule to be edited, identified by index after /t.
     */
    public static void editTraining(TrainingList trainings, String query) {
        try {
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
                switch (matcher.group()) {
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
                default:
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

                trainings.getTrainingList().set(listIndex, trainingToChange);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such training number...");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid index...");
        }

    }

    public static Integer getAttendanceIndex(String query) {
        int attNumber = Integer.parseInt(query.replaceFirst("delete /att", "").trim());
        assert attNumber > 0 : "smallest attNumber is 1";
        return attNumber;
    }

    /**
     * Removes an entry from a AttendanceList based on input index.
     *
     * @param attendanceList AttendanceList containing all Attendance entries.
     * @param query     String input that contains the integer index of the entry to remove.
     */
    public static void deleteAttendance(AttendanceList attendanceList, String query) {
        try {
            int attNumber = getAttendanceIndex(query);
            Attendance entry = attendanceList.deleteAttendance(attNumber);
            assert entry != null : "entry should not be empty";
            Ui.printDeletedAttendanceMessage(entry);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("There is no such attendance number...");
        } catch (NumberFormatException e) {
            System.out.println("Please input a proper number...");
        }
    }

    /**
     * Function waits for user input, or takes input from ./list.txt.
     */
    public static void waitForQuery() {
        String query = "";
        int flag = 0;
        Scanner userInput = new Scanner(System.in);
        while (!query.equals("bye")) {
            System.out.print("=>");
            if (userInput.hasNextLine()) {
                query = userInput.nextLine();
            }
            Entry.addEntry(query, flag);
            flag = 1;
        }
    }


}

