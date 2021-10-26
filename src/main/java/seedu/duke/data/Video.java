package seedu.duke.data;

import seedu.duke.common.Status;

public class Video extends Item {
    private String publisher;
    private String duration;

    /**
     * Constructor for class item.
     *
     * @param title  The title of the Video.
     * @param id     The unique attribute of the Video.
     * @param status The status of the Video, can be either "Loaned" or "Available".
     * @param publisher The publisher of the video.
     * @param duration The duration of the video.
     */
    public Video(String title, String id, Status status, String publisher, String duration) {
        super(title, id, status);
        this.publisher = publisher;
        this.duration = duration;
    }

    /**
     * Getter method that returns the publisher attribute.
     * @return publisher The publisher of the video.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Getter method that returns the duration attribute.
     * @return duration The duration of the video.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Setter method that sets the publisher attribute.
     * @param publisher The input publisher to be set.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Setter method that sets the duration attribute.
     * @param duration The input duration to be set.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Convert the video object to string type.
     * @return A string that represents a video object.
     */
    @Override
    public String toString() {
        return "[V]" + super.toString() + super.separator + getPublisher()
                + super.separator + getDuration();
    }
}
