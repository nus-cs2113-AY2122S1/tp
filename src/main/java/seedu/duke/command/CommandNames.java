package seedu.duke.command;

import seedu.duke.TestCommand;

public enum CommandNames {
    ADD("Add", new AddCommand()),
    TEST("Test", new TestCommand());

    private String name;
    private Command callbackCommand;
    CommandNames(String name, Command callbackCommand) {
        this.name = name;
        this.callbackCommand = callbackCommand;
    }

    public String getName() {
        return name;
    }

    public Command getCallbackCommand() {
        return callbackCommand;
    }
}
