package taa.exception;

//@@author leyondlee
import java.util.ArrayList;

public class DuplicatedArgumentException extends TaaException {
    private static final String MESSAGE_FORMAT_DUPLICATED_KEYS_FOUND = "Duplicated keys found: %s";

    public DuplicatedArgumentException(ArrayList<String> duplicatedKeys) {
        super(generateMessage(duplicatedKeys));
    }

    private static String generateMessage(ArrayList<String> duplicatedKeys) {
        StringBuilder duplicatedKeysStr = new StringBuilder();
        for (int i = 0; i < duplicatedKeys.size(); i += 1) {
            String key = duplicatedKeys.get(i);

            if (i > 0) {
                duplicatedKeysStr.append(", ");
            }
            duplicatedKeysStr.append(key);
        }

        return String.format(MESSAGE_FORMAT_DUPLICATED_KEYS_FOUND, duplicatedKeysStr.toString());
    }
}
