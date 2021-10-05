package seedu.duke.command;

public enum CommandNames {
    ADD_DISH_WASTE("add dish waste", new AddDishWasteCommand()),
    ADD_INGR_WASTE("add ingr waste", new AddIngrWasteCommand()),
    ADD_CONSTITUENT("add constituent", new AddDishIngrCommand()),
    ADD_DISH("add dish", new AddDishCommand()),
    ADD_INGR("add ingr", new AddIngrCommand()),
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
