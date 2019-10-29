package parser.intermediate;

import lexer.Token;

public class Logical extends Expression {

    Logical(Token tok, Expression expr1, Expression expr2) {
        super(tok);

        addChild(expr1);
        addChild(expr2);
    }
}
