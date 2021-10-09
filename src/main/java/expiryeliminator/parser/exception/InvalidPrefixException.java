package expiryeliminator.parser.exception;

public class InvalidPrefixException extends Exception {
    final String prefix;

    public InvalidPrefixException(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
