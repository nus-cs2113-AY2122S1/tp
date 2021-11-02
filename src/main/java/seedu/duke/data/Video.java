package seedu.duke.data;

import seedu.duke.common.Status;

import java.time.LocalDate;

//@@author exetr
public class Video extends Item {
    private String publisher;
    private String duration;

    /**
     * Constructor for class item.
     *
     * @param title  The title of the Video.
     * @param id     The unique attribute of the Video.
     * @param status The status of the Video, can be either "Loaned" or "Available".
     * @param loanee Person who has loaned or reserved the item
     * @param dueDate Date when item should be returned.
     * @param publisher The publisher of the video.
     * @param duration The duration of the video.
     */
    public Video(String title, String id, Status status, String loanee, LocalDate dueDate,
                 String publisher, String duration) {
        super(title, id, status, loanee, dueDate);
        this.publisher = publisher;
        this.duration = duration;
    }

    /**
     * Default constructor for Audio class
     */
    public Video() {
    }

    /**
     * Gets the publisher attribute.
     * @return publisher The publisher of the video.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Gets the duration attribute.
     * @return duration The duration of the video.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the publisher attribute.
     * @param publisher The input publisher to be set.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Sets the duration attribute.
     * @param duration The input duration to be set.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Converts the video object to string type.
     * @return A string that represents a video object.
     */
    @Override
    public String toString() {
        return "[V] " + super.toString() + super.separator + getPublisher()
                + super.separator + getDuration();
    }
}
