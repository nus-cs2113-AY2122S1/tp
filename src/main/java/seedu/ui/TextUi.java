package seedu.ui;

import java.util.Scanner;

public class TextUi {
    private static final String LOGO =
            "   _____         _______        _     \n" +
            "  / ____|       |__   __|      | |    \n" +
            " | |     ___  _ __ | | ___  ___| |__  \n" +
            " | |    / _ \\| '_ \\| |/ _ \\/ __| '_ \\ \n" +
            " | |___| (_) | | | | |  __/ (__| | | |\n" +
            "  \\_____\\___/|_| |_|_|\\___|\\___|_| |_|\n" +
            "                                      \n";

    private static final String LINE =
            "____________________________________________________________\n";

    private final Scanner scanner;

    public TextUi() {
        scanner = new Scanner(System.in);
        initMessage();
    }

    private static void initMessage() {
        System.out.println(LINE + LOGO + LINE);
        System.out.println("Welcome to ConTech, your personal contact tracker.\n" + LINE);
    }

}
