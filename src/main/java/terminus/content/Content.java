package terminus.content;

public class Content {

    private String name;
    private String data;
    public static final String TYPE = "";

    private static final String DISPLAY_MESSAGE = "Name: %s\nContent: %s\n";

    public Content(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDisplayInfo() {
        return String.format(DISPLAY_MESSAGE, name, data);
    }

}
