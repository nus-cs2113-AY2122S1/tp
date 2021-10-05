package terminus.content;

public class Link {

    private String description;
    private String day;
    private String startTime;
    private String zoomLink;
    final public static String TYPE = "Z";

    final private String DISPLAY_MESSAGE = "%s, %s, %s, %s";

    public Link(String description, String day, String startTime, String zoomLink) {
        this.description = description;
        this.day = day;
        this.startTime = startTime;
        this.zoomLink = zoomLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getZoomLink() {
        return zoomLink;
    }

    public void setZoomLink(String zoomLink) {
        this.zoomLink = zoomLink;
    }

    public String getDisplayInfo() {
        return String.format(DISPLAY_MESSAGE, description, day, startTime, zoomLink);
    }
}
