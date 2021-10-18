package seedu.duke.command;

public enum CommandNames {
    ADD_DISH_WASTE("add dish waste", new AddDishWasteCommand()),
    ADD_INGR_WASTE("add ingr waste", new AddIngrWasteCommand()),
    ADD_INGR_STORED("add ingr stored", new AddIngrStoredCommand()),
    ADD_CONSTITUENT("add constituent", new AddDishIngrCommand()),
    ADD_DISH("add dish", new AddDishCommand()),
    ADD_INGR("add ingr", new AddIngrCommand()),
    DELETE_DISH("del dish", new DeleteDishCommand()),
    DELETE_INGR("del ingr", new DeleteIngrCommand()),
    LIST("list", new ListCommand()),
    GRAPH("graph", new GraphCommand()),
    HELP("help", new HelpCommand());


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
