package parser.intermediate;

import java.util.ArrayList;

public class Do extends Stmt {
    public Do(Expression expr, ArrayList<Stmt> stmts) {
        super("DO", expr, stmts);
    }
}
