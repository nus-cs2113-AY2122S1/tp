package seedu.duke;

import java.util.Scanner;

public class Ui {
    private static final String CUT_MESSAGE = "Client has been deleted:";
    
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
        show(logo);
        show(greet);
        showLine();
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showCut(Client client) {
        show(CUT_MESSAGE + "\n" + client);
        showLine();
    }

    public void show(String textToShow) {
        System.out.print(textToShow);
    }
}

