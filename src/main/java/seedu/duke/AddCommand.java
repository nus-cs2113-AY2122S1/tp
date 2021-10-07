package seedu.duke;

//public class AddCommand extends Command{
//
//    private final Task toAdd;
//    public static final String MESSAGE = "     Got it. I've added this task:";
//
//    /**
//     * Constructor for AddCommand class.
//     *
//     * @param userInput The command as user input.
//     */
//    public AddCommand(String userInput) throws StringIndexOutOfBoundsException {
//
//        if(userInput.contains("todo")) {
//            this.toAdd = new Todo(userInput);
//        } else if (userInput.contains("event")) {
//            this.toAdd = new Event(userInput);
//        } else if (userInput.contains("deadline")) {
//            this.toAdd = new Deadline(userInput);
//        } else {
//            this.toAdd = null;
//        }
//
//    }
//
//    /**
//     * Executes the Todo/Deadline/Event commands.
//     *
//     * @return the result of command includes the message shown to user, the task added and total tasks number.
//     */
//    @Override
//    public CommandResult execute() {
//        tasksList.add(toAdd);
//        return new CommandResult(MESSAGE, toAdd, tasksList.size());
//    }
//}