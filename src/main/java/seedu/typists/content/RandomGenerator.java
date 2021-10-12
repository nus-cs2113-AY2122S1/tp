package seedu.typists.content;

import java.util.Random;

/**
 * Generate random string of custom length
 */
public class RandomGenerator {
    public String randomString(int length) {
        Random isCaps = new Random();
        Random rand = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int random = 0;
            if (isCaps.nextBoolean()) {
                random = rand.nextInt(26) + 65;
            }
            else {
                random = rand.nextInt(26) + 97;
            }
            s.append((char) random);
        }
        return s.toString();
    }
}
