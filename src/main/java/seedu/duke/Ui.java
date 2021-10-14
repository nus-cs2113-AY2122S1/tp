package seedu.duke;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {

    private static final String CLEAR_MESSAGE = "All clients have been deleted";
    private static final String CUT_MESSAGE = "Client has been deleted:";
    private static final String ADD_MESSAGE = "Client has been added:";
    private static final String BYE_MESSAGE = "Thanks for using TourPlanner. Goodbye!";

    private static Scanner in = new Scanner(System.in);

    /**
     * Empty Ui class constructor.
     */
    public Ui() {
        ;
    }

    /**
     * Prints a divider to the text Ui.
     */
    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a welcome message to the user on the text Ui.
     */
    public void showWelcome() {
        showLine();
        String logo = " █████  ███  █   █ ████  ████  █      ████  █    █ █    █ █████ ████\n"
                + "   █   █   █ █   █ █   █ █   █ █     █    █ ██   █ ██   █ █     █   █\n"
                + "   █   █   █ █   █ █████ ████  █     ██████ █ █  █ █ █  █ █████ █████\n"
                + "   █   █   █ █   █ █  █  █     █     █    █ █  █ █ █  █ █ █     █  █\n"
                + "   █    ███   ███  █   █ █     █████ █    █ █   ██ █   ██ █████ █   █\n";
        String greet = "Hello, Welcome to TourPlanner!\n"
                + "What can I do for you?";
        show(logo);
        show(greet);
        showLine();
    }

    /**
     * Prompts for user's input and read's the text entered by the user.
     *
     * @return full input entered by the user
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Outputs to the CLI the text to show the user.
     * Creates a newline as well.
     *
     * @param textToShow the output text intended for the user
     */
    public void show(String textToShow) {
        System.out.print(textToShow + System.lineSeparator());
    }

    /**
     * Ui response to clear client list command.
     */
    public void showClear() {
        show(CLEAR_MESSAGE);
    }

    /**
     * Ui response to cut client command.
     *
     * @param client the client object that was cut
     */
    public void showCut(Client client) {
        show(CUT_MESSAGE + "\n" + client);
    }

    /**
     * Ui response to add client command.
     *
     * @param client the client that was just added
     */
    public void showAdd(Client client) {
        show(ADD_MESSAGE + "\n" + client);
    }

    /**
     * Prints an exit message to the text Ui to acknowledge exiting the application.
     */
    public void showBye() {
        show(BYE_MESSAGE);
        showLine();
    }
}

