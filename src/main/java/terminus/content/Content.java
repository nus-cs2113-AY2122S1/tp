package terminus.content;

/**
 * Content class to represent any data related object.
 */
public class Content {

    protected String name;
    protected String data;
    public static final String TYPE = "";

    private static final String DISPLAY_MESSAGE = "Name: %s\nContent:\n%s\n";

    public Content(String name) {
        this.name = name;
    }

    /**
     * Initializes a Content object.
     *
     * @param name The name attribute of the Content.
     * @param data The data attribute of the Content.
     */
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

    /**
     * Returns all the attributes of the Content object formatted by its display message.
     *
     * @return A string containing all the attributes of the Content object.
     */
    public String getDisplayInfo() {
        return String.format(DISPLAY_MESSAGE, name, data);
    }

    /**
     * Returns attributes of the Content object to be listed by the view command.
     *
     * @return A string containing attributes of the Content object based on the view command.
     */
    public String getViewDescription() {
        return this.name;
    }
}
