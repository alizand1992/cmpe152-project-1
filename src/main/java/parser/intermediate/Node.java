package parser.intermediate;

import java.util.ArrayList;

public class Node {
    protected ArrayList<Node> children;
    protected String label;

    public Node() {
        this("");
    }

    public Node(String label) {
        children = new ArrayList<>();
        this.label = label;
    }

    public Node(String label, Node node) {
        this(label);
        addChild(node);
    }

    public Node addChild(Node child) {
        this.children.add(child);
        return this;
    }

    public String getLabel() {
        return this.label;
    }
}
