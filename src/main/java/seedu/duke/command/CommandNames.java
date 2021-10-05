package seedu.duke.command;

public enum CommandNames {
    ADD("add", new AddCommand()),
    LIST("list", new ListCommand()),
    TEST("test", new TestCommand());


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
