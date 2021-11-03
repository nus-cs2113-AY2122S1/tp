package seedu.duke.data;

public class Tour {
    private final String id;
    private final String name;
    private final float price;

    public Tour(String[] values) {
        id = values[0];
        name = values[1];
        price = Float.parseFloat(values[2]);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n"
                + "Id: " + id + "\n"
                + "Price per pax: $" + String.format("%.02f", price);
    }
}
