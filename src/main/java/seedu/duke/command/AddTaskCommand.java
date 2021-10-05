package seedu.duke.command;

public class AddTaskCommand extends Command {
    private String title;
    private String dayOfTheWeek;
    private String information;

    public AddTaskCommand(String title, String dayOfTheWeek, String information) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.information = information;
    }
}
