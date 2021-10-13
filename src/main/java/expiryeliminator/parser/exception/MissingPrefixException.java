package expiryeliminator.parser.exception;


import expiryeliminator.parser.prefix.Prefix;

/**
 * Signals an error caused by a missing prefix where it should have been present.
 */
public class MissingPrefixException extends Exception {
    private final Prefix prefix;

    /**
     * Initialises exception and stores the missing prefix.
     *
     * @param prefix Missing prefix.
     */
    public MissingPrefixException(Prefix prefix) {
        this.prefix = prefix;
    }

    /**
     * Returns the missing prefix.
     *
     * @return Missing prefix.
     */
    public Prefix getPrefix() {
        return prefix;
    }
}
