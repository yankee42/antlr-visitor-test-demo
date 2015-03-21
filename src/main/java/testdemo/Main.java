package testdemo;

import demo.DemoLexer;
import demo.DemoParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream("2+2");
        System.out.println(compile(input));
    }
    
    public static String compile(ANTLRInputStream input) {
        DemoLexer lexer = new DemoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DemoParser parser = new DemoParser(tokens);
        ParseTree tree = parser.expression();
        return new MyVisitor().visit(tree);
    }
}
