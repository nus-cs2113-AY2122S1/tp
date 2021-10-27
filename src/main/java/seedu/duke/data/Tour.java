package seedu.duke.data;

public class Tour {
    private final String code;
    private final String name;
    private final float price;

    public Tour(String[] values) {
        code = values[0];
        name = values[1];
        price = Float.parseFloat(values[2]);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Name: " + name + System.lineSeparator()
                + "Code: " + code + System.lineSeparator()
                + "Price per pax: $" + String.format("%.02f", price);
    }
}
