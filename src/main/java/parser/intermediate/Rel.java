package parser.intermediate;

import lexer.Token;

public class Rel extends Logical {

    public Rel(Token tok, Expression expr1, Expression expr2) {
        super(tok, expr1, expr2);
    }
}
