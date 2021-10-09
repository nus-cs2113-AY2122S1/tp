package seedu.duke.data.item;

public class Item {
    private String Title;
    private String ID;
    private String Status;

    public Item(String title, String ID, String status) {
        Title = title;
        this.ID = ID;
        Status = status;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
