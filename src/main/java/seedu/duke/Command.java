package seedu.duke;


public class Command {
    protected TaskList tasksList;
    private int targetIndex = -1;
    private String targetTask = null;

    /** Constructor for Command class.*/
    protected Command() {
    }

    /**
     * Constructor for Command class.
     *
     * @param targetIndex The target task index to be done or deleted.
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    /**
     * Supply the method to execute command and to be implemented by child class.
     *
     * @throws UnsupportedOperationException because it is implemented by child class.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("Implemented by Child Class");
    }

    /** Locate the tasksList. */
    public void setData(TaskList tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * Get the target Task
     *
     * @return the target task by getting its index.
     * @throws IndexOutOfBoundsException the target number may out of range.
     */
    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return tasksList.get(getTargetIndex());
    }

    /** Get the target task's index.
     *
     * @return the target task's index.
     */
    public int getTargetIndex() {
        return targetIndex;
    }

    /** locate the target task index. */
    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
}
