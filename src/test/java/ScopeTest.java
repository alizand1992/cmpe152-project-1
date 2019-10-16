import lexer.Token;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScopeTest {
    @Test
    public void addTokenWillAddTheTokenToTheTopHashSet() {
        Scope sc = new Scope();
        Token tok = new Token("ABC", "ID")
        sc.addToken(tok);

        assertTrue(sc.getScope().contains(tok));
    }
}
