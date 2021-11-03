package seedu.duke.data;

import seedu.duke.common.Status;

import java.time.LocalDate;

public class Miscellaneous extends Item {
    /**
     * Constructor for Miscellaneous class.
     * @param title Title of item
     * @param id    Unique identifier for item.
     * @param status The status of the item, can be either "Loaned", "Available" or "Reserved".
     * @param loanee Person who has loaned or reserved the item
     * @param dueDate Date when item should be returned.
     */
    public Miscellaneous(String title, String id, Status status, String loanee, LocalDate dueDate) {
        super(title, id, status, loanee, dueDate);
    }

    /**
     * Default constructor for miscellaneous class.
     */
    public Miscellaneous() {
    }

    /**
     * Converts the miscellaneous object to string type.
     * @return A string that represents an miscellaneous object.
     */
    @Override
    public String toString() {
        return "[-] " + super.toString();
    }
}
