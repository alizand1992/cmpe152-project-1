package lexer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LexerTest {
    @Test
    public void idWithSemiColonIsRecogonizedAsTwoTokens() {
        // Example abcd;
        Lexer lex = new Lexer("abcd;");
        assertEquals(2, lex.getTokens().size());
    }

    @Test
    public void idWithSemiColonHasAnIDAndSemicolonToken() {
        Lexer lex = new Lexer("abcd;");
        assertEquals("ID", ((Token)(lex.getTokens().get(0))).getName());
        assertEquals(";", ((Token)(lex.getTokens().get(1))).getName());
    }

    @Test
    public void incorrectTokenReturnsNull() {
        Lexer lex = new Lexer("9abc");

        assertEquals(null, lex.getNextToken());
    }
}
