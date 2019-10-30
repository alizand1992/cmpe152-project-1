package parser;

import lexer.Token;

import java.util.HashSet;
import java.util.LinkedList;

public class Scope {
    private LinkedList<HashSet<ScopeElement>> scopes;

    /**
     * Default constructor
     * does nothing
     */
    public Scope() {
        scopes = new LinkedList<>();
    }

    /**
     * inserts the hashset as the first scope
     *
     * @param scope scope to be added.
     */
    public Scope(HashSet<ScopeElement> scope) {
        this();
        scopes.push(scope);
    }

    /**
     * Copy scope
     *
     * @param scope copy scope object
     */
    public Scope(Scope scope) {
        scopes = (LinkedList<HashSet<ScopeElement>>)scope.getAllScopes().clone();
    }

    /**
     * responds with a true or false whether or not a token is in scope or not
     *
     * @param tok token to check and see if is in scope
     * @return false or true result of the search
     */
    public boolean tokenInScope(Token tok) {
        for (HashSet<ScopeElement> sc : getAllScopes()) {
            for (ScopeElement rhs : sc) {
                if (rhs.getId().equals(tok)) {
                    return true;
                }
            }
        }
        return false;
    }

    public ScopeElement getToken(Token tok) {
        for (HashSet<ScopeElement> sc : getAllScopes()) {
            for (ScopeElement rhs : sc) {
                if (rhs.getId().equals(tok)) {
                    return rhs;
                }
            }
        }
        return null;
    }

    /**
     * Pops the latest scope when exiting outside of the scope
     */
    public HashSet<ScopeElement> exitScope() {
        return scopes.removeLast();
    }

    /**
     * Adds a token to the latest scope. Should throw an error if the token already exists
     *
     * @param tok token to be added
     * @return returns itself so that it is chainable
     * @throws IllegalArgumentException Error in case of duplicate token
     */
    public Scope addToken(Token tok, Token type) throws IllegalArgumentException {
        if (scopes.isEmpty()) {
            createScope();
        }

        if (scopes.peekLast().contains(tok)) {
            throw new IllegalArgumentException("Cannot Insert a Token Twice");
        }

        HashSet<ScopeElement> hs = scopes.removeLast();
        hs.add(new ScopeElement(tok, type));
        scopes.add(hs);

        return this;
    }
    /**
     * returns the latest scope
     *
     * @return top hashset in stack
     */
    public HashSet<ScopeElement> getScope() {
        if (scopes.isEmpty()) {
            return null;
        }

        return scopes.peek();
    }

    public LinkedList<HashSet<ScopeElement>> getAllScopes() {
        return scopes;
    }

    /**
     * Creates a new HashSet and pushes it to the top of the stack
     *
     * @return itself so it is chainable
     */
    public Scope createScope() {
        scopes.push(new HashSet<ScopeElement>());
        return this;
    }

    /**
     * Pushes the "scope" hashset to the top of the stack
     *
     * @param scope hashset to be pushed to the top of the stack
     * @return itself so it is chainable
     */
    public Scope createScope(HashSet<ScopeElement> scope) {
        this.scopes.push(scope);
        return this;
    }

}
