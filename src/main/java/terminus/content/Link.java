package terminus.content;

import java.time.LocalTime;

/**
 * Link class to represent a content of type link.
 */
public class Link extends Content {

    private String day;
    private LocalTime startTime;
    private int duration;
    private String link;
    public static final String TYPE = "Z";

    private static final String DISPLAY_LINK_MESSAGE = "%s (%s, %s - %s): %s";

    public Link(String name, String day, LocalTime startTime, int duration, String link) {
        super(name);
        this.day = day;
        this.startTime = startTime;
        this.duration = duration;
        this.link = link;
    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Returns the end time from the start time and duration of the Link object.
     *
     * @return A LocalTime object containing the end time of the Link
     */
    public LocalTime getEndTime() {
        LocalTime endTime = startTime.plusHours(duration);
        LocalTime midnight = LocalTime.of(00, 00);
        if (endTime.equals(midnight)) {
            endTime = LocalTime.of(23, 59);
        }
        return endTime;
    }

    /**
     * Returns all the attributes of the Link object.
     *
     * @return A string containing all the attributes of the Link object.
     */
    @Override
    public String getDisplayInfo() {
        return String.format(DISPLAY_LINK_MESSAGE, this.name, day, startTime, getEndTime(), link);
    }

    /**
     * Returns all the attributes' information of the Link object.
     *
     * @return A method to display all the attributes of the Link object.
     */
    @Override
    public String getViewDescription() {
        return getDisplayInfo();
    }
}
