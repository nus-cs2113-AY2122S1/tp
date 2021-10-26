package seedu.duke;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import seedu.duke.attendance.Attendance;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
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

    public static boolean hasFilteredListAttendanceKeyword(String arg) {
        return arg.trim().toLowerCase().contains(" /att /t");
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
        } else if (query.trim().equals("--help")) {
            keyword = Keyword.HELP_KEYWORD;
        } else if (hasExitKeyword(query)) {
            keyword = Keyword.EXIT_KEYWORD;
        } else {
            keyword = Keyword.NO_KEYWORD;
        }
        return keyword;
    }

    public static String getTrainingName(String query) {
        String[] words = query.trim().split("[\\s]+");
        StringBuilder sentenceAfterDeletion = new StringBuilder();
        for (String word : words) {
            if (word.contains("/")) {
                break;
            } else {
                sentenceAfterDeletion.append(word).append(" ");
            }
        }
        return sentenceAfterDeletion.toString();
    }

    public static AttendanceList getFilteredAttendanceList(AttendanceList attendanceList, String query) {
        // e.g. list /att /t Friday Training /d 0
        String[] trainingNameAndLabel = query.trim().split("/t");

        String presentOrAbsent = "";

        AttendanceList filteredAttendanceList = new AttendanceList();
        try {
            String trainingName = getTrainingName(trainingNameAndLabel[1].trim());
            for (Attendance attendance : attendanceList.getAttendanceList()) {
                if (attendance.getTrainingName().equals(trainingName.trim())) {
                    filteredAttendanceList.addAttendance(attendance);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printWrongInputMessage();
        }
        return filteredAttendanceList;
    }

    public static Integer getIndexFromFullList(String name, String trainingName, AttendanceList attendanceList) {
        int index = 0;
        for (Attendance attendance : attendanceList.getAttendanceList()) {
            if (attendance.getMemberName().equals(name) & attendance.getTrainingName().equals(trainingName)) {
                return index;
            }
            index++;
        }
        return -1;
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


        String trainingName = "";
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
        return new Attendance(memberName, trainingName, presentOrAbsent);
    }

    public static int getAttendanceIndex(String entry) {
        String[] substring = entry.split("/i",0);
        int trainingIndex = Integer.parseInt(substring[1].trim());
        return trainingIndex;
    }

   public static String getAttendanceTrainingName(String entry){
        int trainingNameStartIndex = entry.indexOf("/t")+2;
        int trainingNameEndIndex = entry.indexOf("/i");
        String trainingName = entry.substring(trainingNameStartIndex,trainingNameEndIndex).trim();
        return trainingName;
   }

    /**
     * Returns an integer Index from the given String query.
     *
     * @param query String user input.
     * @return int Index that is in query.
     */
    public static Integer getIndex(String query) {
        try {
            String regex = "(\\/[a-z])+";
            String[] words = query.trim().split(regex);
            int indexNumber = Integer.parseInt(words[1].trim());
            return indexNumber;
        } catch (NumberFormatException e) {
            System.out.println("Index must be a number");
            return -1;
        }
    }

    /**
     * Function finds tasks with descriptions matching the user's query and adds them to a new ArrayList. If no matching
     * words are found, the user will be notified.
     *
     * @param trainings ArrayList of tasks
     * @param query     user input
     */
    public static void findInTraining(TrainingList trainings, String query) {
        //Leave for v2.0, implement as class in commands package
    }

    /**
     * Function finds tasks with descriptions matching the user's query and adds them to a new ArrayList. If no matching
     * words are found, the user will be notified.
     *
     * @param members ArrayList of tasks
     * @param query   user input
     */
    public static void findInMembers(MemberList members, String query) {
        //Leave for v2.0, implement as class in commands package
    }

    /**
     * Function finds tasks with descriptions matching the user's query and adds them to a new ArrayList. If no matching
     * words are found, the user will be notified.
     *
     * @param attendanceList ArrayList of tasks
     * @param query          user input
     */
    public static void findInAttendanceEntries(AttendanceList attendanceList, String query) {
        //Leave for v2.0, implement as class in commands package
    }

    public static void wrongInputTypeMessage() {
        Ui.printWrongInputMessage();
    }

    public static void askToListAll(AttendanceList attendanceList) {
        Ui.printListAllMessage();
        Scanner userInput = new Scanner(System.in);
        String query = userInput.nextLine();
        if (query.equals("y")) {
            Ui.printList(attendanceList);
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
            System.out.print("=> ");
            if (userInput.hasNextLine()) {
                query = userInput.nextLine();
            }
            Entry.addEntry(query, flag);
            flag = 1;
        }
    }


}

