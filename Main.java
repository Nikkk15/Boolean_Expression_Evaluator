import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean stop;
        do{         
            System.out.println("Enter a boolean expression to be evaluated: ");
            String exprs = input.nextLine();
            System.out.println(exprs);
            System.out.println("Would you like to evaluate another boolean expression? (type 'y' to keep going or 'n' to stop): ");
            char ans = input.nextLine().charAt(0);
            stop = (ans == 'y') ? false : true;
        } while (!stop);                                        //Get expression from user
    }
        //Next steps:
        //1. Create expression evaluation method
        //- split string into list of characters
        //- convert infix expression to postfix notation using a stack and algorithm
        //- create algorithm to evaluate postfix expression
        //- return true or false
        
}