package testdemo;

import demo.DemoBaseVisitor;
import demo.DemoParser;
import org.antlr.v4.runtime.misc.NotNull;

public class MyVisitor extends DemoBaseVisitor<String> {
    @Override
    public String visitPlus(final DemoParser.PlusContext ctx) {
        return visit(ctx.left) + " PLUS " + visit(ctx.right);
    }

    @Override
    public String visitLiteralNumber(final DemoParser.LiteralNumberContext ctx) {
        return ctx.getText();
    }
}
