package exceptions;

import java.io.FileNotFoundException;

public class MissingSceneFileException extends FileNotFoundException {
    public MissingSceneFileException(String message) {
        super(message);
    }
}
