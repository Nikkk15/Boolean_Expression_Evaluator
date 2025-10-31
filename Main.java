import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean stop;
        do{         
            System.out.println("Enter a boolean expression to be evaluated: ");
            String exprs = input.nextLine().trim();
            System.out.println(Evaluate(toPostfix(exprs)));
            System.out.println("Would you like to evaluate another boolean expression? (type 'y' to keep going or 'n' to stop): ");
            char ans = input.nextLine().charAt(0);
            stop = (ans == 'y') ? false : true;
        } while (!stop);                                        //Get expression from user
    }

    public static String toPostfix(String exprs){
        String postfix = "";                                    //Postfix expression
        ArrayStack<String> opStack = new ArrayStack<>();        //Operations Stack
        String[] tokens = exprs.split("\\s+");            //Turn original infix expression to a list in order to iterate
        for (String token : tokens){                            
            if (isNumber(token)) {                              //If number, add to postfix expression
                postfix += (" "+token);                     
            }
            else {                                              //If operand
                if (token.equals(")")) {                             //If closing paranthesis, add all operators to postfix expression until closing paranthesis is found
                    String top = opStack.peek();
                    while (!top.equals("(")){                         
                        postfix += (" "+ opStack.pop());
                        top = opStack.pop();
                    }
                }
                else{                                           //If not parantehsis, add operator to postfix expression
                    opStack.push(token);
                }
            }
        }
        if (!opStack.isEmpty()){                                //Add all remaining operators within the operator stack
            while (!opStack.isEmpty()){
                postfix += (" "+opStack.pop());
            }
        }
        return postfix;
    }

    public static boolean isNumber(String token){               //Check if number or operand
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
        
    }

    public static boolean Evaluate(String postfix){
        ArrayStack<Integer> stack = new ArrayStack<>();
        ArrayStack<Boolean> boolStack = new ArrayStack<>();
         String[] tokens = postfix.trim().split("\\s+");
        for (String token : tokens) {
            if (isNumber(token)) {
                int num = Integer.parseInt(token);
                stack.push(num);


        }
        return true;
        
        
    }

    public static boolean evaluateOperator(ArrayStack<Integer> stack, ArrayStack<Boolean> boolStack, String operator) {
        if ((stack.size() < 2) || (boolStack.size() < 2)) {
            System.out.println(" (Not enough operands)");
        return false;
        }
        if (operator.equals("!")){
            boolStack.push(!boolStack.pop());
        }
        else{
            boolean op2 = boolStack.pop();
            boolean op1 = boolStack.pop();
            if (operator.equals("&&")){
                boolStack.push(op1 && op2);
                return true;
            }
            else if (operator.equals("||")){
                boolStack.push(op1 || op2);
                return true;
            }
            else {
                System.out.println("Illegal operator");
                return false;
            }
        }

        int op2 = stack.pop();
        int op1 = stack.pop();
        

        if (operator.equals("<")) {
            boolStack.push(op1 < op2);
            return true;
        }
        else if (operator.equals(">")) {
            boolStack.push(op1 > op2);
            return true;
        }
        else if (operator.equals("<=")) {
            boolStack.push(op1 <= op2);
            return true;
        }
        else if (operator.equals(">=")) {
            boolStack.push(op1 >= op2);
            return true;
        }
        else if (operator.equals("==")) {
            boolStack.push(op1 >= op2);
            return true;
        }
        else if (operator.equals("!=")) {
            boolStack.push(op1 != op2);
            return true;
        }
        else {
            System.out.println(" (Illegal operator)");
            return false;
        }
}


        //Next steps:
        //1. Create expression evaluation method
        //- split string into list of characters
        //- convert infix expression to postfix notation using a stack and algorithm
        //- create algorithm to evaluate postfix expression
        //- return true or false
        //2. Check whether input is valid or not
}
}