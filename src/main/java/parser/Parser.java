package parser;

import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;
import parser.intermediate.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class Parser {
    private Lexer lex;
    private Scope scope;

    public Parser() {
        this(null);
    }

    public Parser(Lexer lexer) {
        this.lex = lexer;
        scope = new Scope();
    }

    public Node parse() throws Exception {
        if (lex == null) {
            throw new Exception("Lexer is empty.");
        };
        return program();
    }

    public Program program() throws Exception { // Program -> block
        return new Program(block());
    }

    public Block block() throws Exception { // block -> { decls stmts }
        // Match opening block and create a new Scope
        match("{");
        scope.createScope();

        // Decls
        decls();

        // Stmts
        Stmt s = stmts();

        //  match close bracket
        match("}");
        return new Block(s);
    }

    private boolean match(String name) throws Exception {
        return match(name, true);
    }

    private boolean match(String name, boolean discard) throws Exception {
        if (!lex.peek().getName().equals(name)) {
            LinkedList<Token> tokens = lex.getTokens();

            for (Token tok : tokens) {
                System.err.println(tok.toString());
            }

            throw new Exception("Syntax Error!\n  Expected: " + name + "\n  Got: " + lex.peek().getName());
        }
        if (discard) {
            lex.getNextToken();
        }

        return true;
    }

    // decls -> E | decls decl
    public void decls() throws Exception {
        String type = type();
        match("ID", false);
        scope.addToken(lex.getNextToken());
        match(";");

        if (lex.peek().getName().equals("BASE_TYPE")) {
            decls();
        }
    }

    // type -> int | float | bool
    String type() throws Exception {
        Token currentToken = lex.getNextToken();

        if (!currentToken.getName().equals("BASE_TYPE")) {
            throw new Exception("Token is not a type.");
        }

        return currentToken.getPattern();
    }

    /**
     * Calls statement for the first token and itself for the remaining this way it recursively goes throw each token.
     * This allows the all statements to be processed until the end of the block.
     * stmts -> E | stmts stmt
     *
     * @return
     * @throws Exception Syntax error
     */
    public Stmt stmts() throws Exception {
        if (lex.peek().getName().equals("}")) {
            return null;
        }

        return new StatementSequence(stmt(), stmts());
    }

    /**
     * This checks each actual statement based ont the following rule:
     * stmt -> assign | if (allexpr) stmt | if (allexpr) stmt else stmt | while (allexpr) stmt |
     *         do stmt while (allexpr); | for (assign allexpr; incdecexpr) stmt | break; | block
     *
     * @return
     * @throws Exception
     */
    public Stmt stmt() throws Exception {
        Token tok = lex.peek();

        Expression expr;
        ArrayList<Stmt> stmts = new ArrayList<>();

        switch (tok.getName()) {
            case ";":
                lex.getNextToken();
                return null;
            case "IF":
                lex.getNextToken();
                // If condition expression
                match("(");
                expr = allexpr();
                match(")");
                stmts.add(stmt());

                // Does it have an else
                if (match("ELSE", false)) {
                    lex.getNextToken();
                    stmts.add(stmt());
                }

                return new IfElse(expr, stmts);
            case "WHILE":
                lex.getNextToken();
                match("(");
                expr = allexpr();
                match(")");
                stmts.add(stmt());
                return new While(expr, stmts);
            case "DO":
                lex.getNextToken();
                // Get the statements inside the do
                stmts.add(stmt());
                // verify after the do statement there is a while
                match("WHILE");
                // Add expression for while.
                match("(");
                expr = allexpr();
                match(")");
                // Do while must end in a ';'
                match(";");
                return new Do(expr, stmts);
            case "FOR":
                lex.getNextToken();
                // Match the paranthesis and the expression/statement inside of it
                //   eg.  (int i = 0; i < 123; i++)
                match("(");
                stmts.add(assign());
                expr = (allexpr());
                match(";");
                stmts.add(incdecexpr());
                match(")");
                // Capture statements inside the for loop
                stmts.add(stmt());
            case "BREAK":
                lex.getNextToken();
                match(";");
                return new Break();
            case "BASE_TYPE":
                decls();
            case "{":
                return block();
            default:
                return assign();
        }
    }

    // STILL NEEDS WORK
    // assign -> id = allexpr;
    public Stmt assign() throws Exception {
        match("ID", false);

        // Throw an error if the id is not already decleared.
        idInScope(lex.getNextToken());

        match("=");

        // GOTTA FIGURE OUT WHAT TO DO WITH EXPR
        // Set object in example is a good guide.
        Expression expr = allexpr();

        // For only when to ids get set to eachother
        // THIS NEEDS TO CHANGE
        match("ID", false);
        idInScope(lex.getNextToken());

        match(";");
        return null;
    }

    // allexpr -> allexpr || andexpr | andexpr
    public Expression allexpr() throws Exception {

        return null;
    }

    // andexpr -> andexpr && equal | equal
    public Expression andexpr() throws Exception {

        return null;
    }
    // equal -> equal == rel | equal != rel | rel
    public Expression equal() throws Exception {
        return null;
    }

    // rel -> expr < expr |  expr <= expr | expr > expr | expr >= expr | expr
    public Expression rel() throws Exception {

        return null;
    }

    // expr -> expr + term | expr - term | term
    Expression expr() throws Exception {

        return null;
    }


    // term -> term * factor | term / factor | factor
    public Expression term() throws Exception {

        return null;
    }

    // incdecexpr -> id++ | id--
    public Stmt incdecexpr() throws Exception {
        if (match("ID", false)) {
            idInScope(lex.getNextToken());
        }

        return null;
    }

    // STILL NEEDS WORK
    // factor -> (allexpr) | incdecexpr | id | num | real | true | false
    public Stmt factor() {
        Token tok = lex.getNextToken();
        switch (tok.getName()) {
            case "TRUE":
            case "FALSE":
                return new Stmt(tok.getName());
            case "ID":
                break;
            case "NUM":
            case "REAL":
                break;
        }

        return null;
    }

    private void idInScope(Token id) throws Exception {
        if (!scope.tokenInScope(id))
            throw new Exception("Id not found");
    }
}
