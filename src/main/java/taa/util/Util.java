package taa.util;

public class Util {
    public static boolean isInteger(String string) {
        boolean isInt;
        try {
            int value = Integer.parseInt(string);
            isInt = true;
        } catch (NumberFormatException e) {
            isInt = false;
        }

        return isInt;
    }
}
