# Evaluation of Boolean Expressions
Topic 1 (Evaluation of boolean expressions): In class, we worked on the
problems of converting the arithmetic expression from Infix to Postfix (Exercise
3). Besides, there is also an example of evaluating the postfix expression and
find its result (See stack examples on Canvas).
In this project, you will write a similar program to evaluate boolean expressions.
Rather than arithmetic operations, the input expressions for this program will
use the operations && (the “and” operation), ||(the “or” operation), and ! (the
“not” operation). Rather than combining numbers, the input expression will
combine simple boolean comparisons of numbers such as (1 < 2) and (6 < 3).
Assume all the numbers in these simple comparisons are integers. Allow the
following comparison operations:
<, >, <=, >=, ==, and !=.
At first, assume that all boolean expressions are fully parenthesized and well-
formed. Be sure to note that “not” is a unary operation. You can assume that
the argument to “not” (which follows the !) is enclosed in parentheses. Your
program should allow the user to evaluate additional expressions until the user
says he/she wishes to end the program.
Consider the following features in your program:
a. The numbers need not be integers;
b. the expression need not be fully parenthesized—if parentheses are missing,
then the java precedence rules apply (note that innermost expressions such
as (1 < 2) are still assumed to be in parentheses);
c. the expression need not be well-formed—if it is not, then the user is asked
to reenter the expression
