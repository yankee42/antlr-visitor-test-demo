package testdemo;

import demo.DemoParser;
import org.antlr.v4.runtime.RuleContext;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class MyVisitorTest {
    private final MyVisitor myVisitor = new MyVisitor();
    
    @Test
    public void visitPlus_joinsOperatorsWithWordPLUSAsSeparator() throws Exception {
        // setup
        final DemoParser.PlusContext plusNode = mock(DemoParser.PlusContext.class);
        plusNode.left = mockForVisitorResult(DemoParser.ExpressionContext.class, "2");
        plusNode.right = mockForVisitorResult(DemoParser.ExpressionContext.class, "4");
        
        // execution
        final String actual = myVisitor.visitPlus(plusNode);
        
        // evaluation
        assertEquals(actual, "2 PLUS 4");
    }

    /**
     * This method creates an abstract syntax tree node which, when passed to the visit() method of our visitor
     * will return a fixed result.
     * 
     * You should read about the Visitor pattern to understand how
     * {@link org.antlr.v4.runtime.RuleContext#accept(org.antlr.v4.runtime.tree.ParseTreeVisitor) the accept method} and
     * {@link org.antlr.v4.runtime.tree.AbstractParseTreeVisitor#visit(org.antlr.v4.runtime.tree.ParseTree) the visit method}
     * interact with each other.
     */
    private<T extends RuleContext> T mockForVisitorResult(final Class<T> nodeType, final String visitResult) {
        final T mock = mock(nodeType);
        when(mock.accept(myVisitor)).thenReturn(visitResult);
        return mock;
    }
    
    @Test
    public void visitLiteralNumber_returnsTextValueOfNumber() throws Exception {
        // setup
        final DemoParser.LiteralNumberContext literalNumberNode = mock(DemoParser.LiteralNumberContext.class);
        when(literalNumberNode.getText()).thenReturn("42");
        
        // execution
        final String actual = myVisitor.visitLiteralNumber(literalNumberNode);
        
        // evaluation
        assertEquals(actual, "42");
    }
}