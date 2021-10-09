package seedu.duke.parser;

import seedu.duke.command.AddLessonCommand;
import seedu.duke.command.AddTaskCommand;
import seedu.duke.command.DeleteAllCommand;
import seedu.duke.command.DeleteLessonCommand;
import seedu.duke.command.DeleteTaskCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.FindAllCommand;
import seedu.duke.command.FindLessonCommand;
import seedu.duke.command.FindTaskCommand;
import seedu.duke.command.ListAllCommand;
import seedu.duke.command.ListLessonCommand;
import seedu.duke.command.ListTaskCommand;
import seedu.duke.command.Command;
import seedu.duke.command.CommandType;
import seedu.duke.command.ExitCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.Message;

import static seedu.duke.parser.DayOfTheWeek.is;

public class Parser {
    public static CommandType getCommandType(String userResponse) {
        String[] params = userResponse.split(" ", 2);
        return CommandType.of(params[0]);
    }

    /**
     * Identifies key information from raw user input and returns a Command object with the relevant information
     * prepared for execution.
     *
     * @param userResponse the user response
     * @return the corresponding command
     * @throws DukeException if user response is invalid
     */
    public static Command parse(String userResponse) throws DukeException {
        CommandType commandType = getCommandType(userResponse);

        switch (commandType) {
        case ADD:
            return parseAddCommand(userResponse);
        case DELETE:
            return parseDeleteCommand(userResponse);
        case DONE:
            return parseDoneCommand(userResponse);
        case EXIT:
            return parseExitCommand(userResponse);
        case FIND:
            return parseFindCommand(userResponse);
        case LIST:
            return parseListCommand(userResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Parses the user response into an executable add command.
     *
     * @param userResponse the user response
     * @return an executable add type command
     * @throws DukeException when user response is in an incorrect format
     */
    private static Command parseAddCommand(String userResponse) throws DukeException {
        String param = userResponse.replaceFirst("add", "").strip();
        CommandType commandType = getCommandType(param);

        switch (commandType) {
        case TASK:
            return parseAddTaskCommand(param.replaceFirst("task", "").strip());
        case LESSON:
            return parseAddLessonCommand(param.replaceFirst("lesson", "").strip());
        case INVALID:
            // Fallthrough
        default:
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Parses the user response with commandType removed into an executable add task command.
     *
     * @param userResponse the user response
     * @return an executable add task type command
     * @throws DukeException when the user response is in an incorrect format
     */
    private static Command parseAddTaskCommand(String userResponse) throws DukeException {
        String[] params = userResponse.split(" -d | -i ");
        if (params.length < 2 || params.length > 3) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }
        if ((params.length == 3) && (userResponse.indexOf(" -d ") > userResponse.indexOf(" -i "))) {
            throw new DukeException(Message.ERROR_WRONG_FLAG_SEQUENCE);
        }
        String title = params[0].strip();
        String dayOfTheWeek = params[1].strip();
        if (!is(dayOfTheWeek)) {
            throw new DukeException(dayOfTheWeek + Message.ERROR_INVALID_DAY_OF_WEEK);
        }

        switch (params.length) {
        case 2:
            return new AddTaskCommand(title, dayOfTheWeek, "");
        case 3:
            String information = params[2].strip();
            return new AddTaskCommand(title, dayOfTheWeek, information);
        default:
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Parses the user command with commandType removed into an executable add lesson type command.
     *
     * @param userResponse the user response
     * @return an executable add lesson type command
     * @throws DukeException when the user response is in an incorrect format
     */
    private static Command parseAddLessonCommand(String userResponse) throws DukeException {
        String[] params = userResponse.split(" -d | -s | -e ");
        if (params.length != 4) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        if (!hasCorrectLessonFlagSequence(userResponse)) {
            throw new DukeException(Message.ERROR_WRONG_FLAG_SEQUENCE);
        }
        String title = params[0].strip();
        String dayOfTheWeek = params[1].strip();
        if (!is(dayOfTheWeek)) {
            throw new DukeException(dayOfTheWeek + Message.ERROR_INVALID_DAY_OF_WEEK);
        }
        String startTIme = params[2].strip();       // TODO: Validate correctness with time library
        String endTime = params[3].strip();         // TODO: Validate correctness with time library
        return new AddLessonCommand(title, dayOfTheWeek, startTIme, endTime);
    }

    /**
     * Parses the user response into an executable delete command.
     *
     * @param userResponse the user response
     * @return an executable delete type command
     * @throws DukeException when user response is in an incorrect format
     */
    private static Command parseDeleteCommand(String userResponse) throws DukeException {
        String param = userResponse.replaceFirst("delete", "").strip();
        CommandType commandType = getCommandType(param);

        switch (commandType) {
        case TASK:
            return parseDeleteTaskCommand(param.replaceFirst("task", "").strip());
        case LESSON:
            return parseDeleteLessonCommand(param.replaceFirst("lesson", "").strip());
        case ALL:
            return parseDeleteAllCommand();
        case INVALID:
            // Fallthrough
        default:
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Parses the user response with commandType removed into an executable delete task command.
     *
     * @param userResponse the user response
     * @return an executable delete task type command
     * @throws DukeException when the user response is in an incorrect format
     */
    private static Command parseDeleteTaskCommand(String userResponse) throws DukeException {
        if (userResponse.equals("all")) {
            return new DeleteTaskCommand();
        }

        try {
            int taskIndex = Integer.parseInt(userResponse) - 1; // change user index to match internal 0 indexing
            return new DeleteTaskCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeException(Message.ERROR_NOT_NUMBER);
        }
    }

    /**
     * Parses the user response with commandType removed into an executable delete lesson command.
     *
     * @param userResponse the user response
     * @return an executable delete lesson type command
     * @throws DukeException when the user response is in an incorrect format
     */
    private static Command parseDeleteLessonCommand(String userResponse) throws DukeException {
        if (userResponse.equals("all")) {
            return new DeleteLessonCommand();
        }

        try {
            int lessonIndex = Integer.parseInt(userResponse) - 1; // change user index to match internal 0 indexing
            return new DeleteLessonCommand(lessonIndex);
        } catch (NumberFormatException e) {
            throw new DukeException(Message.ERROR_NOT_NUMBER);
        }
    }

    private static Command parseDeleteAllCommand() {
        return new DeleteAllCommand();
    }

    /**
     * Parses the user response into an executable done command.
     *
     * @param userResponse the user response
     * @return an executable done type command
     * @throws DukeException when user response is in an incorrect format
     */
    private static Command parseDoneCommand(String userResponse) throws DukeException {
        // TODO: Implement batch marking
        String[] params = userResponse.split(" ");
        try {
            //get 1-indexed task number
            int taskNumber = Integer.parseInt(params[1]);
            //convert 1-index (1, 2, 3, ...) to 0-index (0, 1, 2, ...)
            int taskIndex = taskNumber - 1;
            return new DoneCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeException(Message.ERROR_NOT_NUMBER);
        }
    }

    /**
     * Parses the user response into an executable exit command.
     *
     * @param userResponse the user response
     * @return an executable exit type command
     * @throws DukeException when user response is in an incorrect format
     */
    private static Command parseExitCommand(String userResponse) throws DukeException {
        boolean isValidResponse = userResponse.equals("exit");
        if (!isValidResponse) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        return new ExitCommand();
    }

    /**
     * Parses the user response into an executable find command.
     *
     * @param userResponse the user response
     * @return an executable find type command
     * @throws DukeException when user response is in an incorrect format
     */
    private static Command parseFindCommand(String userResponse) throws DukeException {
        String param = userResponse.replaceFirst("find", "").strip();
        CommandType commandType = getCommandType(param);

        switch (commandType) {
        case TASK:
            return parseFindTaskCommand(param.replaceFirst("task", "").strip());
        case LESSON:
            return parseFindLessonCommand(param.replaceFirst("lesson", "").strip());
        case ALL:
            return parseFindAllCommand(param.replaceFirst("all", "").strip());
        case INVALID:
            // Fallthrough
        default:
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Parses the given keyword into an executable find task command.
     *
     * @param keyword the keyword parsed from the user response
     * @return an executable find task type command
     * @throws DukeException if keyword is not provided
     */
    private static Command parseFindTaskCommand(String keyword) throws DukeException {
        if (keyword.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        return new FindTaskCommand(keyword);
    }

    /**
     * Parses the given keyword into an executable find lesson command.
     *
     * @param keyword the keyword parsed from the user response
     * @return an executable find lesson type command
     * @throws DukeException if keyword is not provided
     */
    private static Command parseFindLessonCommand(String keyword) throws DukeException {
        if (keyword.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        return new FindLessonCommand(keyword);
    }

    /**
     * Parses the given keyword into an executable find all command.
     *
     * @param keyword the keyword parsed from the user response
     * @return an executable find all type command
     * @throws DukeException if keyword is not provided
     */
    private static Command parseFindAllCommand(String keyword) throws DukeException {
        if (keyword.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        return new FindAllCommand(keyword);
    }

    /**
     * Parses the user response into an executable list command.
     *
     * @param userResponse the user response
     * @return an executable list type command
     * @throws DukeException when user response is in an incorrect format
     */
    private static Command parseListCommand(String userResponse) throws DukeException {
        String param = userResponse.replaceFirst("list", "").strip();
        CommandType commandType = getCommandType(param);

        switch (commandType) {
        case TASK:
            return parseListTaskCommand(param.replaceFirst("task", "").strip());
        case LESSON:
            return parseListLessonCommand(param.replaceFirst("lesson", "").strip());
        case ALL:
            return parseListAllCommand(param.replaceFirst("all", "").strip());
        case INVALID:
            // Fallthrough
        default:
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Parses the given period (day of the week) into an executable list task command.
     *
     * @param period the keyword parsed from the user response
     * @return an executable list task type command
     * @throws DukeException if period is not provided
     */
    private static Command parseListTaskCommand(String period) throws DukeException {
        // TODO: Validate today, tomorrow
        if (period.isBlank()) {
            return new ListTaskCommand();
        } else if (is(period)) {
            return new ListTaskCommand(period);
        }

        throw new DukeException(Message.ERROR_INVALID_COMMAND);
    }

    /**
     * Parses the given period (day of the week) into an executable list lesson command.
     *
     * @param period the keyword parsed from the user response
     * @return an executable list lesson type command
     * @throws DukeException if period is not provided
     */
    private static Command parseListLessonCommand(String period) throws DukeException {
        // TODO: Validate today, tomorrow
        if (period.isBlank()) {
            return new ListLessonCommand();
        } else if (is(period)) {
            return new ListLessonCommand(period);
        }

        throw new DukeException(Message.ERROR_INVALID_COMMAND);
    }

    /**
     * Parses the given period (day of the week) into an executable list all command.
     *
     * @param period the keyword parsed from the user response
     * @return an executable list all type command
     * @throws DukeException if period is not provided
     */
    private static Command parseListAllCommand(String period) throws DukeException {
        // TODO: Validate today, tomorrow
        if (period.isBlank()) {
            return new ListAllCommand();
        } else if (is(period)) {
            return new ListAllCommand(period);
        }

        throw new DukeException(Message.ERROR_INVALID_COMMAND);
    }

    /**
     * Checks if the sequence of flags in an add lesson user response is correct.
     *
     * @param userResponse the user response
     * @return true if the sequence is correct, false otherwise
     */
    private static boolean hasCorrectLessonFlagSequence(String userResponse) {
        int posOfDFlag = userResponse.indexOf(" -d ");
        int posOfSFlag = userResponse.indexOf(" -s ");
        int posOfEFlag = userResponse.indexOf(" -e ");
        return (posOfDFlag < posOfSFlag) && (posOfDFlag < posOfEFlag) && (posOfSFlag < posOfEFlag);
    }
}
