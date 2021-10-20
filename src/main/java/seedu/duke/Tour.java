package seedu.duke;

public class Tour {
    private final String code;
    private final String name;
    private final String price;

    public Tour(String[] values) {
        code = values[0];
        name = values[1];
        price = values[2];
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public String getPrice(){
        return price;
    }
}
