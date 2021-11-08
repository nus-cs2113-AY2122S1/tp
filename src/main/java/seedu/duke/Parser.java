//@@author ianwangeeen

package seedu.duke;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.attendance.Attendance;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.member.Member;
import seedu.duke.member.exception.InvalidMemberException;
import seedu.duke.training.TrainingSchedule;

public class Parser {

    static String regex = "(\\/[a-z])+";

    public static boolean hasListMemberKeyword(String arg) {
        return arg.trim().matches("^list /m");
    }

    public static boolean hasListTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^list /t");
    }

    public static boolean hasListAttendanceKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^list /att");
    }

    public static boolean hasAddMemberKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^add /m .*");
    }

    public static boolean hasAddTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^add /t .*");
    }

    public static boolean hasAddAttendanceKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^add /att .*");
    }

    public static boolean hasDeleteMemberKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^delete /m .*");
    }

    public static boolean hasDeleteTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^delete /t .*");
    }

    public static boolean hasDeleteAttendanceKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^delete /att /t .*");
    }

    public static boolean hasFindMemberKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^find /m .*");
    }

    public static boolean hasFindTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^find /t .*");
    }

    public static boolean hasEditTrainingKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^edit /t .*");
    }

    public static boolean hasEditMemberKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^edit /m .*");
    }

    public static boolean hasExitKeyword(String arg) {
        return arg.trim().toLowerCase().matches("^bye");
    }

    /**
     * Returns the required value for keyword which is the first word keyed in by user.
     *
     * @param entry user raw data input.
     * @return keyword value from Keyword enum class.
     */
    public static Keyword getKeywordStatus(String entry) {
        Keyword keyword;
        if (hasAddMemberKeyword(entry)) {
            keyword = Keyword.ADD_MEMBER_KEYWORD;
        } else if (hasAddTrainingKeyword(entry)) {
            keyword = Keyword.ADD_TRAINING_KEYWORD;
        } else if (hasAddAttendanceKeyword(entry)) {
            keyword = Keyword.ADD_ATTENDANCE_KEYWORD;
        } else if (hasListMemberKeyword(entry)) {
            keyword = Keyword.LIST_MEMBER_KEYWORD;
        } else if (hasListTrainingKeyword(entry)) {
            keyword = Keyword.LIST_TRAINING_KEYWORD;
        } else if (hasListAttendanceKeyword(entry)) {
            keyword = Keyword.LIST_ATTENDANCE_KEYWORD;
        } else if (hasDeleteMemberKeyword(entry)) {
            keyword = Keyword.DELETE_MEMBER_KEYWORD;
        } else if (hasDeleteTrainingKeyword(entry)) {
            keyword = Keyword.DELETE_TRAINING_KEYWORD;
        } else if (hasDeleteAttendanceKeyword(entry)) {
            keyword = Keyword.DELETE_ATTENDANCE_KEYWORD;
        } else if (hasFindMemberKeyword(entry)) {
            keyword = Keyword.FIND_MEMBER_KEYWORD;
        } else if (hasFindTrainingKeyword(entry)) {
            keyword = Keyword.FIND_TRAINING_KEYWORD;
        } else if (hasEditTrainingKeyword(entry)) {
            keyword = Keyword.EDIT_TRAINING_KEYWORD;
        } else if (hasEditMemberKeyword(entry)) {
            keyword = Keyword.EDIT_MEMBER_KEYWORD;
        } else if (entry.trim().equals("--help")) {
            keyword = Keyword.HELP_KEYWORD;
        } else if (hasExitKeyword(entry)) {
            keyword = Keyword.EXIT_KEYWORD;
        } else {
            keyword = Keyword.NO_KEYWORD;
        }
        assert !keyword.equals("");
        return keyword;
    }

    public static String getTrainingName(String entry) {
        String[] words = entry.trim().split("[\\s]+");
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

    /**
     * This function gets the filtered attendance list, requested by the user after the 'list /att /t' command is
     * called.
     *
     * @param attendanceList full list of attendance sheet.
     * @param entry          user raw data input.
     * @return smaller attendance list which has been filtered.
     */
    public static AttendanceList getFilteredAttendanceList(AttendanceList attendanceList, String entry) {
        // e.g. list /att /t Friday Training /d 0
        String[] trainingNameAndLabel = entry.trim().toLowerCase().split("/t");
        AttendanceList filteredAttendanceList = new AttendanceList();

        try {
            String trainingName = getTrainingName(trainingNameAndLabel[1].trim());
            for (Attendance attendance : attendanceList.getAttendanceList()) {
                if (attendance.getTrainingName().toLowerCase().equals(trainingName.trim())) {
                    filteredAttendanceList.addAttendance(attendance);
                }
            }
            if (filteredAttendanceList.getAttendanceListSize() == 0) {
                Ui.printMissingTraining();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printWrongInputMessage();
        }
        return filteredAttendanceList;
    }

    /**
     * Returns the description of the task only, without the date or the keyword. If any fields is empty "", an
     * exception will be thrown at AddTraining or EditTraining. Users should only key in one of each field, /n /a /v, in
     * the input. Additional inputs to the same field will set the field to "", causing an exception to be thrown at
     * AddTraining or EditTraining.
     *
     * @param entry user raw data input.
     * @return description of task.
     */
    public static TrainingSchedule getTrainingDescription(String entry) {
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(entry);
        boolean matchedName = false;
        boolean matchedTime = false;
        boolean matchedVenue = false;

        String[] words = entry.trim().split(regex);
        int parameterSize = words.length;

        String name = "";
        String venue = "";
        String time = "";

        int wordIndex = 1;
        while (matcher.find()) {
            boolean overParameterSize = wordIndex >= parameterSize;
            if (overParameterSize) {
                break;
            }
            switch (matcher.group()) {
            case "/n":
                if (!matchedName) {
                    name = words[wordIndex].trim().toUpperCase();
                    matchedName = true;
                } else {
                    name = "";
                }
                break;
            case "/a":
                if (!matchedTime) {
                    time = words[wordIndex].trim().toUpperCase();
                    matchedTime = true;
                } else {
                    time = "";
                }
                break;
            case "/v":
                if (!matchedVenue) {
                    venue = words[wordIndex].trim().toUpperCase();
                    matchedVenue = true;
                } else {
                    venue = "";
                }
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
     * @param entry   user raw data input.
     * @param keyword provide keyword for command
     * @return Member according to user input.
     */
    public static Member getMemberDetails(String entry, Keyword keyword) {
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(entry);

        String[] words = entry.trim().split(regex);
        String name = "";
        String studentNumber = "";
        String gender = "";
        String phoneNumber = "";

        Member memberdetails = null;
        int wordIndex = 1;
        try {
            while (matcher.find()) {
                switch (matcher.group()) {
                case "/n":
                    name = words[wordIndex].trim().toUpperCase(Locale.ROOT);
                    if (name.equals("")) {
                        throw new InvalidCommandException("Please do not give empty name.");
                    }
                    break;
                case "/s":
                    studentNumber = words[wordIndex].trim().toUpperCase(Locale.ROOT);
                    if (studentNumber.equals("")) {
                        throw new InvalidCommandException("Please do not give empty student number.");
                    }
                    break;
                case "/g":
                    gender = words[wordIndex].trim().toUpperCase(Locale.ROOT);
                    if (gender.equals("")) {
                        throw new InvalidCommandException("Please do not give empty gender.");
                    }
                    break;
                case "/p":
                    phoneNumber = words[wordIndex].trim();
                    if (phoneNumber.equals("")) {
                        throw new InvalidCommandException("Please do not give empty phone number.");
                    }
                    break;
                case "/m":
                    break;
                default:
                    throw new InvalidCommandException("Wrong parameter is given.");
                }
                wordIndex++;
            }
            boolean noParameterProvided = wordIndex <= 2;
            if (noParameterProvided) {
                throw new InvalidCommandException("No parameter is given.");
            }
            if (keyword.equals(Keyword.ADD_MEMBER_KEYWORD)) {
                memberdetails = new Member(name, studentNumber, gender, phoneNumber, true);
            } else if (keyword.equals(Keyword.EDIT_MEMBER_KEYWORD)) {
                memberdetails = new Member(name, studentNumber, gender, phoneNumber, false);
            }
        } catch (InvalidCommandException e) {
            Ui.printCommandErrorMessage(e.getMessage(), keyword);
        } catch (InvalidMemberException e) {
            Ui.printCreateMemberErrorMessage(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Ui.printCommandErrorMessage("Nothing is given after last parameter.", keyword);
        }
        return memberdetails;
    }

    /**
     * Creates Attendance object from input given by user.
     *
     * @param entry user raw data input.
     * @return Attendance according to user input.
     */
    public static Attendance getAttendanceDetails(String entry) {
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(entry);

        String[] words = entry.trim().split(regex);
        int parameterSize = words.length;

        String memberName = "";
        String trainingName = "";
        String presentOrAbsent = "";

        int wordIndex = 1;
        while (matcher.find()) {
            boolean overParameterSize = wordIndex >= parameterSize;
            if (overParameterSize) {
                break;
            }
            switch (matcher.group()) {
            case "/m":
                memberName = words[wordIndex].trim().toUpperCase(Locale.ROOT);
                break;
            case "/n":
                trainingName = words[wordIndex].trim().toUpperCase(Locale.ROOT);
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

    /**
     * This function returns the index of the attendance to be deleted.
     *
     * @param entry user's input.
     * @return index of deleted attendance.
     */
    public static int getAttendanceIndex(String entry) {
        try {
            String[] substring = entry.split("/i", 0);
            int trainingIndex = Integer.parseInt(substring[1].trim());
            assert trainingIndex >= 1 : "trainingIndex should be greater than 1.";
            return trainingIndex;
        } catch (IndexOutOfBoundsException e) {
            Ui.printWrongInputMessage();
        }
        return -1;
    }

    /**
     * This function returns the name of the training, for the attendance to be deleted.
     *
     * @param entry user's input.
     * @return training name of deleted attendance entry.
     */
    public static String getAttendanceTrainingName(String entry) {
        try {
            int trainingNameStartIndex = entry.indexOf("/t") + 2;
            int trainingNameEndIndex = entry.indexOf("/i");
            assert trainingNameEndIndex >= 1 : "trainingNameEndIndex should be greater than 1.";
            String trainingName = entry.substring(trainingNameStartIndex, trainingNameEndIndex).trim();
            return trainingName;
        } catch (IndexOutOfBoundsException e) {
            return "null";
        }
    }

    /**
     * Returns an integer Index from the given String entry.
     *
     * @param entry String user input.
     * @return int Index that is in entry.
     */
    public static Integer getIndex(String entry) {
        try {
            String[] words = entry.trim().split(regex);
            int indexNumber = Integer.parseInt(words[1].trim());
            assert indexNumber >= 1 : "indexNumber should be greater than 1.";
            return indexNumber;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    //@@author

    /**
     * Returns parameter as given by user.
     *
     * @param entry String user input
     * @return Object parameter that is given in entry which will either be int or string as given by user
     */
    public static Object getParameter(String entry) {
        String[] words = entry.trim().split(regex);
        try {
            int indexNumber = Integer.parseInt(words[1].trim());
            assert indexNumber >= 1 : "indexNumber should be greater than 1.";
            return indexNumber;
        } catch (NumberFormatException e) {
            String parameter = words[1].trim();
            return parameter;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Function finds tasks with descriptions matching the user's entry and adds them to a new ArrayList. If no matching
     * words are found, the user will be notified.
     *
     * @param entry user input
     */
    public static String getQuery(String entry) {
        try {
            String[] words = entry.trim().split(regex);
            return words[1].trim();
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * Function asks user if there is a need to list the full list. If 'y' is input, then the full list will show.
     * Otherwise, the full list will not be shown.
     *
     * @param attendanceList full list of attendance sheet.
     */
    public static void askToListAll(AttendanceList attendanceList) {
        Ui.printListAllMessage();
        Scanner userInput = new Scanner(System.in);
        String entry = userInput.nextLine();
        while (!(entry.equals("y") || entry.equals("n"))) {
            Ui.printQuestionToList();
            Ui.printArrow();
            if (userInput.hasNextLine()) {
                entry = userInput.nextLine();
            }
        }
        if (entry.equals("y")) {
            Ui.printList(attendanceList);
        }
    }

    /**
     * Function waits for user input, or takes input from ./list.txt.
     */
    public static void waitForQuery() {
        String entry = "";
        Scanner userInput = new Scanner(System.in);
        while (!entry.equals("bye")) {
            Ui.printArrow();
            if (userInput.hasNextLine()) {
                entry = userInput.nextLine();
            }
            Entry.addEntry(entry);
        }
    }
}