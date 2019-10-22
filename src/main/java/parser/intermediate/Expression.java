package parser.intermediate;

import lexer.Token;

public class Expression extends Node {
    private Token tok;

    public Expression(Token tok) {
        super("Token " + tok.getName());
        this.tok = tok;
    }
}
