package seedu.exceptions;

public class ProfileException extends UniModsException{

    public enum CAUSE {
        NO_PROFILE_FOUND("No local profile can be found,"),
        PROFILE_CORRUPTED("Saved profile was corrupted,");

        private String errorMessage;
        private CAUSE(String message) {
            this.errorMessage = message;
        }

        @Override
        public String toString() {
            return errorMessage;
        }
    }

    public ProfileException(String cause) {
        super(cause);
    }

    public ProfileException(CAUSE cause) {
        this(cause.toString());
    }
}
