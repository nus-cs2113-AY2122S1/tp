package seedu.duke.data;

import seedu.duke.common.Status;

import java.time.LocalDate;

//@@author exetr
public class Audio extends Item {
    private String artist;
    private String duration;

    /**
     * Constructor for class Audio.
     *
     * @param title  The title of the audio.
     * @param id     The unique identifier of the audio.
     * @param status The status of the item, can be either "Loaned", "Available" or "Reserved".
     * @param loanee Person who has loaned or reserved the item
     * @param dueDate Date when item should be returned.
     * @param artist The artist of the audio.
     * @param duration The duration of the audio.
     */
    public Audio(String title, String id, Status status, String loanee, LocalDate dueDate,
                 String artist, String duration) {
        super(title, id, status, loanee, dueDate);
        this.artist = artist;
        this.duration = duration;
    }

    /**
     * Default constructor for audio class
     */
    public Audio() {
    }

    /**
     * Gets the artist attribute.
     * @return publisher The artist of the audio.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Sets the artist attribute.
     * @param artist The input artist to be set.
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Gets the duration attribute.
     * @return publisher The duration of the audio.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration attribute.
     * @param duration The input duration to be set.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Converts the audio object to string type.
     * @return A string that represents an audio object.
     */
    @Override
    public String toString() {
        return "[A] " + super.toString() + super.separator + getArtist()
                + super.separator + getDuration();
    }
}
