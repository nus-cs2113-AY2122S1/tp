package taa.exception;

public class FileCreationException extends TaaException {
    private static final String MESSAGE_FORMAT_FAIL_CREATE_FILE = "Fail to create file - %s";

    public FileCreationException(String filename) {
        super(String.format(MESSAGE_FORMAT_FAIL_CREATE_FILE, filename));
    }
}
