package parser.intermediate;

public class Block extends Node {
    public Block(Stmt stmt) {
        super("BLOCK", stmt);
    }
}
