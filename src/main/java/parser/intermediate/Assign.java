package parser.intermediate;

import lexer.Token;
import parser.Scope;
import util.Type;

public class Assign extends Expression {
    public Assign(Token id, Expression expr, Scope scope) throws Exception {
        super(id);

        Token rhs;
        if (expr.getToken().getName().equals("ID")) {
            rhs = scope.getToken(expr.getToken()).getType();
        } else {
            rhs = expr.getToken();
        }

        if (rhs.getName().equals("REAL"))
            rhs = new Token("float", "BASE_TYPE");
        else if (rhs.getName().equals("NUM"))
            rhs = new Token("int", "BASE_TYPE");

        Token lhs = scope.getToken(id).getType();

        Token type = Type.typeCast(lhs, rhs);
        if (!type.getName().equals(scope.getToken(id).getType().getName())) {
            throw new Exception("Type miss match!");
        }

        addChild(new Expression(id));
        addChild(expr);
        setLabel("ASSIGN");
    }
}
