package lexer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TokenTest {
    @Test
    public void singleCharacterTokensReturnPatternAsTokenName() {
        Token singleCharTok = new Token(";");
        assertEquals(";", singleCharTok.getName());
    }
}
