# Unit testing an ANTLR visitor

This project demonstrates how an ANTLR visitor can be unit tested.

## The compiler under test

The compiler under test will parse simple additions (e.g. `2+2`) and the output is similar but with the word "PLUS"
instead of the plus operator (e.g. `2 PLUS 2`).

## The test for literals

The tests are very simple so far. Especially the test `visitLiteralNumber_returnsTextValueOfNumber` tests only a single
line of code (`return ctx.getText();`). However think about how this test might be extended in the real life:
 
 - Maybe you allow decimals and digit grouping (e.g. since Java 8 this would be a legal literal: `1_337.42`). In this
   case you probably want to normalize the number (e.g. remove the underscore) which could be tested here as well).
 - Having your visitor work with Strings as return value is a questionable simplification. You may want to convert your
   values to instances of `java.lang.Number` which you could test here.
 - You could also test for error handling. E.g. what if the input contains a too large number which would cause an
   overflow?

## The test for the plus rule

The method `visitPlus_joinsOperatorsWithWordPLUSAsSeparator` interacts with the sub nodes of the plus node of the AST.
Since we do NOT want to test whether the visit methods of the sub nodes work as intended, but only want to test the
`visitPlus` method we need to mock these sub nodes. However you should never mock anything on the class you are testing.
However it is possible to mock the accept method on our AST node. To understand how that is done it is vital that you
understand the [Visitor pattern](http://en.wikipedia.org/wiki/Visitor_pattern).