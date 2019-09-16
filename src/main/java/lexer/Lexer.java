package lexer;

import java.util.LinkedList;

public class Lexer {
    private LinkedList<Token> tokens;
    private String line;

    public Lexer() {
        tokens = new LinkedList<Token>();
    }

    public Lexer(String str) {
        tokens = new LinkedList<>();
        line = str;
    }

    public Token getNextToken() {
        return null;
    };

    public LinkedList<Token> getTokens() {
        return this.tokens;
    }

    /**
     *  This method is meant to decide if a pattern is an ID token or not.
     *  pattern used is [A-Za-z][A-Za-z0-9_]*
     *  first character:
     *    upper or lower case alphabet
     *  All other characters:
     *    upper or lower case alphabet
     *    numbers
     *    underscore
     *
     * @param pattern
     * @return whether or not the pattern is an ID
     */
    public static boolean isId(String pattern) {
        char firstChar = pattern.charAt(0);
        if (firstChar >= '0' && firstChar <= '9') {
            return false;
        }

        if (firstChar == '_') {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            boolean illegalChar = false;
            char currentChar = pattern.charAt(i);

            if (!(isLowerCase(currentChar) || isUpperCase(currentChar) || isDigit(currentChar) || currentChar == '_')) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNum(String pattern) {
        return false;
    }

    public static boolean isReal(String pattern) {
        return  false;
    }


    // Private helper methods
    /**
     * This method takes a character and returns true if it is a lower case alphabet character
     *
     * @param c
     * @return
     */
    private static boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

    /**
     * This methid takes a character and returns true if it is an upper case alphabet character
     *
     * @param c
     * @return
     */
    private static boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    /**
     * This method takes a character and returns true if it is a number character.
     *
     * @param c
     * @return
     */
    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
