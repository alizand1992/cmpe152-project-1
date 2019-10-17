import lexer.Token;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

import static org.junit.Assert.*;

public class ScopeTest {
    Token tok1;
    @Before
    public void init() {
        tok1 = new Token("ABC", "ID");
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

    @Test(expected = IllegalArgumentException.class)
    public void addTokenDoesNotAddTokenTwiceEvenWhenInHigherScopes() {
        Scope sc = new Scope();
        sc.addToken(tok1).createScope().addToken(tok1);
    }

}
