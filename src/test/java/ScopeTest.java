package parser;

import lexer.Token;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class ScopeTest {
    Token tok1;
    Token tok1_type;

    Token tok2;
    Token tok2_type;
    @Before
    public void init() {
        tok1 = new Token("ABC", "ID");
        tok1_type = new Token("int", "BASE_TYPE");
        tok2 = new Token("a", "ID");
        tok2_type = new Token("float", "BASE_TYPE");
    }

    @Test
    public void addTokenCreatesAScopeIfNoScopeExist() {
        Scope sc = new Scope();
        sc.addToken(tok1, tok1_type);
        assertFalse(sc.getScope() == null);
    }

    @Test
    public void addTokenWillAddTheTokenToTheScope() {
        Scope sc = new Scope();
        sc.addToken(tok1, tok1_type);

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
        HashSet<ScopeElement> hs = new HashSet<>();
        hs.add(new ScopeElement(tok1, tok1_type));

        sc.createScope(hs);

        assertEquals(hs, sc.getScope());
    }

    @Test
    public void addTokenWillAddTheTokenToTheTopScope() {
        Scope sc = new Scope();
        sc.createScope().createScope();
        sc.addToken(tok1, tok1_type);
        sc.exitScope();
        assertFalse(sc.getScope().contains(tok1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTokenDoesNotAddATokenTwice() {
        Scope sc = new Scope();
        sc.addToken(tok1, tok1_type);
        sc.addToken(tok1, tok1_type);
    }

    @Test
    public void tokenInScopeFindsTokenInTopScope() {
        Scope sc = new Scope();
        sc.addToken(tok1, tok1_type);
        assertTrue(sc.tokenInScope(tok1));
    }

    @Test
    public void tokenInScopeFindsTokenInAllScopes() {
        Scope sc = new Scope();
        sc.addToken(tok1, tok1_type);
        sc.createScope();
        assertTrue(sc.tokenInScope(tok1));
    }

    @Test
    public void tokeninScopeFindsTokenInTopScopeWhenMultipleTokensPresent() {
        Scope sc = new Scope();
        System.out.println(tok1.toString() + " " + tok2.toString());
        sc.addToken(tok1, tok1_type).addToken(tok2, tok2_type);
        assertTrue(sc.tokenInScope(tok1));
    }
}
