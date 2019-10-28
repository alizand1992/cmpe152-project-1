package parser;

import lexer.Token;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class ScopeTest {
    Token tok1;
    Token tok2;
    @Before
    public void init() {
        tok1 = new Token("ABC", "ID");
        tok2 = new Token("a", "ID");
    }

    @Test
    public void addTokenCreatesAScopeIfNoScopeExist() {
        Scope sc = new Scope();
        sc.addToken(tok1);
        assertFalse(sc.getScope() == null);
    }

    @Test
    public void addTokenWillAddTheTokenToTheScope() {
        Scope sc = new Scope();
        sc.addToken(tok1);

        assertTrue(sc.getScope().contains(tok1));
    }

    @Test
    public void createScopeWithNoArgumentCreatesAnEmptyHashSet() {
        Scope sc = new Scope();
        sc.createScope();

        assertTrue(sc.getScope().isEmpty());
    }

    @Test
    public void createScopeWithArgumentAddsHashSetAsScope() {
        Scope sc = new Scope();
        HashSet<Token> hs = new HashSet<>();
        hs.add(tok1);

        sc.createScope(hs);

        assertEquals(hs, sc.getScope());
    }

    @Test
    public void addTokenWillAddTheTokenToTheTopScope() {
        Scope sc = new Scope();
        sc.createScope().createScope();
        sc.addToken(tok1);
        sc.exitScope();
        assertFalse(sc.getScope().contains(tok1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTokenDoesNotAddATokenTwice() {
        Scope sc = new Scope();
        sc.addToken(tok1);
        sc.addToken(tok1);
    }

    @Test
    public void tokenInScopeFindsTokenInTopScope() {
        Scope sc = new Scope();
        sc.addToken(tok1);
        assertTrue(sc.tokenInScope(tok1));
    }

    @Test
    public void tokenInScopeFindsTokenInAllScopes() {
        Scope sc = new Scope();
        sc.addToken(tok1);
        sc.createScope();
        assertTrue(sc.tokenInScope(tok1));
    }

    @Test
    public void tokeninScopeFindsTokenInTopScopeWhenMultipleTokensPresent() {
        Scope sc = new Scope();
        System.out.println(tok1.toString() + " " + tok2.toString());
        sc.addToken(tok1).addToken(tok2);
        assertTrue(sc.tokenInScope(tok1));
    }
}
