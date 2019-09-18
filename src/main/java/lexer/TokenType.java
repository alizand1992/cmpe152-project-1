package lexer;

import java.util.HashMap;

public class TokenType {
    public static Token getTokenFromPattern(String pattern) {
        Token token = null;
        if (getTypes().containsKey(pattern)) {
            token = (Token)getTypes().get(pattern);
        }
        return token;
    }

    private static HashMap getTypes() {
        HashMap<String, Token> types = new HashMap<>();

        types.put("&&", new Token("&&", "AND"));
        types.put("int", new Token("int", "BASE_TYPE"));
        types.put("float", new Token("float", "BASE_TYPE"));
        types.put("bool", new Token("bool", "BASE_TYPE"));
        types.put("break", new Token("break", "BREAK"));
        types.put("do", new Token("do", "DO"));
        types.put("else", new Token("else", "ELSE"));
        types.put("==", new Token("==", "EQ"));
        types.put("false", new Token("false", "FALSE"));
        types.put("for", new Token("for", "FOR"));
        types.put(">=", new Token(">=", "GE"));
        types.put("id", new Token("id", "ID"));
        types.put("if", new Token("if", "IF"));
        types.put("<=", new Token("<=", "LE"));
        types.put("!=", new Token("!=", "NE"));
        types.put("num", new Token("num", "NUM"));
        types.put("||", new Token("||", "OR"));
        types.put("real", new Token("real", "REAL"));
        types.put("true", new Token("true", "TRUE"));
        types.put("while", new Token("while", "WHILE"));
        types.put(";", new Token(";", ";"));
        types.put("=", new Token("="));
        types.put("<", new Token("<"));
        types.put(">", new Token(">"));
        types.put("(", new Token("("));
        types.put(")", new Token(")"));
        types.put("{", new Token("{"));
        types.put("}", new Token("}"));
        types.put("+", new Token("+"));
        types.put("-", new Token("-"));
        types.put("*", new Token("*"));
        types.put("/", new Token("/"));
        types.put("\26", new Token("\26", "EOF"));

        return types;
    }
}
