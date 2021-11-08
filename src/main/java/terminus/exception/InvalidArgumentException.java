package terminus.exception;

/**
 * Invalid Argument Exception class which handles exception when argument from user command is invalid.
 */
public class InvalidArgumentException extends Exception {

    private final String format;
    
    public InvalidArgumentException(String message) {
        this(null, message);
    }
    
    public InvalidArgumentException(String format, String message) {
        super(message);
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
