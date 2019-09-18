package lexer;

public class main {
    public static void main(String[] args) throws Exception {

        Lexer myLexer = new Lexer("{ int b; b = 1; {         int a; a = 2;         do a = a+1; while(a < 100); } }");
        while (true) {
            Token tok = myLexer.getNextToken();
            if (tok == null) {
                break;
            } else if (tok.getName() == "EOF") {
                break;
            } else {
                System.out.print(tok.getName() + "\t" + tok.getPattern() + "\n");
            }
        }

    }
}