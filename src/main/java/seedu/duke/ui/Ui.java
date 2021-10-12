package seedu.duke.ui;

import java.util.Scanner;

import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonList;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class Ui {
    public static final String LINE =
            "    _______________________________________________________________________________"
                    + System.lineSeparator();

    public static final String PADDING = "     ";

    /** Temporary logo. */
    public static final String LOGO = PADDING
            + "  _   _       _   _   ____           ____     _   _   ____     ____   __   __ "
            + System.lineSeparator() + PADDING
            + " | \\ |\"|   U |\"|u| | / __\"| u     U | __\")uU |\"|u| | |  _\"\\   |  _\"\\  \\ \\ / / "
            + System.lineSeparator() + PADDING
            + "<|  \\| |>   \\| |\\| |<\\___ \\/       \\|  _ \\/ \\| |\\| |/| | | | /| | | |  \\ V /  "
            + System.lineSeparator() + PADDING
            + "U| |\\  |u    | |_| | u___) |        | |_) |  | |_| |U| |_| |\\U| |_| |\\U_|\"|_u "
            + System.lineSeparator() + PADDING
            + " |_| \\_|    <<\\___/  |____/>>       |____/  <<\\___/  |____/ u |____/ u  |_|   "
            + System.lineSeparator() + PADDING
            + " ||   \\\\,-.(__) )(    )(  (__)     _|| \\\\_ (__) )(    |||_     |||_ .-,//|(_  "
            + System.lineSeparator() + PADDING
            + " (_\")  (_/     (__)  (__)         (__) (__)    (__)  (__)_)   (__)_) \\_) (__) "
            + System.lineSeparator();

    private final Scanner sc = new Scanner(System.in);

    /**
     * Gets user input from the standard input.
     *
     * @return the user input up to the next newline
     */
    public String readUserResponse() {
        return sc.nextLine().strip();
    }

    /** Greets user by displaying logo. */
    public void printGreeting() {
        System.out.print(LINE);
        System.out.print(LOGO);
        System.out.println(LINE);
    }

    /**
     * Greets user on exiting the program.
     */
    public void printExit() {
        System.out.print(LINE);
        System.out.println(PADDING + "Bye!");
        System.out.print(LINE);
    }

    public void printMessage(String message) {
        System.out.print(LINE);
        System.out.println(PADDING + message);
        System.out.println(LINE);
    }

    /**
     * Prints a message to acknowledge that the specified task has been added.
     *
     * @param task the task that has been added
     * @param size the number of tasks in the list
     */
    // TODO: Fix print formatting
    public void printTaskAdded(Task task, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Noted. I've added this task:");
        System.out.println(PADDING + "  " + task);
        System.out.println(PADDING + "Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints a message to acknowledge that the specified lesson has been added.
     *
     * @param lesson the lesson that has been added
     * @param size the number of lessons in the list
     */
    public void printLessonAdded(Lesson lesson, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Noted. I've added this lesson:");
        System.out.println(PADDING + "  " + lesson);
        System.out.println(PADDING + "Now you have " + size + " lessons in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints a message to acknowledge that all tasks and lessons have been deleted.
     */
    public void printDeletedAll() {
        System.out.print(LINE);
        System.out.println(PADDING + "All your tasks and lessons have been deleted.");
        System.out.println(LINE);
    }

    /**
     * Prints a message to acknowledge that all lessons have been deleted.
     */
    public void printDeletedAllLessons() {
        System.out.print(LINE);
        System.out.println(PADDING + "All your lessons have been deleted.");
        System.out.println(LINE);
    }

    /**
     * Prints a message to acknowledge that the specified lesson has been deleted.
     *
     * @param lesson the Lesson type object that has been deleted
     * @param size the number of remaining lessons in the list
     */
    public void printDeletedLesson(Lesson lesson, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Ok. The following lesson has been deleted:");
        System.out.println(PADDING + "  " + lesson);
        System.out.println(PADDING + "Now you have " + size + " lesson(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints a message to acknowledge that all tasks have been deleted.
     */
    public void printDeletedAllTasks() {
        System.out.print(LINE);
        System.out.println(PADDING + "All your tasks have been deleted.");
        System.out.println(LINE);
    }

    /**
     * Prints a message to acknowledge that the specified task has been deleted.
     * @param task the Task type object that has been deleted
     * @param size the number of remaining tasks in the list
     */
    public void printDeletedTask(Task task, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Ok. The following task has been deleted:");
        System.out.println(PADDING + "  " + task);
        System.out.println(PADDING + "Now you have " + size + " task(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays the task list.
     *
     * @param taskList the task list
     */
    public void printTaskList(TaskList taskList) {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the tasks in your list:");
        System.out.print(taskList);
        System.out.println(LINE);
    }

    /**
     * Displays the lesson list.
     *
     * @param lessonList the lesson list
     */
    public void printLessonList(LessonList lessonList) {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the lessons in your list:");
        System.out.print(lessonList);
        System.out.println(LINE);
    }

    /**
     * Displays the task and lesson list.
     *
     * @param taskList the task list
     * @param lessonList the lesson list
     */
    public void printAllList(TaskList taskList, LessonList lessonList) {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the tasks in your list:");
        System.out.print(taskList);
        System.out.println(PADDING + "Here are the lessons in your list:");
        System.out.print(lessonList);
        System.out.println(LINE);
    }

    /**
     * Displays the filtered task list based on the specified keyword.
     *
     * @param taskList the initial task list
     * @param keyword the specified keyword
     */
    public void printTasksWithKeyword(TaskList taskList, String keyword) {
        TaskList filteredTaskList = taskList.filterTasksByKeyword(keyword);

        System.out.print(LINE);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "There is no matching task in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching tasks in your list:");
            System.out.print(filteredTaskList);
        }
        System.out.println(LINE);
    }

    /**
     * Displays the filtered lesson list based on the specified keyword.
     *
     * @param lessonList the initial lesson list
     * @param keyword the specified keyword
     */
    public void printLessonsWithKeyword(LessonList lessonList, String keyword) {
        LessonList filteredLessonList = lessonList.filterLessonsByKeyword(keyword);

        System.out.print(LINE);
        if (filteredLessonList.isEmpty()) {
            System.out.println(PADDING + "There is no matching lesson in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching lessons in your list:");
            System.out.print(filteredLessonList);
        }
        System.out.println(LINE);
    }

    /**
     * Displays the filtered task and lesson list based on the specified keyword.
     *
     * @param taskList the initial task list
     * @param lessonList the initial lesson list
     * @param keyword the specified keyword
     */
    public void printAllWithKeyword(TaskList taskList, LessonList lessonList, String keyword) {
        System.out.print(LINE);

        TaskList filteredTaskList = taskList.filterTasksByKeyword(keyword);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "There is no matching task in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching tasks in your list:");
            System.out.print(filteredTaskList);
        }

        LessonList filteredLessonList = lessonList.filterLessonsByKeyword(keyword);
        if (filteredLessonList.isEmpty()) {
            System.out.println(PADDING + "There is no matching lesson in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching lessons in your list:");
            System.out.print(filteredLessonList);
        }

        System.out.println(LINE);
    }

    /**
     * Displays the filtered task list based on the specified keyword.
     *
     * @param taskList the initial task list
     * @param period the specified period
     */
    public void printTasksWithPeriod(TaskList taskList, String period) {
        TaskList filteredTaskList = taskList.filterTasksByPeriod(period);

        System.out.print(LINE);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "There is no matching task in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching tasks in your list:");
            System.out.print(filteredTaskList);
        }
        System.out.println(LINE);
    }

    /**
     * Displays the filtered lesson list based on the specified keyword.
     *
     * @param lessonList the initial lesson list
     * @param period the specified period
     */
    public void printLessonsWithPeriod(LessonList lessonList, String period) {
        LessonList filteredLessonList = lessonList.filterLessonsByPeriod(period);

        System.out.print(LINE);
        if (filteredLessonList.isEmpty()) {
            System.out.println(PADDING + "There is no matching lesson in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching lessons in your list:");
            System.out.print(filteredLessonList);
        }
        System.out.println(LINE);
    }

    /**
     * Displays the filtered task and lesson list based on the specified keyword.
     *
     * @param taskList the initial task list
     * @param lessonList the initial lesson list
     * @param period the specified period
     */
    public void printAllWithPeriod(TaskList taskList, LessonList lessonList, String period) {
        System.out.print(LINE);

        TaskList filteredTaskList = taskList.filterTasksByPeriod(period);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "There is no matching task in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching tasks in your list:");
            System.out.print(filteredTaskList);
        }

        LessonList filteredLessonList = lessonList.filterLessonsByPeriod(period);
        if (filteredLessonList.isEmpty()) {
            System.out.println(PADDING + "There is no matching lesson in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching lessons in your list:");
            System.out.print(filteredLessonList);
        }

        System.out.println(LINE);
    }

    /**
     * Displays the task marked as done.
     * 
     * @param taskList the task list
     * @param task the task marked as done
     */
    public void printDoneTask(TaskList taskList, Task task) {
        System.out.print(LINE);
        System.out.println(PADDING + "Nice! I've marked this task as done: ");
        System.out.println(PADDING + task);
        System.out.println(PADDING + "Now you have " + taskList.getNumberOfPendingTasks() + " pending tasks.");
        System.out.println(LINE);
    }
}
