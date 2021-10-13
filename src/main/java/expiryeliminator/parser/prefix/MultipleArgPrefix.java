package expiryeliminator.parser.prefix;

/**
 * Represents a prefix that can appear more than once.
 */
public class MultipleArgPrefix extends Prefix {
    /**
     * Initialises multiple arg prefix.
     *
     * @param singleArgPrefix Prefix that should be allowed to appear more than once.
     */
    public MultipleArgPrefix(SingleArgPrefix singleArgPrefix) {
        prefix = singleArgPrefix.getPrefix();
    }
}
