package expiryeliminator.parser.prefix;

/**
 * Represents a prefix that should only appear once.
 */
public class SingleArgPrefix extends Prefix {
    /**
     * Initialises prefix.
     *
     * @param prefix Prefix.
     */
    public SingleArgPrefix(String prefix) {
        this.prefix = prefix;
    }
}
