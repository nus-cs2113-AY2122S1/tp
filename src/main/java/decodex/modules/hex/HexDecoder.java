package decodex.modules.hex;

import decodex.data.Data;
import decodex.data.exception.ModuleException;
import decodex.modules.Module;
import decodex.ui.messages.ErrorMessages;

// @@author SeenFang

/**
 * The HexDecoder class converts a hexadecimal string back into its raw value.
 */
public class HexDecoder extends Module {

    public static final String MODULE_NAME = "hexdecode";
    public static final String MODULE_DESCRIPTION = "Converts a hexadecimal string back into its raw value.";

    public static final int HEXADECIMAL_RADIX = 16;
    public static final String REGEX_SPLIT_EVERY_2_CHARS = "(?<=\\G..)";

    public HexDecoder() {
        super(MODULE_NAME, MODULE_DESCRIPTION);
    }

    /**
     * Decodes the given hexadecimal string back into its raw value.
     *
     * @param data Data object to be decoded.
     * @return new Data object representing the decoded input.
     * @throws ModuleException If the Data is not in hexadecimal format.
     */
    @Override
    public Data run(Data data) throws ModuleException {
        String inputString = data.toString().toLowerCase();

        if (!isValidHex(inputString)) {
            throw new ModuleException(ErrorMessages.HEX_DECODING_FAILED_MESSAGE);
        }

        StringBuilder decodedStringBuilder = new StringBuilder();
        String[] hexSplit = inputString.split(REGEX_SPLIT_EVERY_2_CHARS);

        for (String hexChar : hexSplit) {
            char parsedChar = (char) Integer.parseInt(hexChar, HEXADECIMAL_RADIX);
            decodedStringBuilder.append(parsedChar);
        }

        String decodedString = decodedStringBuilder.toString();

        return new Data(decodedString);
    }

    /**
     * Checks if the provided hexadecimal string is valid.
     *
     * @param hexString Hexadecimal string to be checked.
     * @return Boolean if hexadecimal string is valid.
     */
    private boolean isValidHex(String hexString) {
        boolean isEven = hexString.length() % 2 == 0;
        boolean isHexCharacters = isHexCharacters(hexString);

        return isEven && isHexCharacters;
    }

    /**
     * Checks if the provided hexadecimal string is of valid hexadecimal characters.
     *
     * @param hexString Hexadecimal string to be checked.
     * @return Boolean is hexadecimal string consists of valid characters.
     */
    private boolean isHexCharacters(String hexString) {
        // Check if String is valid hexadecimal String.
        for (int i = 1; i < hexString.length(); i++) {
            if (Character.digit(hexString.charAt(i), 16) == -1) {
                return false;
            }
        }

        return true;
    }
}
