package seedu.duke;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Entry.initializeFiles();
        Ui.printGreeting();
        Parser.waitForQuery();
    }
}
