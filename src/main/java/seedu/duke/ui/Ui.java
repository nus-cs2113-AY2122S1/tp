package seedu.duke.ui;

/**
 * Text UI of the CLImods application.
 */
//@@author APZH
public class Ui {

    private static final String LOGO = "\n"
            + " _____        _                _  _   _       ___  ___            _\n"
            + "/  ___|      | |              | || | | |      |  \\/  |           | |\n"
            + "\\ `--.   ___ | |__    ___   __| || | | | _ __ | .  . |  ___    __| | ___\n"
            + " `--. \\ / __|| '_ \\  / _ \\ / _` || | | || '__|| |\\/| | / _ \\  / _` |/ __|\n"
            + "/\\__/ /| (__ | | | ||  __/| (_| || |_| || |   | |  | || (_) || (_| |\\__ \\\n"
            + "\\____/  \\___||_| |_| \\___| \\__,_| \\___/ |_|   \\_|  |_/ \\___/  \\__,_||___/\n"
            + "-------------------------------------------------------------------------\n"
            + "Command-Line Interface for NUSMODS                               (v1.0.0)\n"
            + "-------------------------------------------------------------------------";

    private static final String LINE_SEPARATOR = "---------------------------------------"
            + "----------------------------------";
    private static final String MESSAGE_BORDER = "|| ";

    public void printLogo() {
        System.out.println(LOGO);
    }

    public void printCursor() {
        System.out.print("[user]: ");
    }

    public void printMessage(String message) {
        String[] messageLines = message.split("\\r?\\n");
        for (int i = 0; i < messageLines.length; i++) {
            System.out.print(MESSAGE_BORDER + messageLines[i] + "\n");
        }
        System.out.println(LINE_SEPARATOR);
    }

}
