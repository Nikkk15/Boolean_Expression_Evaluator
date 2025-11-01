import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean stop;
        
        // Intro and instructions for user
        System.out.println("=== Boolean Expression Evaluator ===\n");
        System.out.println("Supported operators: <, >, <=, >=, ==, !=, &&, ||, !");
        System.out.println("Use parentheses and spaces between tokens (e.g., ( 5 > 3 ) && ( 2 != 1 ))\n");

        do{                                                                                     //User input loop, keep asking until user chooses to stop                                                                                                                                             
            System.out.println("Enter a boolean expression to be evaluated: ");
            String exprs = input.nextLine().trim();
            try {
                System.out.println(Evaluate(toPostfix(exprs)));                     //Convert infix expression to postfix, evaluate postfix expression and print boolean result
            } catch (IllegalArgumentException e) {                      
                System.out.println("Error: " + e.getMessage());                     //Expected user errors (mismatch parantheses, incomplete expression, etc.)
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());          //Any other unexpected error
            }
            System.out.println("Would you like to evaluate another boolean expression? (type 'y' to keep going or 'n' to stop): ");
            char ans = input.nextLine().trim().toLowerCase().charAt(0);
            stop = (ans == 'y') ? false : true;
        } while (!stop); 
        input.close();                                       
    }

    public static String toPostfix(String exprs){
        String postfix = "";                                    //Postfix expression built as a single string
        ArrayStack<String> opStack = new ArrayStack<>();        //Operations Stack
        String[] tokens = exprs.split("\\s+");            //Turn original infix expression to a list in order to iterate
        for (String token : tokens){                            
            if (isNumber(token)) {                              //If token is number, append to postfix expression
                postfix += (" "+token);                     
            }
            else {                                              //Otherwise, token is an operator or paranthesis
                if (token.equals(")")) {                             //If closing paranthesis, add all operators to postfix expression until closing paranthesis is found
                    while (!opStack.isEmpty() && !opStack.peek().equals("(")) {                         
                        postfix += (" "+ opStack.pop());
                    }
                    if (opStack.isEmpty()) {                                    //If '(' never found, then parentheses are mismatched
                        throw new IllegalArgumentException("Mismatched parentheses: missing '('");
                    }
                    opStack.pop();                                              //Discard remaing '('
                }                                   
                else if (opStack.isEmpty() || precedence(opStack.peek()) < precedence(token)){
                    opStack.push(token);        //If operator stack is empty or precedence of top element in stack is lesser then the precedence of the current operator, just push token onto operation stack
                }
                else {
                    while (!opStack.isEmpty() && precedence(opStack.peek()) >= precedence(token)){
                        postfix += (" "+ opStack.pop());   //While the operator on top has a higher or equal precedence, pop and append to postfix expression
                    }
                    opStack.push(token);                    //push current operator
                }
            }
        }
    
                                       //Add all remaining operators within the operator stack
            while (!opStack.isEmpty()){
                String top = opStack.peek();
                if (top.equals("(") || top.equals(")")) {   //any leftover parantheses means mismatch
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                postfix += (" "+opStack.pop());
            }
            
        
        return postfix;         //return final postfix expression
    }


    public static boolean isNumber(String token){               //Check if number or operand
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
        
    }

    public static boolean Evaluate(String postfix){
        ArrayStack<Double> stack = new ArrayStack<>();                  //Number stack
        ArrayStack<Boolean> boolStack = new ArrayStack<>();             //Boolean stack
        String[] tokens = postfix.trim().split("\\s+");
        boolean expressionOk = true;                                    //tracks if evaluation stays valid
        for (String token : tokens) {
            if (isNumber(token)) {                                      //If number, push to number stack
                double num = Double.parseDouble(token);
                stack.push(num);
                }
            else {                                                      //If not, then it is an operator  
                expressionOk = evaluateOperator(stack, boolStack, token);           //if operator application failed thne there were not enough operands
                if (!expressionOk){
                    break;
                }
            }
        }
        //After all actions are performed, we expect exactly one boolean value inside the boolean stack, otherwise there was an error with expression provided
        if (boolStack.isEmpty()) {throw new IllegalArgumentException("Incomplete expression");}
        if (boolStack.size() > 1) { throw new IllegalArgumentException("Bad expression: extra boolean values remain"); }
        return boolStack.pop();                 //Final result of evaluation
    }
    public static boolean evaluateOperator(ArrayStack<Double> stack, ArrayStack<Boolean> boolStack, String operator) {
        if (operator.equals("!")) {             //Check for NOT
            if (boolStack.size() < 1) {                  
                System.out.println("(Not enough operands)");
                return false;
            }
            boolStack.push(!boolStack.pop());           //Flip boolean
            return true;                    
        }
        if (operator.equals("&&") || operator.equals("||")) {       //Check for logical AND / OR
            if (boolStack.size() < 2) {
                System.out.println("(Not enough operands)");
                return false;
            }
            boolean op2 = boolStack.pop();
            boolean op1 = boolStack.pop();
            if (operator.equals("&&")) {
                boolStack.push(op1 && op2);
            } 
            else {
                boolStack.push(op1 || op2);
            }
            return true;
        }

        if (stack.size() < 2) {                                     //All comparison operations
        System.out.println("(Not enough operands)");
        return false;
    }
    double op2 = stack.pop();
    double op1 = stack.pop();
                                                            
    if (operator.equals("<"))       { boolStack.push(op1 <  op2); return true; }  //Check for the operator if op1 is less than op2
    else if (operator.equals(">"))  { boolStack.push(op1 >  op2); return true; }  //Check for the operator if op1 is more than op2  
    else if (operator.equals("<=")) { boolStack.push(op1 <= op2); return true; }  //Check for the operator if op1 is lee or equal than op2
    else if (operator.equals(">=")) { boolStack.push(op1 >= op2); return true; }  //Check for the operator if op1 is more ot equal than op2
    else if (operator.equals("==")) { boolStack.push(op1 == op2); return true; }  //Check for the operator if op1 is equal op2
    else if (operator.equals("!=")) { boolStack.push(op1 != op2); return true; }  //Check for the operator if op1 is not equal op2

    System.out.println(" (Illegal operator)");  //If we get to this point it means operator token wasn't recognized
    return false;
}

    public static int precedence(String op) {               //Calculate order of precedence for each comparison and operation
        switch (op) {
            case "!": return 4;
            case "<": case ">": case "<=": case ">=": case "==": case "!=": return 3;
            case "&&": return 2;
            case "||": return 1;
            default: return 0;
        }
    }
}
