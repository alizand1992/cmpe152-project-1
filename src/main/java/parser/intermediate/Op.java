package parser.intermediate;

import lexer.Token;

public class Op extends Expression {
    private String type;

    public Op(Token tok, String type) {
        super(tok);
    }
}
