package parser;

import lexer.Lexer;
import lexer.Token;
import parser.intermediate.*;

import java.util.ArrayList;
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

    public void parse() throws Exception {
        if (lex == null) return;
        program();
    }

    public Program program() throws Exception { // Program -> block
        return new Program(block());
    }

    public Block block() throws Exception { // block -> { decls stmts }
        // Match opening block and create a new Scope
        match("{");
        scope.createScope();

        // Decls


        // Stmts
        Stmt s = new Stmt();

        //  match close bracket
        match("}");
        return new Block(s);
    }

    private boolean match(String pattern) throws Exception {
        return match(pattern, true);
    }

    private boolean match(String pattern, boolean discard) throws Exception {
        if (!lex.peek().getPattern().equals(pattern)) {
            throw new Exception("Syntax Error!");
        }
        if (discard) {
            lex.getNextToken();
        }

        return true;
    }

    // decls -> E | decls decl

    // type -> int | float | bool

    // stmts -> E | stmts stmt

    /**
     * Calls statement for the first token and itself for the remaining this way it recursively goes throw each token.
     * This allows the all statements to be processed until the end of the block.
     *
     * @return
     * @throws Exception Syntax error
     */
    public Stmt stmts() throws Exception {
        if (match("}", false)) {
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
        Token tok = lex.getNextToken();
        Expression expr;
        ArrayList<Stmt> stmts = new ArrayList<>();

        switch (tok.getName()) {
            case ";":
                return null;
            case "IF":
                // If condition expression
                match("(");
                expr = allexpr();
                match(")");
                stmts.add(stmt());

                // Does it have an else
                if (lex.peek().getName().equals("ELSE")) {
                    lex.getNextToken();
                    stmts.add(stmt());
                }

                return new IfElse(expr, stmts);
            case "WHILE":
                match("(");
                expr = allexpr();
                match(")");
                stmts.add(stmt());
                return new While(expr, stmts);
            case "DO":
                // Get the statements inside the do
                stmts.add(stmt());
                // verify after the do statement there is a while
                match("while");
                // Add expression for while.
                match("(");
                expr = allexpr();
                match(")");
                // Do while must end in a ';'
                match(";");
                return new Do(expr, stmts);
            case "BREAK":
                break;
            case "{":
                return block();
            default:
                return assign();
        }
        return null;
    }

    // assign -> id = allexpr;
    public Stmt assign() throws Exception {

        return null;
    }


    // allexpr -> allexpr || andexpr | andexpr
    public Expression allexpr() throws Exception {

        return null;
    }

    // andexpr -> andexpr && equal | equal

    // equal -> equal == rel | equal != rel | rel

    // rel -> expr < expr |  expr <= expr | expr > expr | expr >= expr | expr

    // expr -> expr + term | expr - term | term

    // term -> term * factor | term / factor | factor

    // incdecexpr -> id++ | id--

    // factor -> (allexpr) | incdecexpr | id | num | real | true | false
//    public Factor factor() {
//
//        return
//    }

}
