package parser.intermediate;

import lexer.Token;
import parser.Scope;
import parser.ScopeElement;
import util.Type;

import java.util.HashSet;
import java.util.LinkedList;

public class Assign extends Expression {
    public Assign(Token id, Expression expr, Scope scope) throws Exception {
        super(id);

        Token rhs;
        if (expr.getChildren().size() > 1) {
            LinkedList<Node> nd = new LinkedList<>(expr.getChildren());
            Token x1 = ((Expression)nd.pop()).getToken();
            Token x2 = ((Expression)nd.pop()).getToken();

            if (x1.getName().equals("ID"))
                x1 = scope.getToken(x1).getType();

            if (x2.getName().equals("ID")) {
                x2 = scope.getToken(x2).getType();
            }


            rhs = Type.typeCast(x1,  x2);

            for (Node n : nd) {
                Token t = ((Expression)n).getToken();

                if (t.getName().equals("ID"))
                    t = scope.getToken(expr.getToken()).getType();

                rhs = Type.typeCast(rhs, t);
            }
        } else {
            if (expr.getToken().getName().equals("ID")) {
                rhs = scope.getToken(expr.getToken()).getType();
            } else {
                rhs = expr.getToken();
            }


            if (rhs.getName().equals("REAL"))
                rhs = new Token("float", "BASE_TYPE");
            else if (rhs.getName().equals("NUM"))
                rhs = new Token("int", "BASE_TYPE");
        }

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
