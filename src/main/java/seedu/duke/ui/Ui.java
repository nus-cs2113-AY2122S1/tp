package seedu.duke.ui;

/**
 * Text UI of the CLImods application.
 */
public class Ui {

    private static final String LOGO = "\n"
            + "  /$$$$$$  /$$       /$$$$$$                               /$$          \n"
            + " /$$__  $$| $$      |_  $$_/                              | $$          \n"
            + "| $$  \\__/| $$        | $$   /$$$$$$/$$$$   /$$$$$$   /$$$$$$$  /$$$$$$$\n"
            + "| $$      | $$        | $$  | $$_  $$_  $$ /$$__  $$ /$$__  $$ /$$_____/\n"
            + "| $$      | $$        | $$  | $$ \\ $$ \\ $$| $$  \\ $$| $$  | $$|  $$$$$$\n"
            + "| $$    $$| $$        | $$  | $$ | $$ | $$| $$  | $$| $$  | $$ \\____  $$\n"
            + "|  $$$$$$/| $$$$$$$$ /$$$$$$| $$ | $$ | $$|  $$$$$$/|  $$$$$$$ /$$$$$$$/\n"
            + " \\______/ |________/|______/|__/ |__/ |__/ \\______/  \\_______/|_______/\n"
            + "------------------------------------------------------------------------\n"
            + "Command-Line Interface for NUSMODS                              (v1.0.0)\n"
            + "------------------------------------------------------------------------";

    private static final String LINE_SEPARATOR = "---------------------------------------"
            + "---------------------------------";
    private static final String MESSAGE_BORDER = "|| ";

    public void printLogo() {
        System.out.println(LOGO);
    }

    public void printCursor() {
        System.out.print("[user]: ");
    }

    public void printMessage(String message) {
        String[] messageLines = message.split("\\r?\\n");
        for(int i = 0; i < messageLines.length; i++) {
            System.out.print(MESSAGE_BORDER + messageLines[i] + "\n");
        }
        System.out.println(LINE_SEPARATOR);
    }

}
