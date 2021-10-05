package seedu.duke.command;

public class AddLessonCommand extends Command {
    private String lessonDescription;
    private String dayOfTheWeek;
    private String startTime;
    private String endTime;

    public AddLessonCommand(String lessonDescription, String dayOfTheWeek, String startTime, String endTime) {
        this.lessonDescription = lessonDescription;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
