package seedu.duke.data;

/**
 * Represents a tour under the Tour agency.
 */
public class Tour {
    private final String id;
    private final String name;
    private final Float price;

    /**
     * Tour object constructor, that inputs the tour's information obtained from Add command.
     *
     * @param values the array of values of the tour, ordered in this manner:
     *               id, name, price
     */
    public Tour(String[] values) {
        id = values[0];
        name = values[1];
        price = Float.parseFloat(values[2]);
    }

    /**
     * Getter for id value in Tour object.
     *
     * @return tour's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for name value in Tour object.
     *
     * @return tour's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for price value in Tour object.
     *
     * @return tour's price.
     */
    public Float getPrice() {
        return price;
    }

    /**
     * Formats tour's information as a string that is viewable as an indexed list item.
     *
     * @return the formatted string containing tour's information
     */
    @Override
    public String toString() {
        return "Tour ID: " + id + "\n"
                + "Name: " + name + "\n"
                + "Price per pax: $" + String.format("%.02f", price);
    }
}
