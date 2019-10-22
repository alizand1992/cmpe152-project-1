package parser.intermediate;

import java.util.ArrayList;

public class For extends Stmt {
    public For(Expression expr, ArrayList<Stmt> stmts) {
        super("FOR", expr, stmts);
    }
}
