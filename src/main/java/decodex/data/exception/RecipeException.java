package decodex.data.exception;

/**
 * This exception is raised when an error during recipe execution occurs.
 */
public class RecipeException extends Exception {

    public RecipeException(String message) {
        super(message);
    }
}
