package expiryeliminator.parser.prefix;

/**
 * Represents a prefix that is optional.
 */
public class OptionalArgPrefix extends SingleArgPrefix {
    /**
     * Initialises optional arg prefix.
     *
     * @param singleArgPrefix Prefix that should be allowed to be optional.
     */
    public OptionalArgPrefix(SingleArgPrefix singleArgPrefix) {
        super(singleArgPrefix.getPrefix());
    }
}
