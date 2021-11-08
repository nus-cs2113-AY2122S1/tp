package expiryeliminator.parser.exception;

/**
 * Signals an error caused by an invalid prefix that was encountered.
 */
public class InvalidPrefixException extends Exception {
    private final String prefix;

    /**
     * Initialises exception and stores the invalid prefix.
     *
     * @param prefix Invalid prefix.
     */
    public InvalidPrefixException(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Returns the invalid prefix.
     *
     * @return Invalid prefix.
     */
    public String getPrefix() {
        return prefix;
    }
}
