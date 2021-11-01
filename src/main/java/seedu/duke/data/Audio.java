package seedu.duke.data;

import seedu.duke.common.Status;

import java.time.LocalDate;

//@@author exetr
public class Audio extends Item {
    private String artist;
    private String duration;

    /**
     * Constructor for class item.
     *
     * @param title  The title of the item.
     * @param id     The unique attribute of the item.
     * @param status The status of the item, can be either "Loaned" or "Available".
     * @param artist The artist of the video.
     * @param duration The duration of the video.
     */
    public Audio(String title, String id, Status status, String loanee, LocalDate dueDate,
                 String artist, String duration) {
        super(title, id, status, loanee, dueDate);
        this.artist = artist;
        this.duration = duration;
    }

    /**
     * Getter method that returns the artist attribute.
     * @return publisher The artist of the audio.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Setter method that sets the artist attribute.
     * @param artist The input artist to be set.
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Getter method that returns the duration attribute.
     * @return publisher The duration of the audio.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Setter method that sets the duration attribute.
     * @param duration The input duration to be set.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Convert the audio object to string type.
     * @return A string that represents an audio object.
     */
    @Override
    public String toString() {
        return "[A] " + super.toString() + super.separator + getArtist()
                + super.separator + getDuration();
    }
}
