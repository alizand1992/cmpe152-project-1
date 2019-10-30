package parser;

import lexer.Token;

public class ScopeElement {
    private Token id;
    private Token type;

    public ScopeElement(Token id, Token type) {
        this.id = id;
        this.type = type;
    }

    public Token getId() {
        return id;
    }

    public Token getType() {
        return type;
    }
}
