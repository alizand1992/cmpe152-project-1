import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest {
    @Test
    public void getHelloReturnsHelloString() {
        assertEquals("Hello World!", HelloWorld.getHello());
    }
}
