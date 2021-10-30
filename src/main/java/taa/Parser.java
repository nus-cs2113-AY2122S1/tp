package taa;

//@@author leyondlee
import taa.command.ArchiveCommand;
import taa.command.ResetCommand;
import taa.command.assessment.AddAssessmentCommand;
import taa.command.attendance.ListLessonAttendanceCommand;
import taa.command.classcategory.AddClassCommand;
import taa.command.student.AddStudentCommand;
import taa.command.mark.AverageMarksCommand;
import taa.command.Command;
import taa.command.assessment.DeleteAssessmentCommand;
import taa.command.attendance.DeleteAttendanceCommand;
import taa.command.mark.DeleteMarkCommand;
import taa.command.classcategory.DeleteClassCommand;
import taa.command.student.DeleteStudentCommand;
import taa.command.assessment.EditAssessmentCommand;
import taa.command.mark.EditMarkCommand;
import taa.command.classcategory.EditClassCommand;
import taa.command.student.EditStudentCommand;
import taa.command.ExitCommand;
import taa.command.student.FindStudentCommand;
import taa.command.HelpCommand;
import taa.command.assessment.ListAssessmentsCommand;
import taa.command.attendance.ListAttendanceCommand;
import taa.command.mark.ListMarksCommand;
import taa.command.classcategory.ListClassesCommand;
import taa.command.student.ListStudentsCommand;
import taa.command.mark.MedianMarkCommand;
import taa.command.attendance.SetAttendanceCommand;
import taa.command.mark.SetMarkCommand;
import taa.command.student.SortByScoresCommand;
import taa.command.student.SetCommentCommand;
import taa.command.student.DeleteCommentCommand;
import taa.command.student.ListCommentCommand;
import taa.exception.DuplicatedArgumentException;
import taa.exception.TaaException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String ARGUMENT_VALID_REGEX = "^[0-9a-zA-Z\\-_()\\s]+$";

    private static final String MESSAGE_UNKNOWN_COMMAND = "Unknown Command";
    private static final String MESSAGE_INVALID_VALUE = "Invalid characters in value(s). Only alphanumeric characters, "
        + "spaces, and some special characters are allowed.\nAllowed special characters: - _ ( )";

    public static Command parseUserInput(String userInput) throws TaaException {
        String[] userInputSplit = splitFirstSpace(userInput);
        String commandString = userInputSplit[0];
        String argument = userInputSplit[1];

        Command command;
        switch (commandString) {
        case Command.COMMAND_HELP:
            command = new HelpCommand(argument);
            break;

        case Command.COMMAND_EXIT:
            command = new ExitCommand(argument);
            break;

        case Command.COMMAND_LIST_CLASSES:
            command = new ListClassesCommand(argument);
            break;

        case Command.COMMAND_ADD_CLASS:
            command = new AddClassCommand(argument);
            break;

        case Command.COMMAND_EDIT_CLASS:
            command = new EditClassCommand(argument);
            break;

        case Command.COMMAND_DELETE_CLASS:
            command = new DeleteClassCommand(argument);
            break;

        case Command.COMMAND_ADD_STUDENT:
            command = new AddStudentCommand(argument);
            break;

        case Command.COMMAND_EDIT_STUDENT:
            command = new EditStudentCommand(argument);
            break;

        case Command.COMMAND_DELETE_STUDENT:
            command = new DeleteStudentCommand(argument);
            break;

        case Command.COMMAND_LIST_STUDENTS:
            command = new ListStudentsCommand(argument);
            break;

        case Command.COMMAND_FIND_STUDENT:
            command = new FindStudentCommand(argument);
            break;

        case Command.COMMAND_ADD_ASSESSMENT:
            command = new AddAssessmentCommand(argument);
            break;

        case Command.COMMAND_LIST_ASSESSMENTS:
            command = new ListAssessmentsCommand(argument);
            break;

        case Command.COMMAND_EDIT_ASSESSMENT:
            command = new EditAssessmentCommand(argument);
            break;

        case Command.COMMAND_DELETE_ASSESSMENT:
            command = new DeleteAssessmentCommand(argument);
            break;

        case Command.COMMAND_SET_ATTENDANCE:
            command = new SetAttendanceCommand(argument);
            break;

        case Command.COMMAND_LIST_ATTENDANCE:
            command = new ListAttendanceCommand(argument);
            break;

        case Command.COMMAND_LIST_LESSON_ATTENDANCE:
            command = new ListLessonAttendanceCommand(argument);
            break;

        case Command.COMMAND_DELETE_ATTENDANCE:
            command = new DeleteAttendanceCommand(argument);
            break;

        case Command.COMMAND_SET_MARKS:
            command = new SetMarkCommand(argument);
            break;

        case Command.COMMAND_EDIT_MARK:
            command = new EditMarkCommand(argument);
            break;

        case Command.COMMAND_DELETE_MARK:
            command = new DeleteMarkCommand(argument);
            break;

        case Command.COMMAND_AVERAGE_MARKS:
            command = new AverageMarksCommand(argument);
            break;

        case Command.COMMAND_MEDIAN_MARK:
            command = new MedianMarkCommand(argument);
            break;

        case Command.COMMAND_LIST_MARKS:
            command = new ListMarksCommand(argument);
            break;

        case Command.COMMAND_SORT_BY_SCORES:
            command = new SortByScoresCommand(argument);
            break;

        case Command.COMMAND_SET_COMMENT:
            command = new SetCommentCommand(argument);
            break;

        case Command.COMMAND_DELETE_COMMENT:
            command = new DeleteCommentCommand(argument);
            break;

        case Command.COMMAND_LIST_COMMENT:
            command = new ListCommentCommand(argument);
            break;

        case Command.COMMAND_ARCHIVE:
            command = new ArchiveCommand(argument);
            break;

        case Command.COMMAND_RESET:
            command = new ResetCommand(argument);
            break;

        default:
            throw new TaaException(MESSAGE_UNKNOWN_COMMAND);
        }

        return command;
    }

    public static String[] splitFirstSpace(String string) {
        String[] result = string.trim().split("\\s+", 2);
        if (result.length != 2) {
            return new String[]{result[0], ""};
        }

        return result;
    }

    /**
     * Gets argument values specified by argumentKeys. Keys with empty values are not included in the returned HashMap.
     * e.g.
     * string: "add_class c/CS2113T-F12 n/Class F12", argumentKeys: {"c","n"}
     * Result: HashMap(
     *             "c":"CS2113T-F12",
     *             "n":"Class F12"
     *         )
     *
     * @param string       The string to parse.
     * @param argumentKeys The argument keys to find.
     * @return HashMap - argumentKey:argumentValue pair.
     * @throws TaaException if there are any duplicated keys found within string or value is invalid.
     */
    public static HashMap<String, String> getArgumentsFromString(String string, String[] argumentKeys)
        throws TaaException {
        if (argumentKeys == null || argumentKeys.length == 0) {
            return new HashMap<>();
        }

        String argumentString = " " + string;
        HashMap<String, Integer> argumentIndexMap = new HashMap<>();
        ArrayList<Integer> argumentIndexes = new ArrayList<>();
        ArrayList<String> duplicatedKeys = new ArrayList<>();
        for (String key : argumentKeys) {
            String searchString = String.format(" %s/", key);
            int index = argumentString.indexOf(searchString);
            if (index != -1) {
                argumentIndexMap.put(key, index);
                argumentIndexes.add(index);

                String trailingString = argumentString.substring(index + 3);
                if (trailingString.contains(searchString)) {
                    duplicatedKeys.add(key);
                }
            }
        }

        if (!duplicatedKeys.isEmpty()) {
            throw new DuplicatedArgumentException(duplicatedKeys);
        }

        Collections.sort(argumentIndexes);

        HashMap<String, String> result = new HashMap<>();
        for (String key : argumentIndexMap.keySet()) {
            int argumentIndex = argumentIndexMap.get(key);
            int startIndex = argumentIndex + key.length() + 1;

            String value;
            int listIndex = argumentIndexes.indexOf(argumentIndex);
            boolean isLastIndex = listIndex == (argumentIndexes.size() - 1);
            if (isLastIndex) {
                value = string.substring(startIndex);
            } else {
                int nextArgumentIndex = argumentIndexes.get(listIndex + 1);
                value = string.substring(startIndex, nextArgumentIndex - 1);
            }
            value = value.trim();

            if (!isValueValid(value)) {
                throw new TaaException(MESSAGE_INVALID_VALUE);
            }

            result.put(key, value);
        }

        return result;
    }

    public static boolean isValueValid(String value) {
        if (value.isEmpty()) {
            return true;
        }

        Pattern pattern = Pattern.compile(ARGUMENT_VALID_REGEX);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
