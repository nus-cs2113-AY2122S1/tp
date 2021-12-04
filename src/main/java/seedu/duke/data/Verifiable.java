package seedu.duke.data;

/**
 * Interface that implements verifiability to various objects.
 */
public interface Verifiable {
    /**
     * Implements a simple check that verifies if the attribute tied to Verifiable is valid.
     *
     * @return True if valid, false if invalid as specified by each attribute
     */
    boolean isValid();
}
