package seedu.duke.items;

public abstract class Item {

    protected String type;
    protected String title;
    protected String description;

    public Item(String type, String title, String description) {
        this.type = type;
        this.title = title;
        this.description = description;
    }

    public String getItemType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getDateValue();
}
