package seedu.duke;

import java.util.Scanner;

public class Ui {

    private static final String CLEAR_MESSAGE = "All clients have been deleted";
    private static final String CUT_MESSAGE = "Client has been deleted:";
    private static final String ADD_MESSAGE = "Client has been added:";
    private static final String BYE_MESSAGE = "Thanks for using TourPlanner. Goodbye!";

    private static Scanner in = new Scanner(System.in);

    public Ui() {
        ;
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        String logo = " █████  ███  █   █ ████  ████  █      ████  █    █ █    █ █████ ████\n"
               + "   █   █   █ █   █ █   █ █   █ █     █    █ ██   █ ██   █ █     █   █\n"
               + "   █   █   █ █   █ █████ ████  █     ██████ █ █  █ █ █  █ █████ █████\n"
               + "   █   █   █ █   █ █  █  █     █     █    █ █  █ █ █  █ █ █     █  █\n"
               + "   █    ███   ███  █   █ █     █████ █    █ █   ██ █   ██ █████ █   █\n";
        String logo2 = "gg";
        String greet = "Hello, Welcome to TourPlanner!\n"
                + "What can I do for you?";
        show(logo2);
        show(greet);
        showLine();
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void show(String textToShow) {
        System.out.print(textToShow);
    }

    public void showClear() {
        show(CLEAR_MESSAGE);
        showLine();
    }

    public void showCut(Client client) {
        show(CUT_MESSAGE + "\n" + client);
        showLine();
    }

    public void showAdd(Client client) {
        show(ADD_MESSAGE + "\n" + client);
        showLine();
    }

    public void showBye() {
        show(BYE_MESSAGE);
        showLine();
    }
}

