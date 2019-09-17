package lexer;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lexer {
    private LinkedList<Token> tokens;
    private ArrayList<String> lines;

    public Lexer() {
        tokens = new LinkedList<>();
        lines = new ArrayList<>();
    }

    public Lexer(String line) {
        tokens = new LinkedList<>();
        lines = new ArrayList<>();
        lines.add(line);
    }

    public Lexer(ArrayList<String> lines) {
        tokens = new LinkedList<>();
        this.lines = lines;
    }

    /**
     * This method returns the next token in the LinkedList. It should keep track of an internal index.
     * It should return EOF for when called and the index is on the last token.
     *
     * @return single token at the next index
     */
    public Token getNextToken() {
        return null;
    };

    /**
     * This method returns all of the tokens in form of an LinkedList.
     *
     * @return All tokens
     */
    public LinkedList<Token> getTokens() {
        return this.tokens;
    }

    public void tokenize() {
        if (lines == null) {
            return;
        }
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
            char currentChar = pattern.charAt(i);

            if (!(isAlphabet(currentChar) || isDigit(currentChar) || currentChar == '_')) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNum(String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            if (!isDigit(pattern.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isReal(String pattern) {
        return  false;
    }


    // Private helper methods

    /**
     * This method takes a character and returns true if it is an alphabet character
     *
     * @param c
     * @return
     */
    private static boolean isAlphabet(char c) {
        return isLowerCase(c) || isUpperCase(c);
    }

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
     * This method takes a character and returns true if it is an upper case alphabet character
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
