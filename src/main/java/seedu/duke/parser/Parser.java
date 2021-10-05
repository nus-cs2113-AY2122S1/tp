package seedu.duke.parser;

import seedu.duke.command.*;
import seedu.duke.exception.DukeException;

public class Parser {
    public static final String COMMAND_ADD = "add";
    public static final String TASK_KEYWORD = "task";
    public static final String LESSON_KEYWORD = "lesson";
    public static final String ALL_KEYWORD = "all";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String FLAG_DAY = "-d";
    public static final String FLAG_INFORMATION = "-i";
    public static final String FLAG_START_TIME = "-s";
    public static final String FLAG_END_TIME = "-e";
    public static int SEPARATING_SPACE = 3;

    /**
     * Identifies key information from raw user input and returns a Command object with the relevant information
     * prepared for execution.
     *
     * @param userInput the raw input that from the command prompt
     * @return the Command object to be executed
     * @throws DukeException the exception that occurs when there are issues while parsing the input
     */
    public Command parse(String userInput) throws DukeException {
        int positionOfSpace = userInput.indexOf(" ");
        String commandWord = positionOfSpace > 0 ? userInput.substring(0, positionOfSpace).strip() : userInput;
        String commandInfo = userInput.substring(positionOfSpace + 1);

        switch (commandWord) {
        case COMMAND_ADD:
            return parseAddCommand(commandInfo);
        case COMMAND_LIST:
            return parseListCommand(commandInfo);
        case COMMAND_DONE:
            return parseDoneCommand(commandInfo);
        case COMMAND_DELETE:
            return parseDeleteCommand(commandInfo);
        case COMMAND_FIND:
            return parseFindCommand(commandInfo);
        default:
            throw new DukeException("");
        }
    }

    /**
     * Returns the relevant Command object with the title of the task or lesson to add, the date of the task or
     * lesson. Tasks can have an optional information parameter, and lessons have a start and end time.
     *
     * @param addInfo the remainder of the command following the word "add"
     * @return the Command to be executed to add the task or lesson
     * @throws DukeException the exception that occurs when the user enters a type to add other than "task" or "lesson"
     */
    private Command parseAddCommand(String addInfo) throws DukeException {
        int positionOfSpace = addInfo.indexOf(" ");
        String taskOrLesson = addInfo.substring(0, positionOfSpace);
        int positionOfDay = addInfo.indexOf(FLAG_DAY);
        String title = addInfo.substring(positionOfSpace + 1, positionOfDay - 1).strip();

        switch (taskOrLesson) {
        case TASK_KEYWORD:
            String dayOfTask;
            int positionOfInformation = addInfo.indexOf(FLAG_INFORMATION);
            if (positionOfInformation != -1) {
                dayOfTask = addInfo.substring(positionOfDay + SEPARATING_SPACE, positionOfInformation - 1).strip();
                String information = addInfo.substring(positionOfInformation + SEPARATING_SPACE).strip();
                return new AddTaskCommand(title, dayOfTask, information);
            } else {
                // no information entered
                dayOfTask = addInfo.substring(positionOfDay + SEPARATING_SPACE).strip();
                return new AddTaskCommand(title, dayOfTask, "");
            }
        case LESSON_KEYWORD:
            int positionOfStartTime = addInfo.indexOf(FLAG_START_TIME);
            int positionOfEndTime = addInfo.indexOf(FLAG_END_TIME);
            String dayOfLesson = addInfo.substring(positionOfDay + SEPARATING_SPACE, positionOfStartTime - 1).strip();
            String lessonStartTime =
                    addInfo.substring(positionOfStartTime + SEPARATING_SPACE, positionOfEndTime - 1).strip();
            String lessonEndTime = addInfo.substring(positionOfEndTime + SEPARATING_SPACE).strip();
            return new AddLessonCommand(title, dayOfLesson, lessonStartTime, lessonEndTime);
        default:
            throw new DukeException("");
        }
    }

    /**
     * Returns an executable Command object containing information about the type of tasks or lessons to list, and
     * the time period of items to list.
     *
     * @param listInfo the remainder of the command following the word "list"
     * @return the Command to be executed to list items
     */
    private Command parseListCommand(String listInfo) {
        int positionOfSpace = listInfo.indexOf(" ");
        String listType = listInfo.substring(0, positionOfSpace); // either all, task or lesson
        String period = listInfo.substring(positionOfSpace + 1); // today, week, tomorrow, mon, tue, wed, thu, fri,
        // sat, or sun

        return new ListCommand(listType, period);
    }

    /**
     * Returns an executable Command object containing the index specified by the user of the task to mark as done.
     *
     * @param doneIndex the index of the task to mark done
     * @return the Command to be executed to mark a task as done
     */
    private Command parseDoneCommand(String doneIndex) {
        int taskIndex = Integer.parseInt(doneIndex);
        return new DoneCommand(taskIndex);
    }

    /**
     * Returns an executable Command object containing information about the type of tasks or lessons to delete, and if
     * the user chooses to delete all tasks or lessons, the type of event to delete ("task" or "lesson").
     *
     * @param deleteInfo the remainder of the command following the word "delete"
     * @return the Command to be executed to delete a task / lesson / all tasks or lessons
     * @throws DukeException the exception thrown when the user either enters invalid input to delete
     */
    private Command parseDeleteCommand(String deleteInfo) throws DukeException {
        int positionOfSpace = deleteInfo.indexOf(" ");
        String deleteType = deleteInfo.substring(0, positionOfSpace);

        switch (deleteType) {
        case TASK_KEYWORD:
        case LESSON_KEYWORD:
            int deleteIndex = Integer.parseInt(deleteInfo.substring(positionOfSpace + 1).strip());
            return new DeleteCommand(deleteType, deleteIndex);
        case ALL_KEYWORD:
            String deleteAllType = deleteInfo.substring(positionOfSpace + 1).strip();
            if (deleteAllType.equals(TASK_KEYWORD) || deleteAllType.equals(LESSON_KEYWORD)) {
                return new DeleteCommand(deleteType, 0, deleteAllType);
            } else {
                throw new DukeException("");
            }
        default:
            throw new DukeException("");
        }
    }

    /**
     * Returns an executable Command object containing information about the keyword to locate in existing tasks and/or
     * lessons.
     *
     * @param findInfo the remainder of the command following the word "find"
     * @return the Command object to be executed to find relevant tasks/lessons
     */
    private Command parseFindCommand(String findInfo) {
        int positionOfSpace = findInfo.indexOf(" ");
        String typeToFind = findInfo.substring(0, positionOfSpace);
        String findKeyword = findInfo.substring(positionOfSpace + 1).strip();
        return new FindCommand(typeToFind, findKeyword);
    }
}
