package parser;

import lexer.Lexer;
import lexer.Token;
import parser.intermediate.*;

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

    private void match(String pattern) throws Exception {
        match(pattern, true);
    }

    private void match(String pattern, boolean discard) throws Exception {
        if (!lex.peek().getPattern().equals(pattern)) {
            throw new Exception("Syntax Error!");
        }
        if (discard) {
            lex.getNextToken();
        }
    }

    // decls -> E | decls decl

    // type -> int | float | bool

    // stmts -> E | stmts stmt
    public Stmt stmts() throws Exception {
        match("}", false);
        return new Stmt();
    }


    // assign -> id = allexpr;

    // stmt -> assign | if (allexpr) stmt | if (allexpr) stmt else stmt | while (allexpr) stmt |
    //     do stmt while (allexpr); | for (assign allexpr; incdecexpr) stmt | break; | block

    // allexpr -> allexpr || andexpr | andexpr

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
