import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args){
        ArrayList<Equation> history = new ArrayList<Equation>();
        welcomeMessage();
        calculate(history);
        exitMessage();

    }
    public static void calculate(ArrayList<Equation> history){
        Scanner keyboard = new Scanner(System.in);
        int eqNum = 1;
        boolean cont = true;
        System.out.println("Enter an Equation, type HELP for help, or type QUIT to stop calculating:");
        do {
            String entered = keyboard.next();
            switch (entered) {
                case ("HELP"):
                    helpMessage();
                    break;
                case ("QUIT"):
                    cont = false;
                    break;
                case ("EQH"):
                    System.out.println(history);
                    break;
                case ("---"):
                    System.out.println("test successful");
                    break;
                default:

                    history.add(new Equation(eqNum, findEQKey(history,entered)));
                    System.out.println(history.getLast().answer);
                    break;
            }
            eqNum++;
        }while(cont);
    }
    public static String findEQKey(ArrayList<Equation> history,String entered){
        String returner = entered;
        String target;
        char[] terms  = new char[]{'(', ')', '+', '-', '*', '/', 'E'};
        if(entered.contains("ANS")){
            returner = entered.replace("ANS", history.getLast().answer);

        }
        if(entered.contains("EQ")){
            int locEQ = entered.indexOf("EQ");
            target = entered.substring(locEQ, Equation.findChars(entered.substring(locEQ+2), terms)+2);
            String numkey = target.substring(2);
            if (numkey.matches("[0-9]+")) {
                int id = Integer.parseInt(numkey);
                if (id <= history.size()){
                    returner = returner.replace(target, history.get(id-1).answer);
                }
            }
        }
        return returner;
    }
    public static void welcomeMessage(){
        System.out.println("Welcome to this calculator!");
        System.out.println("---------------------------");

    }
    private static void exitMessage() {
        System.out.println("Thanks for calculating!");
    }
    public static void helpMessage(){
        System.out.println("* Please use \"*\" for multiplication and \"/\" for division.");
        System.out.println("  - Example: \"3*4\" or \"3/4\".");
        System.out.println("  - \"3x4\" will not work as x is assumed to be a variable");
        System.out.println("* For safety, you may want to encapsulate certain portions of an");
        System.out.println("  equation in parenthesis to ensure the proper Order of Operations.");
        System.out.println("* When multiplying a term against a parenthetical equation,");
        System.out.println("  Ex: \"3*(3+4)\", ensure that an asterisk is placed between the ");
        System.out.println("  parenthesis and the multiplier.");
        System.out.println("   - \"3(3+4)\" may not work as intended");
        System.out.println("* If you would like to utilize the result of a previous equation as");
        System.out.println("  a variable you can use that equation's EQ-key (Ex: \"(EQ1)\" for ");
        System.out.println("  the first equation), or simply \"ANS\" for the most recent result.");
        System.out.println("  Remember that all inserts (\"ANS\", \"EQ_\") should be in all-caps");
        System.out.println("* To view all previous equations, type \"EQH\"instead of an equation");
    }
}
