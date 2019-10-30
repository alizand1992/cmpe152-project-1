package parser.intermediate;

import lexer.Token;

public class Assign extends Expression {
    public Assign(Token id, Expression expr) {
        super(id);
        addChild(new Expression(id));
        addChild(expr);
        setLabel("ASSIGN");
    }
}
