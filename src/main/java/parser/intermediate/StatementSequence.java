package parser.intermediate;

public class StatementSequence extends Stmt {
    public StatementSequence(Stmt s1, Stmt s2) {
        super("STMTS");
        addChild(s1);
        addChild(s2);
    }
}
