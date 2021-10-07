package seedu.duke;

//public class DeleteCommand extends Command{
//
//    public static final String MESSAGE_REMOVE = "     Noted. I've removed this task:";
//
//    /**
//     * Constructor of DeleteCommand Class.
//     *
//     * @param targetIndex the index of deleted task.
//     */
//    public DeleteCommand(int targetIndex) {
//        super(targetIndex);
//    }
//
//    /**
//     * Executes the Delete command.
//     *
//     * @return the result of command includes the message shown to user, the task deleted and total tasks number.
//     */
//    @Override
//    public CommandResult execute() {
//        try {
//            final Task target = getTargetTask();
//            tasksList.remove(target);
//            return new CommandResult(MESSAGE_REMOVE, target, tasksList.size());
//        }  catch (IndexOutOfBoundsException e) {
//            return new CommandResult(MESSAGE_INVALID_DELETE);
//        }
//    }
//
//}
