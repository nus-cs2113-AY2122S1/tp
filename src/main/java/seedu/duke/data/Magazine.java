package seedu.duke.data;

import seedu.duke.common.Status;

//@@author exetr
public class Magazine extends Item {
    private String publisher;
    private String edition;


    /**
     * Constructor for class Magazine.
     *
     * @param title  The title of the magazine.
     * @param id     The unique attribute of the magazine.
     * @param status The status of the magazine, can be either "Loaned" or "Available".
     * @param publisher The publisher of the magazine.
     * @param edition The edition of the magazine.
     */
    public Magazine(String title, String id, Status status, String publisher, String edition) {
        super(title, id, status);
        this.publisher = publisher;
        this.edition = edition;
    }

    /**
     * Getter method that returns publisher attribute.
     * @return String Publisher of the magazine.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Getter method that returns edition attribute.
     * @return String Edition of the magazine.
     */
    public String getEdition() {
        return edition;
    }

    /**
     * Setter method that sets publisher attribute.
     * @param publisher The publisher of the magazine.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Setter method that sets edition attribute.
     * @param edition The edition of the magazine.
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     * Convert the magazine object to string type.
     * @return A string that represents a magazine object.
     */
    @Override
    public String toString() {
        return "[M] " + super.toString() + super.separator + getPublisher()
                + super.separator + getEdition();
    }
}
