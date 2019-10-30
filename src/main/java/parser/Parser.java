package parser;

import lexer.Lexer;
import lexer.Token;
import parser.intermediate.*;

import java.util.ArrayList;
import java.util.LinkedList;

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
            System.out.println("66 " + lex.getTokens());
        }

        return true;
    }

    // decls -> E | decls decl
    public void decls() throws Exception {
        String type = type();
        match("ID", false);
        scope.addToken(lex.getNextToken());
        System.out.println("77 " + lex.getTokens());
        match(";");

        if (lex.peek().getName().equals("BASE_TYPE")) {
            decls();
        }
    }

    // type -> int | float | bool
    String type() throws Exception {
        Token currentToken = lex.getNextToken();
        System.out.println("88 " + lex.getTokens());
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
                System.out.println("129 " + lex.getTokens());
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
                    System.out.println("142 " + lex.getTokens());
                    stmts.add(stmt());
                }

                return new IfElse(expr, stmts);
            case "WHILE":
                lex.getNextToken();
                System.out.println("149 " + lex.getTokens());
                match("(");
                expr = allexpr();
                match(")");
                stmts.add(stmt());
                return new While(expr, stmts);
            case "DO":
                lex.getNextToken();
                System.out.println("157 " + lex.getTokens());
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
                System.out.println("171 " + lex.getTokens());
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
                System.out.println("184 " + lex.getTokens());
                match(";");
                return new Break();
            case "BASE_TYPE":
                decls();
            case "}":
                return null;
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
        System.out.println("205 " + lex.getTokens().toString());
        match("=");

        // GOTTA FIGURE OUT WHAT TO DO WITH EXPR
        // Set object in example is a good guide.
        Expression expr = allexpr();
        match(";");
        return expr;
    }

    // allexpr -> allexpr || andexpr | andexpr
    public Expression allexpr() throws Exception {
        Expression expr = andexpr();
        Token currentToken = lex.peek();

        while (currentToken.getName().equals("OR")) {
            currentToken = lex.getNextToken();
            System.out.println("222 " + lex.getTokens().toString());
            expr = new Or(currentToken, expr, andexpr());
        }

        return expr;
    }

    // andexpr -> andexpr && equal | equal
    public Expression andexpr() throws Exception {
        Expression expr = equal();
        Token currentToken = lex.peek();

        while (currentToken.getName().equals("AND")) {
            currentToken = lex.getNextToken();
            System.out.println("236 " + lex.getTokens().toString());
            expr = new And(currentToken, expr, equal());
        }

        return expr;
    }
    // equal -> equal == rel | equal != rel | rel
    public Expression equal() throws Exception {
        Expression expr = rel();
        Token currentToken = lex.peek();

        while (currentToken.getName().equals("EQ") || currentToken.getName().equals("NE")) {
            currentToken = lex.getNextToken();
            System.out.println("249 " + lex.getTokens().toString());
            expr = new Rel(currentToken, expr, rel());
        }

        return expr;
    }

    // rel -> expr < expr |  expr <= expr | expr > expr | expr >= expr | expr
    public Expression rel() throws Exception {
        Expression expr = expr();
        Token currentToken = lex.peek();

        switch (currentToken.getName()) {
            case "LE":
            case "<":
            case ">":
            case "GE":
                currentToken = lex.getNextToken();
                System.out.println("267 " + lex.getTokens().toString());
                return new Rel(currentToken, expr, expr());
            default:
                return expr;
        }
    }

    // expr -> expr + term | expr - term | term
    Expression expr() throws Exception {
        Expression expr = term();
        Token currentToken = lex.peek();

        while (currentToken.getName().equals("+") || currentToken.getName().equals("-")) {
            currentToken = lex.getNextToken();
            System.out.println("281 " + lex.getTokens().toString());
            expr = new Arith(currentToken, expr, term());
        }

        return expr;
    }


    // term -> term * factor | term / factor | factor
    public Expression term() throws Exception {
        Expression expr = factor();
        Token currentToken = lex.peek();

        while (currentToken.getName().equals("*") || currentToken.getName().equals("/")) {
            System.out.println("295 " + lex.getTokens());
            if (lex.peek().getName().equals(";"))
                break;
            currentToken = lex.getNextToken();
            System.out.println("297 " + lex.getTokens());
            expr = new Arith(currentToken, expr, factor());
            System.out.println("299 " + lex.getTokens());
        }

        return expr;
    }

    // incdecexpr -> id++ | id--
    public Stmt incdecexpr() throws Exception {
        if (match("ID", false)) {
            idInScope(lex.getNextToken());
        }

        Token currentToken = lex.peek();
        if (currentToken.getName().equals("INC") || currentToken.getName().equals("DEC")) {
            currentToken = lex.getNextToken();
            System.out.println("312 " + lex.getTokens());
            return new Stmt(currentToken.getName());
        }

        return null;
    }

    // STILL NEEDS WORK
    // factor -> (allexpr) | incdecexpr | id | num | real | true | false
    public Expression factor() throws Exception {
        Token tok = lex.peek();
        System.out.println("323 " + lex.getTokens());
        switch (tok.getName()) {
            case "(":
                Expression expr = allexpr();
                match(")");
                return expr;
            case "TRUE":
            case "FALSE":
            case "NUM":
            case "REAL":
                tok = lex.getNextToken();
                return new Expression(tok);
            case "ID":
                tok = lex.getNextToken();
                idInScope(tok);
                return new Expression(tok);
            case "INC":
            case "DEC":
                // Increment Decrement
                incdecexpr();
        }

        return null;
    }

    private void idInScope(Token id) throws Exception {
        if (!scope.tokenInScope(id))
            throw new Exception("Id not found");
    }
}
