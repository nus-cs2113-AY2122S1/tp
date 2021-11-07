package seedu.duke;

public class Duke {
    /**
     * Main entry-point for the CCAManager application.
     */
    public static void main(String[] args) {
        Entry.initializeFiles();
        Ui.printGreeting();
        Parser.waitForQuery();
    }
}
