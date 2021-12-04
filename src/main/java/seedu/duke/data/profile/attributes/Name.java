package seedu.duke.data.profile.attributes;

import seedu.duke.data.Verifiable;

/**
 * Name attribute of profile.
 */
public class Name implements Verifiable {

    protected String name;
    private static final String EMPTY_STRING = "";

    public Name() {

    }

    /**
     * Constructs a name object.
     *
     * @param name name input of user
     */
    public Name(String name) {
        setName(name.trim());
    }

    /**
     * Retrieves the name from Name object.
     *
     * @return name of Name object
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the Name Object.
     *
     * @param name name input by the user
     */
    public void setName(String name) {
        this.name = name.trim();
    }

    @Override
    public boolean isValid() {
        if (name == null || name.trim().equals(EMPTY_STRING)) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '|' || name.charAt(i) == '/') {
                return false;
            }
        }
        return true;
    }


}
