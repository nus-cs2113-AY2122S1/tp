package seedu.duke;

import java.util.Scanner;

public class Ui {

    private static final String CLEAR_MESSAGE = "All clients have been deleted";

    private static Scanner in = new Scanner(System.in);

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
        String greet = "Hello, Welcome to TourPlanner!\n"
                + "What can I do for you?";
        System.out.println(logo);
        System.out.println(greet);
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
}

