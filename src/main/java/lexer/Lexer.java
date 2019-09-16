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

    public static boolean isId(String pattern) {
        return false;
    }

    public static boolean isNum(String pattern) {
        return false;
    }

    public static boolean isReal(String pattern) {
        return  false;
    }
}
