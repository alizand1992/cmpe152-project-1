package parser.intermediate;

import lexer.Token;

public class And extends Logical {
    public And(Token tok, Expression expr1, Expression expr2) {
        super(tok, expr1, expr2);
        setLabel("AND");
    }
}
