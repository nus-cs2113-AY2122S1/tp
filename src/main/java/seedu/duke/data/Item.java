package seedu.duke.data;

import seedu.duke.common.Status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Item class is a class representing a item in the library.
 */
public class Item {
    private String title;
    private String id;
    private Status status;
    private String loanee;
    private LocalDate dueDate;

    // Date functions
    public static final String dateFormat = "dd-MM-yyyy";
    protected DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(dateFormat);

    // Literals
    protected String separator = " | ";
    public static final String ARG_TITLE = "t/";
    public static final String ARG_ID = "i/";
    public static final String ARG_STATUS = "s/";
    public static final String ARG_LOANEE = "u/";
    public static final String ARG_DUE = "d/";

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
        this.loanee = null;
        this.dueDate = null;
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
     * Getter method that returns the loanee attribute.
     * @return loanee Username of a person.
     */
    public String getLoanee() {
        return loanee;
    }

    /**
     * Setter method that sets the loanee attribute.
     * @param loanee Username of a person.
     */
    public void setLoanee(String loanee) {
        this.loanee = loanee;
    }

    /**
     * Getter method that returns dueDate attribute.
     * @return LocalDate date object representing due date.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Getter method that returns dueDate attribute as a string.
     * @return String date representing due date.
     */
    public String getDueDateString() {
        return dueDate.format(dtFormatter);
    }

    /**
     * Setter method that sets the dueDate attribute.
     * @param dueDate LocalDate object representing when item should be returned.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Setter method that sets the dueDate attribute.
     * @param dueDate String representing when item should be returned.
     */
    public void setDueDate(String dueDate) throws DateTimeParseException {
        this.dueDate = LocalDate.parse(dueDate, dtFormatter);
    }

    /**
     * Setter method that resets the dueDate attribute.
     */
    public void setDueDate() {
        this.dueDate = null;
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