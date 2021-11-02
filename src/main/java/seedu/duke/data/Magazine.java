package seedu.duke.data;

import seedu.duke.common.Status;

import java.time.LocalDate;

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
     * @param loanee Person who has loaned or reserved the item
     * @param dueDate Date when item should be returned.
     * @param publisher The publisher of the magazine.
     * @param edition The edition of the magazine.
     */
    public Magazine(String title, String id, Status status, String loanee, LocalDate dueDate,
                    String publisher, String edition) {
        super(title, id, status, loanee, dueDate);
        this.publisher = publisher;
        this.edition = edition;
    }

    /**
     * Default constructor for Magazine class
     */
    public Magazine() {
    }

    /**
     * Gets publisher attribute.
     * @return String Publisher of the magazine.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Gets edition attribute.
     * @return String Edition of the magazine.
     */
    public String getEdition() {
        return edition;
    }

    /**
     * Sets publisher attribute.
     * @param publisher The publisher of the magazine.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Sets edition attribute.
     * @param edition The edition of the magazine.
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     * Converts the magazine object to string type.
     * @return A string that represents a magazine object.
     */
    @Override
    public String toString() {
        return "[M] " + super.toString() + super.separator + getPublisher()
                + super.separator + getEdition();
    }
}
