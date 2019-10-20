package parser;

import lexer.Lexer;
import lexer.Token;
import parser.intermediate.*;

public class Parser {
    private Lexer lex;

    public Parser() {
        lex = null;
    }

    public Parser(Lexer lexer) {
        this.lex = lexer;
    }

    public void parse() {
        if (lex == null) return;

        for (Token tok = lex.getNextToken(); !tok.getPattern().equals("\26");  tok = lex.getNextToken()) {

        }
    }

    public Program program() { // Program -> block
        return new Program(block());
    }

    public Block block() { // block -> { decls stmts }
        Stmt s = new Stmt();
        return new Block(s);
    }


    // decls -> E | decls decl

    // type -> int | float | bool

    // stmts -> E | stmts stmt

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
