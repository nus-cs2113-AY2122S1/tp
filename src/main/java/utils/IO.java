package utils;

import utils.message.Strings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * IO utilities.
 */
public class IO {
    private IO() {
        // Disallow instantiation
    }

    /**
     * Reads a non-empty string from the {@code Scanner} provided.
     *
     * @param in     the {@code Scanner} from which input is read
     * @param prompt the prompt to show
     * @return the string read
     * @throws NoSuchElementException if input is exhausted
     * @throws IllegalStateException  if this scanner is closed
     */
    public static String readString(Scanner in, String prompt) throws NoSuchElementException, IllegalStateException {
        String result = null;
        while (true) {
            System.out.print(prompt);
            result = in.nextLine().trim();
            if (!result.isBlank()) {
                break;
            }
            Errors.print(Strings.ERR_EMPTY_STRING, false);
        }
        return result;
    }

    /**
     * Reads a string matching one of the available pre-defined values from the
     * {@code Scanner} provided. Matching is case insensitive.
     *
     * @param in     the {@code Scanner} from which input is read
     * @param prompt the prompt to show
     * @return the string read
     * @throws NoSuchElementException if input is exhausted, or the list of
     *                                available strings is empty
     * @throws IllegalStateException  if this scanner is closed
     */
    public static String readStringFromAvailable(Scanner in, String prompt, String[] available)
            throws NoSuchElementException, IllegalStateException {
        String result = null;
        boolean matched = false;
        while (!matched) {
            result = readString(in, prompt);
            for (String str : available) {
                if (result.toLowerCase().equals(str.toLowerCase())) {
                    matched = true;
                    break;
                }
            }
            Errors.print(Strings.ERR_UNKNOWN_INPUT, false);
        }
        return result;
    }

    /**
     * Reads a double from the {@code Scanner} provided.
     *
     * @param in     the {@code Scanner} from which input is read
     * @param prompt the prompt to show
     * @return the double read
     * @throws NoSuchElementException if input is exhausted
     * @throws IllegalStateException  if this scanner is closed
     */
    public static double readDouble(Scanner in, String prompt) throws NoSuchElementException, IllegalStateException {
        double result = 0;
        while (true) {
            try {
                System.out.print(prompt);
                result = in.nextDouble();
                in.nextLine();
                break;
            } catch (InputMismatchException ime) {
                in.nextLine();
                Errors.print(Strings.ERR_INPUT_MISMATCH_DOUBLE_EXPECTED, false);
            }
        }
        return result;
    }

    /**
     * Reads an integer from the {@code Scanner} provided.
     *
     * @param in     the {@code Scanner} from which input is read
     * @param prompt the prompt to show
     * @return the integer read
     * @throws NoSuchElementException if input is exhausted
     * @throws IllegalStateException  if this scanner is closed
     */
    public static int readInt(Scanner in, String prompt) throws NoSuchElementException, IllegalStateException {
        int result = 0;
        while (true) {
            try {
                System.out.print(prompt);
                result = in.nextInt();
                in.nextLine();
                break;
            } catch (InputMismatchException ime) {
                in.nextLine();
                Errors.print(Strings.ERR_INPUT_MISMATCH_INT_EXPECTED, false);
            }
        }
        return result;
    }

    /**
     * Reads a boolean from the {@code Scanner} provided.
     *
     * @param in     the {@code Scanner} from which input is read
     * @param prompt the prompt to show
     * @return the boolean read
     * @throws NoSuchElementException if input is exhausted
     * @throws IllegalStateException  if this scanner is closed
     */
    public static boolean readBoolean(Scanner in, String prompt) throws NoSuchElementException, IllegalStateException {
        String input = null;
        while (true) {
            input = readString(in, prompt);
            if (input.toLowerCase().equals("y") || input.toLowerCase().equals("n")) {
                break;
            }
            Errors.print(Strings.ERR_BOOLEAN_INPUT, false);
        }
        return input.toLowerCase().equals("y");
    }

    /**
     * Reads a date from the {@code Scanner} provided.
     *
     * @param in      the {@code Scanner} from which input is read
     * @param prompt  the prompt to show
     * @param pattern the pattern of the date string
     * @return the date read, or {@code null} if the pattern is invalid
     * @throws NoSuchElementException if input is exhausted
     * @throws IllegalStateException  if this scanner is closed
     */
    public static Date readDate(Scanner in, String prompt, String pattern)
            throws NoSuchElementException, IllegalStateException {
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat(pattern);
        } catch (IllegalArgumentException e) {
            return null;
        }
        while (true) {
            String input = readString(in, prompt);
            try {
                return sdf.parse(input);
            } catch (ParseException e) {
                Errors.print(Strings.ERR_DATE_INPUT, false);
            }
        }
    }

    /**
     * Reads a time string from the {@code Scanner} provided.
     *
     * @param in      the {@code Scanner} from which input is read
     * @param prompt  the prompt to show
     * @param pattern the pattern of the time string
     * @return the time string read, or {@code null} if the pattern is invalid
     * @throws NoSuchElementException if input is exhausted
     * @throws IllegalStateException  if this scanner is closed
     */
    public static String readTimeString(Scanner in, String prompt, String pattern)
            throws NoSuchElementException, IllegalStateException {
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat(pattern);
        } catch (IllegalArgumentException e) {
            return null;
        }
        while (true) {
            String input = readString(in, prompt);
            try {
                sdf.parse(input);
                return input;
            } catch (ParseException e) {
                Errors.print(Strings.ERR_TIME_INPUT, false);
            }
        }
    }

    /**
     * Reads a date string from the {@code Scanner} provided.
     *
     * @param in      the {@code Scanner} from which input is read
     * @param prompt  the prompt to show
     * @param pattern the pattern of the date string
     * @return the date string read, or {@code null} if the pattern is invalid
     * @throws NoSuchElementException if input is exhausted
     * @throws IllegalStateException  if this scanner is closed
     */
    public static String readDateString(Scanner in, String prompt, String pattern)
        throws NoSuchElementException, IllegalStateException {
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat(pattern);
        } catch (IllegalArgumentException e) {
            return null;
        }
        while (true) {
            String input = readString(in, prompt);
            try {
                sdf.parse(input);
                return input;
            } catch (ParseException e) {
                Errors.print(Strings.ERR_DATE_INPUT, false);
            }
        }
    }
}

