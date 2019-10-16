import lexer.Token;

import java.util.HashSet;
import java.util.Stack;

public class Scope {
    private Stack<HashSet<Token>> scopes = new Stack<>();

    /**
     * Default constructor
     * does nothing
     */
    public Scope() {

    }

    /**
     * inserts the hashset as the first scope
     *
     * @param scope scope to be added.
     */
    public Scope(HashSet<Token> scope) {

    }

    /**
     * Copy scope
     *
     * @param scope copy scope object
     */
    public Scope(Scope scope) {

    }

    /**
     * responds with a true or false whether or not a token is in scope or not
     *
     * @param tok token to check and see if is in scope
     * @return false or true result of the search
     */
    public boolean tokenInScope(Token tok) {

        return false;
    }

    /**
     * Pops the latest scope when exiting outside of the scope
     */
    public void exitScope() {

    }

    /**
     * Adds a token to the latest scope. Should throw an error if the token already exists
     *
     * @param tok token to be added
     * @return returns itself so that it is chainable
     * @throws IllegalArgumentException Error in case of duplicate token
     */
    public Scope addToken(Token tok) throws IllegalArgumentException {

        return this;
    }

    /**
     * Creates a new HashSet and pushes it to the top of the stack
     *
     * @return itself so it is chainable
     */
    public Scope createScope() {

        return this;
    }

    /**
     * Pushes the "scope" hashset to the top of the stack
     *
     * @param scope hashset to be pushed to the top of the stack
     * @return itself so it is chainable
     */
    public Scope createScope(HashSet<Token> scope) {

        return this;
    }

    /**
     * returns the latest scope
     *
     * @return top hashset in stack
     */
    public HashSet<Token> getScope() {
        if (scopes.empty()) {
            return null;
        }

        return scopes.peek();
    }
}
