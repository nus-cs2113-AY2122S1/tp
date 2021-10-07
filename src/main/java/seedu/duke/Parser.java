package seedu.duke;

public class Parser {

    public static final String LIST = "list";
    public static final String ADD = "add";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    public static final String DELETE = "remove";
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

        String[] words = userInput.trim().split(" ", 3);

        switch(words[0]) {

//            case LIST:
//                return new ListCommand();
//            case ADD:
//                if(words[1].equals("employee")) {
//                    return new AddCommand(userInput);
//                } else if(words[1].equals("menu")) {
//                    return new AddCommand(userInput);
//                } else if(words[1].equals("storage")) {
//                    return new AddCommand(userInput);
//                } else {
//                    return new FalseCommand(FALSE_MESSAGE);
//                }
//            case DONE:
//                return new DoneCommand(Integer.parseInt(userInput.substring(5)) - 1);
//            case DELETE:
//                if(words[1].equals("employee")) {
//                    return new DeleteCommand(Integer.parseInt(words[2]));
//                } else if(words[1].equals("menu")) {
//                    return new DeleteCommand(Integer.parseInt(words[2]));
//                } else if(words[1].equals("storage")) {
//                    return new DeleteCommand(Integer.parseInt(words[2]));
//                } else {
//                    return new FalseCommand(FALSE_MESSAGE);
//                }
//            case FIND:
//                return new FindCommand(userInput);
            case EXIT:
                return new ExitCommand();
            default:
                return new FalseCommand(FALSE_MESSAGE);
        }
    }
}

