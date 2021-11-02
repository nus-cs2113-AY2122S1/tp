package taa.exception;

import java.util.ArrayList;

public class DuplicatedArgumentException extends TaaException {
    private static final String MESSAGE_FORMAT_DUPLICATED_KEYS_FOUND = "Duplicated keys found: %s";

    public DuplicatedArgumentException(ArrayList<String> duplicatedKeys) {
        super(generateMessage(duplicatedKeys));
    }

    private static String generateMessage(ArrayList<String> duplicatedKeys) {
        String duplicatedKeysStr = "";
        for (int i = 0; i < duplicatedKeys.size(); i += 1) {
            String key = duplicatedKeys.get(i);

            if (i > 0) {
                duplicatedKeysStr += ", ";
            }
            duplicatedKeysStr += key;
        }

        return String.format(MESSAGE_FORMAT_DUPLICATED_KEYS_FOUND, duplicatedKeysStr);
    }
}
