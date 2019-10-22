package parser.intermediate;

import java.util.ArrayList;

public class IfElse extends Stmt {
    public IfElse(Expression expr, ArrayList<Stmt> stmts) {
        super();

        String label = "IF";

        if (stmts.size() == 2) {
            label = "IF-ELSE";
        }

        setLabel(label);

        addChild(expr);
        for (Stmt stmt : stmts) {
            addChild(stmt);
        }
    }
}
