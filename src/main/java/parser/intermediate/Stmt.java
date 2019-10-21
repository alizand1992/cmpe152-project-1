package parser.intermediate;

public class Stmt extends Node {
    public Stmt() {

    }

    public Stmt(String label) {
        super(label);
    }

    public Stmt(String label, Stmt s) {
        super(label, s);
    }
}
