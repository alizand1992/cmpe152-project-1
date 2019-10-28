package parser.intermediate;

import lexer.Token;
import parser.Scope;

public class Id extends Node {
    private Token tok;
    private String id;
    private String type;

    public Id(String id, Token tok) {
        this.id = id;
        this.tok = tok;
    }

    public boolean inScope(Scope scope) {
        return scope.tokenInScope(tok);
    }

    public boolean correctType(String type) {
        return this.type.equals(type);
    }
}
