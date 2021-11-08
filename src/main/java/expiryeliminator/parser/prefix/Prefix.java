package expiryeliminator.parser.prefix;

/**
 * Represents a prefix (e.g. "i/").
 */
public abstract class Prefix {
    protected String prefix;

    /**
     * Returns prefix.
     *
     * @return Prefix.
     */
    public String getPrefix() {
        return prefix;
    }
}
