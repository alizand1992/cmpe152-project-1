import lexer.Lexer;
import lexer.Token;
import parser.Parser;
import parser.intermediate.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Compiler {
    public static void main(String[] argv) {
        String path = "";
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter a file to compile: ");
        System.out.println("Working Directory: " + System.getProperty("user.dir"));
        path = in.nextLine();

        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Lexer lex = new Lexer(lines);

        Parser parser = new Parser(lex);

        try {
            Node root = parser.parse();

            printNode(root, "--");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printNode(Node node, String prefix) {
        if (node == null)
            return;
        System.out.println(prefix + " " + node.getLabel());

        for (Node nd : node.getChildren()) {
            printNode(nd, prefix + "--");
        }
    }
}

