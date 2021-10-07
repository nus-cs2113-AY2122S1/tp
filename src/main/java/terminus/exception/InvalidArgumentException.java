package terminus.exception;

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
