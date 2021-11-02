package seedu.duke.data;

import seedu.duke.common.Status;

import java.time.LocalDate;

//@@author exetr
public class Book extends Item {
    private String author;

    /**
     * Constructor for class Book.
     *
     * @param title  The title of the book.
     * @param id     The unique attribute of the book.
     * @param loanee Person who has loaned or reserved the item
     * @param dueDate Date when item should be returned.
     * @param status The status of the book, can be either "Loaned" or "Available".
     * @param author The author of the book.
     */
    public Book(String title, String id, Status status, String loanee, LocalDate dueDate, String author) {
        super(title, id, status, loanee, dueDate);
        this.author = author;
    }

    /**
     * Default constructor for Book class
     */
    public Book() {
    }

    /**
     * Gets the author of the book.
     * @return author The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author attribute.
     * @param author THe author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Generates string representation of the book object.
     * @return String Representation of the book object.
     */
    @Override
    public String toString() {
        return "[B] " + super.toString() + super.separator + getAuthor();
    }
}
