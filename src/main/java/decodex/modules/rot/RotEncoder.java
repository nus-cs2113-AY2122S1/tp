package decodex.modules.rot;

import decodex.data.Data;
import decodex.modules.Module;

/**
 * The RotEncoder class handles the rotation of the alphabets in the input string.
 */
public class RotEncoder extends Module {
    
    public static final String MODULE_NAME = "rotencode";
    public static final String MODULE_DESCRIPTION = "Rotates alphabetical characters by a specified integer offset.";

    // Constants for performing rotational cipher
    private static final char LOWERCASE_A = 'a';
    private static final char UPPERCASE_A = 'A';
    private static final int ALPHABETS_COUNT = 26;

    private final int rotateOffset;

    public RotEncoder(int rotateOffset) {
        super(MODULE_NAME, MODULE_DESCRIPTION);
        this.rotateOffset = rotateOffset;
    }

    /**
     * Applies the rotational cipher to the given Data object.
     *
     * @param data The given Data object.
     * @return A Data object with its alphabetical characters rotated.
     */
    @Override
    public Data run(Data data) {
        StringBuilder encodedStringBuilder = new StringBuilder();

        for (byte b : data.getRawBytes()) {
            char letter = (char) b;
            char updatedLetter = rotateAlphabet(letter, rotateOffset);
            encodedStringBuilder.append(updatedLetter);
        }

        String encodedString = encodedStringBuilder.toString();
        return new Data(encodedString);
    }

    /**
     * Rotates the given alphabet.
     *
     * @param c            The given  alphabet.
     * @param rotateOffset Number of positions to rotate the alphabet by.
     * @return The updated alphabet.
     */
    private char rotateAlphabet(char c, int rotateOffset) {
        char updatedChar = c;
        if (Character.isLowerCase(c)) {
            updatedChar = rotateLowercaseAlphabet(c, rotateOffset);
        } else if (Character.isUpperCase(c)) {
            updatedChar = rotateUppercaseAlphabet(c, rotateOffset);
        }
        return updatedChar;
    }

    /**
     * Rotates the given lowercase alphabet.
     *
     * @param c            The given lowercase alphabet.
     * @param rotateOffset Number of positions to rotate the alphabet by.
     * @return The updated alphabet.
     */
    private char rotateLowercaseAlphabet(char c, int rotateOffset) {
        int originalAlphabetIndex = c - LOWERCASE_A;
        int roundedRotateOffset = rotateOffset % ALPHABETS_COUNT;
        int updatedAlphabetIndex = Math.floorMod(originalAlphabetIndex + roundedRotateOffset, ALPHABETS_COUNT);
        return (char) (LOWERCASE_A + updatedAlphabetIndex);
    }

    /**
     * Rotates the given uppercase alphabet.
     *
     * @param c            The given uppercase alphabet.
     * @param rotateOffset Number of positions to rotate the alphabet by.
     * @return The updated alphabet.
     */
    private char rotateUppercaseAlphabet(char c, int rotateOffset) {
        int originalAlphabetIndex = c - UPPERCASE_A;
        int roundedRotateOffset = rotateOffset % ALPHABETS_COUNT;
        int updatedAlphabetIndex = Math.floorMod(originalAlphabetIndex + roundedRotateOffset, ALPHABETS_COUNT);
        return (char) (UPPERCASE_A + updatedAlphabetIndex);
    }

    /**
     * Returns a String formatted RotEncoder.
     *
     * @return The string formatted RotEncoder.
     */
    @Override
    public String toString() {
        return super.toString() + " " + rotateOffset;
    }
}
