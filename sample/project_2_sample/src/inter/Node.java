package inter;

import lexer.Lexer;

import java.util.ArrayList;

public class Node {
   public int lexline = 0;
   ArrayList<Node> children = new ArrayList<>();

   Node() {
      lexline = Lexer.line;
   }

   public ArrayList<Node> getChildren() {
      return children;
   }

   public String getNodeStr() {
      return "";
   }

   void error (String s) {
      throw new Error("near line "+lexline+": "+s);
   }
}
