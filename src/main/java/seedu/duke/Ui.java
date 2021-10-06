package seedu.duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Ui {

    private static final String DIVIDER = "    ——————————————————————————————————————————————————————————————";
    private static final String SPACE = "    ";
    private static final String LIST_MESSAGE = "     Here are the tasks in your list:";
    private static final String ADD_MESSAGE = "     Now you have %d tasks in the list.";
    public static final String FIND_MESSAGE = "     Here are the matching tasks in your list:";
    private static final int INDEX_OFFSET = 1;
    private static final String POINT = ".";


    private final Scanner in;
    private final PrintStream out;

    /** constructor of Ui class. */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor of Ui class
     *
     * @param in the source user input.
     * @param out the space to display the outcome of programme.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /** show the start message to welcome using the programme. */
    public void showWelcomeMessage() {
        out.println(DIVIDER);
        out.println("     Hello! I'm Duke\n     What can I do for you?");
        out.println(DIVIDER);
    }

    /** show the end message for using the programme. */
    public void showGoodbyeMessage() {
        out.println(DIVIDER);
        out.println("     Bye. Hope to see you again soon!");
        out.println(DIVIDER);
    }

    /**
     * Display all the messages needed to show to user.
     *
     * @param message the needed messages to show.
     */
    public void showMessages(String ... message) {
        for(String m: message) {
            out.println(m);
        }
    }

    /**
     * Show the result to user.
     *
     * @param result the result executed by the command.
     */
    public void showResultToUser(CommandResult result) {
        if(result.feedback.equals("bye")) {
        } else if (result.feedback.contains("☹ OOPS!!!")) {
            showMessages(DIVIDER, result.feedback, DIVIDER);
        } else if (result.feedback.equals("list")) {
            showMessages(DIVIDER, LIST_MESSAGE);
            showTasksList(result.relevantTasks);
        } else if (result.feedback.equals("     Nice! I've marked this task as done:")) {
            showMessages(DIVIDER, result.feedback);
            out.println("     " + result.relevantTask);
            showMessages(DIVIDER);
        } else if (result.feedback.equals("find")) {
            showMessages(DIVIDER, FIND_MESSAGE);
            showTasksList(result.relevantTasks);
        } else {
            showMessages(DIVIDER, result.feedback);
            out.println("      " + result.relevantTask);
            showMessages(String.format(ADD_MESSAGE, result.size), DIVIDER);
        }
    }

    /**
     * Show the relevant tasks to user.
     *
     * @param tasksList the relevant tasks need to show to user.
     */
    public void showTasksList (List<Task> tasksList) {
        int taskIndex = INDEX_OFFSET;
        for(Task task: tasksList) {
            out.print(SPACE + taskIndex++ + POINT);
            out.println(task);
        }
        out.println(DIVIDER);
    }

    /**
     * Get the command from user.
     *
     * @return the user input command.
     */
    public String getUserCommand() {
        String inputLine;
        inputLine = in.nextLine();
        return inputLine;
    }
}