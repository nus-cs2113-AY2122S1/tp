package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A string parser utility that implements some core functionalities of the
 * built-in {@code StringTokenizer}, but also with some additional
 * application-specific ones.
 *
 * @see java.util.StringTokenizer
 */
public class StringParser {
    /**
     * The type of token.
     */
    private enum TokenType {
        /**
         * Double-quoted token.
         */
        DOUBLE_QUOTED,

        /**
         * Single quoted token.
         */
        SINGLE_QUOTED,

        /**
         * Unquoted token.
         */
        UNQUOTED;
    }

    /**
     * The RegEx pattern that matches any of the following, seperated by any number
     * of whitespace characters:
     * <ul>
     * <li>"Double quoted tokens" with escape {@code \"};</li>
     * <li>'Single quoted tokens' with escape {@code \'};</li>
     * <li>Unquoted single tokens.</li>
     * </ul>
     * The pattern is
     * {@code ("[^"\\]*(?:\\.[^"\\]*)*")|('[^'\\]*(?:\\.[^'\\]*)*')|(\S+)}.
     */
    private static final String REGEX_DOUBLE = "\"[^\"\\\\]*(?:\\\\.[^\"\\\\]*)*\"";
    private static final String REGEX_SINGLE = "'[^'\\\\]*(?:\\\\.[^'\\\\]*)*'";
    private static final String REGEX_UNQUOTED = "\\S+";
    private static final String REGEX = String.format("(%s)|(%s)|(%s)", REGEX_DOUBLE,
            REGEX_SINGLE, REGEX_UNQUOTED);

    //The string that needs to be processed by the parser.

    private final String string;

    // The list of tokens parsed by the parser.
    private final List<Token> tokens;

    // Pointer to the next unread token.
    private int next = 0;

    /**
     * A token consumed by the parser.
     */
    private class Token {
        /**
         * The type of this token.
         */
        TokenType tokenType;

        /**
         * The value of this token.
         */
        String token;

        /**
         * Initialises a token.
         *
         * @param tokenType the type of the token
         * @param token     the value of the token
         */
        Token(TokenType tokenType, String token) {
            this.tokenType = tokenType;
            this.token = token;
        }
    }
    
    /**
     * Initialises a parser with the string given.
     *
     * @param string the string for the parser to consume
     */
    public StringParser(String string) {
        this.string = string;
        this.tokens = new ArrayList<>();

        Matcher matcher = Pattern.compile(REGEX).matcher(string);
        while (matcher.find()) {
            String token = matcher.group();
            TokenType tokenType;
            if (matcher.group(1) != null) {
                token = token.substring(1, token.length() - 1);
                tokenType = TokenType.DOUBLE_QUOTED;
            } else if (matcher.group(2) != null) {
                token = token.substring(1, token.length() - 1);
                tokenType = TokenType.SINGLE_QUOTED;
            } else {
                tokenType = TokenType.UNQUOTED;
            }
            if (!token.isBlank()) {
                tokens.add(new Token(tokenType, token));
            }
        }
    }

    /**
     * Finds if this parser has more unread tokens.
     *
     * @return {@code true} if there are more tokens to be read; {@code false} otherwise
     * @see java.util.StringTokenizer#hasMoreTokens()
     */
    public boolean hasMoreTokens() {
        return next < tokens.size();
    }

    /**
     * Gets the next unread token from this parser.
     *
     * @return the next token string, if exists; {@code null} otherwise
     * @see java.util.StringTokenizer#nextToken()
     */
    public String nextToken() {
        if (hasMoreTokens()) {
            return tokens.get(next++).token;
        }
        return null;
    }

    /**
     * Calculates the number of times that this parser's {@code nextToken} method
     * can be called before it returns {@code null}. The current position is not
     * advanced.
     *
     * @return the number of tokens remaining in the string using the current delimiter set.
     * @see java.util.StringTokenizer#countTokens()
     */
    public int countTokens() {
        return tokens.size();
    }

    /**
     * Gets the underlying string of this parser.
     *
     * @return the underlying string
     */
    public String getString() {
        return string;
    }

    /**
     * Gets the unread tokens, combined as a string.
     *
     * @return the unread tokens, combined as a string
     */
    public String getRemaining() {
        List<Token> remainingTokens = tokens.subList(next, tokens.size());
        List<String> remainingStrings = new ArrayList<>();
        for (Token token : remainingTokens) {
            String quotes;
            switch (token.tokenType) {
            case DOUBLE_QUOTED: {
                quotes = "\"";
                break;
            }
            case SINGLE_QUOTED: {
                quotes = "'";
                break;
            }
            case UNQUOTED:
            default: {
                quotes = "";
                break;
            }
            }
            remainingStrings.add(String.format("%s%s%s", quotes, token.token, quotes));
        }
        return String.join(" ", remainingStrings);
    }
}
