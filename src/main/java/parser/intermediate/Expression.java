package parser.intermediate;

import lexer.Token;

public class Expression extends Stmt {
    private Token tok;

    public Expression(Token tok) {
        super("Token " + tok.getName() + "    " + tok.getPattern());
        this.tok = tok;
    }
}
