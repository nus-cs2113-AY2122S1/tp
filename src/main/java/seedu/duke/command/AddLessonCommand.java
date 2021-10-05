package seedu.duke.command;

public class AddLessonCommand extends Command {
    private String title;
    private String dayOfTheWeek;
    private String startTime;
    private String endTime;

    public AddLessonCommand(String title, String dayOfTheWeek, String startTime, String endTime) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
