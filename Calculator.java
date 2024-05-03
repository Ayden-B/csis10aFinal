import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args){
        //Equation[] history = new Equation[];
        welcomeMessage();
        calculate();
        System.out.println("Thanks for calculating!");
    }
    public static void calculate(){
        Scanner keyboard = new Scanner(System.in);
        int eqNum;
        boolean cont = true;
        do {
            switch (keyboard.next()) {
                case ("HELP"): helpMessage();
                    ;
                case ("QUIT"): cont = false
                    ;
                case ("EQH"): //Equation[var];
                    ;
                case ("---"):
                    ;
                default:
            }
        }while(cont);
    }
    public static void welcomeMessage(){
        System.out.println("Welcome to this calculator!");
        System.out.println("---------------------------");
        System.out.println("If you are at any point confused or need help, type HELP instead of an equation.");
        System.out.println("Enter an Equation or type QUIT to stop calculating:");

    }
    public static void helpMessage(){
        System.out.println("* Please use \"*\" for multiplication and \"/\" for division.");
        System.out.println("  - Example: \"3*4\" or \"3/4\".");
        System.out.println("  - \"3x4\" will not work as x is assumed to be a variable");
        System.out.println("* For safety, you may want to encapsulate certain portions of an");
        System.out.println("  equation in parenthesis to ensure the proper Order of Operations.");
        System.out.println("* When multiplying a term against a parenthetical equation,");
        System.out.println("  Ex: \"3*(3+4)\", ensure that the asterisk is placed between the ");
        System.out.println("  parenthesis and the multiplicand.");
        System.out.println("   - \"3(3+4)\" may not work as intended");
        System.out.println("* If you would like to utilize the result of a previous equation as");
        System.out.println("  a variable you can use that equation's EQ-key (Ex: \"(EQ1)\" for ");
        System.out.println("  the first equation), or simply \"ANS\" for the most recent result.");
        System.out.println("* To view all previous equations, type \"EQH\"instead of an equation");
    }
}
