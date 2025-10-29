import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean stop;
        do{         
            System.out.println("Enter a boolean expression to be evaluated: ");
            String exprs = input.nextLine().trim();
            System.out.println(toPostfix(exprs));
            System.out.println("Would you like to evaluate another boolean expression? (type 'y' to keep going or 'n' to stop): ");
            char ans = input.nextLine().charAt(0);
            stop = (ans == 'y') ? false : true;
        } while (!stop);                                        //Get expression from user
    }

    public static String toPostfix(String exprs){
        String postfix = "";                                    //Postfix expression
        ArrayStack<String> opStack = new ArrayStack<>();        //Operations Stack
        String[] tokens = exprs.split(" ");               //Turn original infix expression to a list in order to iterate
        for (String token : tokens){                            
            if (isNumber(token)) {                              //If number, add to postfix expression
                postfix += (" "+token);                     
            }
            else {                                              //If operand
                if (token == ")") {                             //If closing paranthesis, add all operators to postfix expression until closing paranthesis is found
                    String top = opStack.peek();
                    while (top != "("){                         
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



        //Next steps:
        //1. Create expression evaluation method
        //- split string into list of characters
        //- convert infix expression to postfix notation using a stack and algorithm
        //- create algorithm to evaluate postfix expression
        //- return true or false
        //2. Check whether input is valid or not
}
