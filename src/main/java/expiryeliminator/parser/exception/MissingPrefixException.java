package expiryeliminator.parser.exception;


import expiryeliminator.parser.Prefix;

public class MissingPrefixException extends Exception {
    final Prefix prefix;

    public MissingPrefixException(Prefix prefix) {
        this.prefix = prefix;
    }

    public Prefix getPrefix() {
        return prefix;
    }
}
