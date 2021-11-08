package seedu.duke.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import seedu.duke.common.Status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//@@author silinche
/**
 * The Item class is a class representing an item in the library.
 */
public abstract class Item {
    private String title;
    private String id;
    private Status status;
    private String loanee;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate;

    // Date functions
    public static final String dateFormat = "dd-MM-yyyy";
    protected DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(dateFormat);

    // Literals
    protected String separator = " | ";

    /**
     * Constructor for class item.
     * @param title The title of the item.
     * @param id The unique attribute of the item.
     * @param status The status of the item, can be either "Loaned", "Available" or "Reserved".
     * @param loanee The username of the person loaning or reserving the item.
     * @param dueDate The date when the item should be returned.
     */
    public Item(String title, String id, Status status, String loanee, LocalDate dueDate) {
        this.title = title;
        this.id = id;
        this.status = status;
        this.loanee = loanee;
        this.dueDate = dueDate;
    }

    /**
     * Default constructor.
     */
    public Item() {
    }

    /**
     * Gets the title attribute.
     * @return title The title of the item in String.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title attribute.
     * @param title The input title to be set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the id attribute.
     * @return id The id of the item in String.
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the id attribute.
     * @param id The id of the item in String.
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Gets the status attribute.
     * @return status The status of the item in String.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status method.
     * @param status The input status to be set.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    //@@author
    /**
     * Gets the loanee attribute.
     * @return loanee Username of a person.
     */
    public String getLoanee() {
        return loanee;
    }

    /**
     * Sets the loanee attribute.
     * @param loanee Username of a person.
     */
    public void setLoanee(String loanee) {
        this.loanee = loanee;
    }

    /**
     * Gets the dueDate attribute.
     * @return LocalDate date object representing due date.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Gets the dueDate attribute as a string.
     * @return String date representing due date.
     */
    public String dueDateToString() {
        String output;
        try {
            output = dueDate.format(dtFormatter);
        } catch (NullPointerException e) {
            output = null;
        }
        return output;
    }

    /**
     * Sets the dueDate attribute.
     * @param dueDate LocalDate object representing when item should be returned.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Sets the dueDate attribute.
     * @param dueDate String representing when item should be returned.
     */
    public void setDueDate(String dueDate) throws DateTimeParseException {
        this.dueDate = LocalDate.parse(dueDate, dtFormatter);
    }

    /**
     * Resets the dueDate attribute.
     */
    public void setDueDate() {
        this.dueDate = null;
    }

    public void setDueDate(String[] date) {
        this.dueDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]),Integer.parseInt(date[2]));
    }

    /**
     * Converts the item object to string type.
     * @return A string that represents an item object.
     */
    @Override
    public String toString() {
        String output;
        if (status.equals(Status.RESERVED)) {
            output = getID() + separator + getStatus() + " (" + getLoanee() + ")" + separator + getTitle();
        } else if (status.equals(Status.LOANED)) {
            output = getID() + separator + getStatus() + " (" + getLoanee() + " TILL " + dueDateToString() + ")"
                    + separator + getTitle();
        } else {
            output = getID() + separator + getStatus() + separator + getTitle();
        }
        return output;
    }
}