package parser.intermediate;

import java.util.ArrayList;

public class While extends Stmt {
    public While(Expression expr, ArrayList<Stmt> stmts) {
        super("WHILE", expr, stmts);
    }
}
