package util;

import lexer.Token;

public class Type {
    public static Token typeCast(Token lhs, Token rhs) {
        // Nothing to cast since it is not numeric
        if (!lhs.isNumeric() || !rhs.isNumeric()) {
            return null;
        }

        // cast int to float
        if (lhs.isFloat() || rhs.isFloat()) {
            return new Token("float");
        }

        // cast to int
        if (lhs.isInt() || rhs.isInt()) {
            return new Token("int");
        }

        // is bool
        return new Token("bool");
    }
}
