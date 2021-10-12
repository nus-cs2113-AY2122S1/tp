package terminus.content;

import java.time.LocalTime;

/**
 * Link class to represent a content of type link.
 */
public class Link extends Content {

    private String day;
    private LocalTime startTime;
    private String link;
    public static final String TYPE = "Z";

    private static final String DISPLAY_LINK_MESSAGE = "%s (%s, %s): %s";

    public Link(String name, String day, LocalTime startTime, String link) {
        super(name);
        this.day = day;
        this.startTime = startTime;
        this.link = link;
    }

    /**
     * Returns the day of the Link object.
     *
     * @return A string containing the day of the Link object
     */
    public String getDay() {
        return day;
    }

    /**
     * Sets the day of the Link object.
     *
     * @param day The new day to be set for the Link object
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Returns the startTime of the Link object.
     *
     * @return A LocalTime object containing the startTime of the Link object
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the startTime of the Link object.
     *
     * @param startTime The new startTime to be set for the Link object
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the link of the Link object.
     *
     * @return A String containing the link of the Link object
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the link of the Link object.
     *
     * @param link The new link to be set for the Link object
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Returns all the attributes of the Link object.
     *
     * @return A string containing all the attributes of the Link object
     */
    @Override
    public String getDisplayInfo() {
        return String.format(DISPLAY_LINK_MESSAGE, this.name, day, startTime, link);
    }

    /**
     * Returns all the attributes' information of the Link object.
     *
     * @return A method to display all the attributes of the Link object
     */
    @Override
    public String getViewDescription() {
        return getDisplayInfo();
    }
}
