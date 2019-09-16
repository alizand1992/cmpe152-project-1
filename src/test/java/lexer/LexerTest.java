package lexer;

import org.junit.Test;

import static org.junit.Assert.*;

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

    // isId function test
    @Test
    public void isIdReturnsFalseIfStartingWithNumber() {
        assertFalse(Lexer.isId("9asd"));
    }

    @Test
    public void isIdReturnsFalseIfStartingWithUnderScore() {
        assertFalse(Lexer.isId("_abcd"));
    }

    @Test
    public void isIdReturnsTrueIfOnlyLowerCasse() {
        assertTrue(Lexer.isId("abcd"));
    }

    @Test
    public void isIdRetrunsTrueIfLowerCaseAndUnderscoor() {
        assertTrue(Lexer.isId("ab_cd"));
    }

    @Test
    public void isIdReturnsFalseIfOtherCharactesThanIdPatternAreinString() {
        assertFalse(Lexer.isId("abcd;"));
    }

    @Test
    public void isIdReturnsTrueIfLowerCaseAndNumber() {
        assertTrue(Lexer.isId("abcd9"));
    }

    @Test
    public void isIdReturnsTrueIfLowerCaseNumberAndUnderscore() {
        assertTrue(Lexer.isId("ab_cd9"));
    }

    @Test
    public void isIdReturnsTrueIfAll() {
        assertTrue(Lexer.isId("Abc_d9"));
    }

    // isReal function tests
    @Test
    public void isRealReturnsTrueIfReal() {
        assertTrue(Lexer.isReal("123.123"));
    }

    @Test
    public void isRealReturnsFalseIfNum() {
        assertEquals(false, Lexer.isReal("123"));
    }

    @Test
    public void isRealReturnsFalseIfString() {
        assertEquals(false, Lexer.isReal("abcd9.9"));
    }

    @Test
    public void isRealReturnsFalseIfIncorrectFormat() {
        assertEquals(false, Lexer.isReal("99.99.99"));
    }

    // isNum function tests
    @Test
    public void isNumReturnsTrueIfNum() {
        assertEquals(true, Lexer.isNum("99"));
    }

    @Test
    public void isNumReturnsFalseIfString() {
        assertEquals(false, Lexer.isNum("123a"));
    }

    @Test
    public void isNumReturnsFalseIfReal() {
        assertEquals(false, Lexer.isNum("123.123"));
    }

    // getNextToken
    @Test
    public void getNextTokenReturnsFirstTokenCorrectly() {
        Lexer lex = new Lexer("int a = 5;");
        assertEquals(new Token("int", "BASE_TYPE"), lex.getNextToken());
        assertEquals(new Token("a", "ID"), lex.getNextToken());
        assertEquals(new Token("="), lex.getNextToken());
        assertEquals(new Token("5", "NUM"), lex.getNextToken());
        assertEquals(new Token(";"), lex.getNextToken());
    }

    @Test
    public void incorrectTokenReturnsNull() {
        Lexer lex = new Lexer("9abc");
        assertEquals(null, lex.getNextToken());
    }
}
