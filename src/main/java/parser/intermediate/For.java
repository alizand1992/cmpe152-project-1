package parser.intermediate;

import java.util.ArrayList;

public class For extends Stmt {
    public For(ArrayList<Stmt> stmts) {
        super("FOR", stmts);
    }
}
