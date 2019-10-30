package parser.intermediate;

import java.util.ArrayList;

public class Stmt extends Node {
    public Stmt() {

    }

    public Stmt(String label) {
        super(label);
    }

    public Stmt(String label, Stmt s) {
        super(label, s);
    }

    public Stmt(String label, ArrayList<Stmt> stmts) {
        super(label);
        for(Stmt stmt : stmts) {
            addChild(stmt);
        }
    }

    public Stmt(String label, Expression expr, ArrayList<Stmt> stmts) {
        super(label);

        addChild(expr);
        for(Stmt stmt : stmts) {
            addChild(stmt);
        }
    }
}
