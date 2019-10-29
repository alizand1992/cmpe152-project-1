package parser.intermediate;

import lexer.Token;

public class Or extends Logical {
    public Or(Token tok, Expression expr1, Expression expr2) {
        super(tok, expr1, expr2);
        setLabel("OR");
    }
}
