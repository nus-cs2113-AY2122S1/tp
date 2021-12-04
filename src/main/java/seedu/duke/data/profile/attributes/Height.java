package seedu.duke.data.profile.attributes;

import seedu.duke.data.Verifiable;

/**
 * Height attribute of Profile.
 */
public class Height implements Verifiable {

    public static final int LOWER_HEIGHT_LIMIT = 1;
    public static final int UPPER_HEIGHT_LIMIT = 300;

    protected double height;

    public Height() {

    }

    /**
     * Constructs a height object.
     *
     * @param height height input by user.
     */
    public Height(double height) {
        setHeight(height);
    }

    /**
     * Retrieves the height of Height object.
     *
     * @return the height of Height object
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height for Height object.
     *
     * @param height height input by user
     */
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean isValid() {
        return height >= LOWER_HEIGHT_LIMIT && height <= UPPER_HEIGHT_LIMIT;
    }

}
