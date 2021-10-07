package seedu.duke.parser;

public class Parser {
    private String rawCommandString;

    public Parser(String rawCommandString) {
        this.rawCommandString = rawCommandString;
    }

    public boolean isAddBudget() {
        if (rawCommandString.split(" ")[0].equals("AddBudget")) {
            return true;
        }
        return false;
    }

    public boolean isBye() {
        if (rawCommandString.split(" ")[0].equals("bye")) {
            return true;
        }
        return false;
    }
}
