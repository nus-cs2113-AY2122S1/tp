package seedu.foodorama.command;

public enum CommandNames {
    ADD_DISH_WASTE("add dish waste", new AddDishWasteCommand()),
    ADD_INGR_WASTE("add ingr waste", new AddIngrWasteCommand()),
    ADD_INGR_STORED("add ingr stored", new AddIngrStoredCommand()),
    SET_INGR_EXPIRY("set ingr expiry", new SetIngrExpiryCommand()),
    SET_DISH_LIMIT("set dish limit", new SetDishLimitCommand()),
    SET_INGR_LIMIT("set ingr limit", new SetIngrLimitCommand()),
    LINK("link", new LinkCommand()),
    ADD_DISH("add dish", new AddDishCommand()),
    ADD_INGR("add ingr", new AddIngrCommand()),
    DELETE_DISH("del dish", new DeleteDishCommand()),
    DELETE_INGR("del ingr", new DeleteIngrCommand()),
    LIST("list", new ListCommand()),
    FIND("find", new FindCommand()),
    GRAPH("graph", new GraphCommand()),
    RAND_RISH_NAME("rdish", new RandomDishCommand()),
    CLEAR_DISH("clear dish", new ClearDishCommand()),
    CLEAR_INGR("clear ingr", new ClearIngrCommand()),
    CLEAR_ALL("clear all", new ClearAllCommand()),
    HELP("help", new HelpCommand()),
    SORT_DISH("sort dish", new SortDishCommand()),
    SORT_INGR("sort ingr", new SortIngrCommand()),
    EDIT_DISH_NAME("edit dish name", new EditDishNameCommand()),
    EDIT_DISH_WASTE("edit dish waste", new EditDishWasteCommand()),
    EDIT_INGR_NAME("edit ingr name", new EditIngrNameCommand()),
    EDIT_INGR_WASTE("edit ingr waste", new EditIngrWasteCommand()),
    EDIT_INGR_STORAGE("edit ingr stored", new EditIngrStoredCommand());


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
