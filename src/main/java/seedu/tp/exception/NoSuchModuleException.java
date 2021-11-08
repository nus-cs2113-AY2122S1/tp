package seedu.tp.exception;

//@author SuibianP
/*
 * Indicating that there is no such requested module class combination in NUSMods
 */
public class NoSuchModuleException extends Throwable {
    public static final String MESSAGE = "There is no such module-class combination in NUSMods.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
