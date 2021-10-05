package seedu.duke.command;

public class AddTaskCommand extends Command {
    private String taskDescription;
    private String dayOfTheWeek;
    private String information;

    public AddTaskCommand(String taskDescription, String dayOfTheWeek, String information) {
        this.taskDescription = taskDescription;
        this.dayOfTheWeek = dayOfTheWeek;
        this.information = information;
    }
}
