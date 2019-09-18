package lexer;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lexer {
    private LinkedList<Token> tokens;
    private ArrayList<String> lines;
    private int index;

    private static final char[] noSpaceNeeded = {
        ' ', ';', '=', '<',
        '>', '(', ')', '{',
        '}', '+', '-', '*',
        '/', '&', '!', '|'
    };

    public Lexer() {
        tokens = new LinkedList<>();
        lines = new ArrayList<>();
        index = 0;
    }

    public Lexer(String line) {
        tokens = new LinkedList<>();
        lines = new ArrayList<>();
        lines.add(line);
        tokenize();
    }

    public Lexer(ArrayList<String> lines) {
        tokens = new LinkedList<>();
        this.lines = lines;
        tokenize();
    }

    /**
     * This method returns the next token in the LinkedList. It should keep track of an internal index.
     * It should return EOF for when called and the index is on the last token.
     *
     * @return single token at the next index
     */
    public Token getNextToken() {
        if (index < tokens.size()) {
            index++;
            return tokens.get(index - 1);
        }

        return TokenType.getTokenFromPattern("\26");
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

        for (String line : lines) {
            tokenizeLine(line);
        }
    }

    public void tokenizeLine(String line) {
        line = line.trim();
        String temp = "";

        for (int i = 0; i < line.length(); i++) {
            temp += line.charAt(i);

            if (getToken(temp) == null) {
                char lastChar = temp.charAt(temp.length() - 1);
                if (temp.length() != 1) {
                    temp = temp.substring(0, temp.length() - 1);
                    boolean flag = false;

                    boolean currentCharDoesNotNeedSpaces = false;
                    if (temp.length() - 1 >= 0) {
                        for (int j = 0; j < noSpaceNeeded.length; j++) {
                            if (temp.charAt(temp.length() - 1) == noSpaceNeeded[j]) {
                                currentCharDoesNotNeedSpaces = true;
                                break;
                            }
                        }
                    }

                    if (!currentCharDoesNotNeedSpaces) {
                        for (int j = 0; j < noSpaceNeeded.length; j++) {
                            if (lastChar == noSpaceNeeded[j]) {
                                flag = true;
                                tokens.add(getToken(temp));
                                line = discardFoundTokenAndSpace(temp, line);
                                temp = "";
                                i = -1;
                                break;
                            }
                        }
                    } else {
                        tokens.add(getToken(temp));
                        line = discardFoundTokenAndSpace(temp, line);
                        temp = "";
                        i = -1;
                        flag = true;
                    }

                    if (!flag) {
                        tokens.add(null);
                    }
                }
            } else {
                if (temp.length() == line.length())
                    tokens.add(getToken(temp));
            }
        }
    }

    private String discardFoundTokenAndSpace(String temp, String line) {
        String newLine = "";

        for (int i = temp.length(); i < line.length(); i++) {
            if (newLine.length() == 0 && line.charAt(i) == ' ') {
                continue;
            }
            newLine += line.charAt(i);
        }

        return newLine;
    }

    public Token getToken(String pattern) {
        if (TokenType.getTokenFromPattern(pattern) != null) {
            return TokenType.getTokenFromPattern(pattern);
        } else if (isId(pattern)) {
            return new Token(pattern, "ID");
        } else if (isReal(pattern)) {
            return new Token(pattern, "REAL");
        } else if (isNum(pattern)) {
            return new Token(pattern, "NUM");
        }

        return null;
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
        if (pattern.charAt(0) == '.' || pattern.charAt(pattern.length() - 1) == '.') {
            return false;
        }

        boolean hasDec = false;
        for (int i = 0; i < pattern.length(); i++) {
            if (!isDigit(pattern.charAt(i))) {
                if (pattern.charAt(i) == '.' && !hasDec) {
                    hasDec = true;
                } else {
                    return false;
                }
            }
        }

        return hasDec;
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
