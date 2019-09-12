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
        tokens.add(new Token("abcd", "ID"));
        tokens.add(new Token(";"));
    }

//    public Lexer(Lexer rhs) {
//        tokens = (LinkedList<Token>)rhs.getTokens();
//    }

    public Token getNextToken() {
        return null;
    };

    public LinkedList<Token> getTokens() {
        return this.tokens;
    }

    public static boolean isValid(String pattern) {
        return false;
    }

    public static boolean isId(String pattern) {
        return false;
    }
}
