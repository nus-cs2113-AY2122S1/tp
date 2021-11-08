package exceptions;

public class DukeFileNotFoundException extends Exception {

    public DukeFileNotFoundException() {
        super("File Not Found");
    }
}