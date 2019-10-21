package parser.intermediate;

public class Block extends Stmt {
    public Block(Stmt stmt) {
        super("BLOCK", stmt);
    }
}
