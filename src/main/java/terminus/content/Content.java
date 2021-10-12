package terminus.content;

public class Content {

    protected String name;
    protected String data;
    public static final String TYPE = "";

    private static final String DISPLAY_MESSAGE = "Name: %s\nContent:\n%s\n";

    public Content(String name) {
        this.name = name;
    }

    /**
     * Initializes the Content object.
     *
     * @param name the name attribute of Content
     * @param data the data attribute of Content
     */
    public Content(String name, String data) {
        this.name = name;
        this.data = data;
    }

    /**
     * Returns the name attribute of the Content object.
     *
     * @return A string containing the name attribute of the Content object
     */
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

    /**
     * Returns all the attributes of the Content object.
     *
     * @return A string containing all the attributes of the Content object
     */
    public String getDisplayInfo() {
        return String.format(DISPLAY_MESSAGE, name, data);
    }

    public String getViewDescription() {
        return this.name;
    }
}
