package seedu.duke.data;

import seedu.duke.Status;

/**
 * The Item class is a class representing a item in the library.
 */
public class Item {
    private String title;
    private String id;
    private Status status;

    protected String separator = " | ";

    /**
     * Constructor for class item.
     * @param title The title of the item.
     * @param id The unique attribute of the item.
     * @param status The status of the item, can be either "Loaned" or "Available".
     */
    public Item(String title, String id, Status status) {
        this.title = title;
        this.id = id;
        this.status = status;
    }

    /**
     * Getter method that returns the title attribute.
     * @return title The title of the item in String.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method that sets the title attribute.
     * @param title The input title to be set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method that returns the id attribute.
     * @return id The id of the item in String.
     */
    public String getID() {
        return id;
    }

    /**
     * Setter method that returns the id attribute.
     * @param id The id of the item in String.
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Getter method that returns the status attribute.
     * @return status The status of the item in String.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Setter method that sets the status method.
     * @param status The input status to be set.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Convert the item object to string type.
     * @return A string that represents an item object.
     */
    @Override
    public String toString() {
        return getID() + separator + getStatus() + separator + getTitle();
    }
}