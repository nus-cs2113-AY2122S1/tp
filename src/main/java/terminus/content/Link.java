package terminus.content;

public class Link extends Content {

    private String day;
    private String startTime;
    private String zoomLink;
    public static final String TYPE = "Z";

    private static final String DISPLAY_LINK_MESSAGE = "%s, %s, %s, %s";

    public Link(String name, String day, String startTime, String zoomLink) {
        super(name);
        this.day = day;
        this.startTime = startTime;
        this.zoomLink = zoomLink;
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

    @Override
    public String getDisplayInfo() {
        return String.format(DISPLAY_LINK_MESSAGE, this.name, day, startTime, zoomLink);
    }

    @Override
    public String getViewDescription() {
        return getDisplayInfo();
    }
}
