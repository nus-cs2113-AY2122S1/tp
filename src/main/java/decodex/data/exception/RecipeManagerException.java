package decodex.data.exception;

/**
 * This exception is raised when an error during recipe manager execution occurs.
 */
public class RecipeManagerException extends Exception {

    public RecipeManagerException(String message) {
        super(message);
    }
}
