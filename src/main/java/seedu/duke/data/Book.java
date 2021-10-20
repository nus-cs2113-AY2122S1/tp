package seedu.duke.data;

import seedu.duke.Status;

public class Book extends Item {
    private String author;

    /**
     * Constructor for class Book.
     *
     * @param title  The title of the book.
     * @param id     The unique attribute of the book.
     * @param status The status of the book, can be either "Loaned" or "Available".
     * @param author The author of the book.
     */
    public Book(String title, String id, Status status, String author) {
        super(title, id, status);
        this.author = author;
    }

    /**
     * Getter method that returns the author of the book.
     * @return author The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter method that sets author attribute.
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
        return "[B]" + super.toString() + super.separator + getAuthor();
    }
}
