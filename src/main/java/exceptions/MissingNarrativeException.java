package exceptions;

import java.io.FileNotFoundException;

public class MissingNarrativeException extends FileNotFoundException {
    public MissingNarrativeException(String message) {
        super(message);
    }
}
