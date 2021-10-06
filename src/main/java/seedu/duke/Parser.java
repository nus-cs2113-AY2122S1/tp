package seedu.duke;

public class Parser {

    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final String EXIT = "bye";
    public static final String DONE = "done";
    public static final String FALSE_MESSAGE = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String INVALID_TODO_ADD_MESSAGE = "     ☹ OOPS!!! The description of a todo cannot be empty ";
    public static final String INVALID_EVENT_ADD_MESSAGE = "     ☹ OOPS!!! The description of an event cannot be empty " +
            "or incorrect format.";
    public static final String INVALID_DEADLINE_ADD_MESSAGE = "     ☹ OOPS!!! The description of a deadline cannot be empty " +
            "or incorrect format.";

    /**
     * Return a new command class to execute based on the user input.
     *
     * @param userInput contains the command
     * @return the new command class
     */
    public Command parseCommand (String userInput) {

        String[] words = userInput.trim().split(" ", 2);

        switch(words[0]) {

            case LIST:
                return new ListCommand();
            case TODO:
                if(userInput.equals("todo")) {
                    return new FalseCommand(INVALID_TODO_ADD_MESSAGE);
                } else {
                    return new AddCommand(userInput);
                }
            case EVENT:
                try {
                    return new AddCommand(userInput);
                } catch (StringIndexOutOfBoundsException e) {
                    return new FalseCommand(INVALID_EVENT_ADD_MESSAGE);
                }
            case DEADLINE:
                try {
                    return new AddCommand(userInput);
                } catch (StringIndexOutOfBoundsException e) {
                    return new FalseCommand(INVALID_DEADLINE_ADD_MESSAGE);
                }
            case DONE:
                return new DoneCommand(Integer.parseInt(userInput.substring(5)) - 1);
            case DELETE:
                return new DeleteCommand(Integer.parseInt(userInput.substring(7)) - 1);
            case FIND:
                return new FindCommand(userInput);
            case EXIT:
                return new ExitCommand();
            default:
                return new FalseCommand(FALSE_MESSAGE);
        }
    }
}

